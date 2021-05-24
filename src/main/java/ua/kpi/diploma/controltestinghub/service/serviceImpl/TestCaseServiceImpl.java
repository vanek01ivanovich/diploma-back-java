package ua.kpi.diploma.controltestinghub.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kpi.diploma.controltestinghub.dao.ActionInstanceDao;
import ua.kpi.diploma.controltestinghub.dao.TestCaseDao;
import ua.kpi.diploma.controltestinghub.dao.VariableValueDao;
import ua.kpi.diploma.controltestinghub.dto.*;
import ua.kpi.diploma.controltestinghub.exception.ValidationException;
import ua.kpi.diploma.controltestinghub.model.*;
import ua.kpi.diploma.controltestinghub.service.TestCaseService;
import ua.kpi.diploma.controltestinghub.util.Pageable;
import ua.kpi.diploma.controltestinghub.util.Pagination;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class TestCaseServiceImpl implements TestCaseService {

    private final TestCaseDao testCaseDao;
    private final VariableValueDao variableValueDao;
    private final ActionInstanceDao actionInstanceDao;
    private final Pagination pagination;
    private final List<String> TEST_CASE_UPD_WITH_USER_TABLE_FIELDS = Arrays.asList("id", "name", "email");


    public TestCaseServiceImpl(TestCaseDao testCaseDao, VariableValueDao variableValueDao, ActionInstanceDao actionInstanceDao, Pagination pagination) {
        this.testCaseDao = testCaseDao;
        this.variableValueDao = variableValueDao;
        this.actionInstanceDao = actionInstanceDao;
        this.pagination = pagination;
    }

    /**
     * For filtering distinct by object field
     *
     * @param keyExtractor getter for object field
     * @param <T>          type of object
     * @return whether field added to set
     */
    private <T> Predicate<T> distinctBy(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    /**
     * Grouping variables by actionInstances
     *
     * @param actionInstanceJoinedList actions with variables
     * @return map with actionInstanceId as key and variableDto
     */
    private Map<Integer, List<VariableDto>> getActionVariables(List<ActionInstanceJoined> actionInstanceJoinedList) {
        return actionInstanceJoinedList.stream()
                .collect(Collectors.groupingBy(ActionInstanceJoined::getId,
                        Collectors.mapping(ai -> VariableDto.builder()
                                .id(ai.getVariable().getId())
                                .name(ai.getVariable().getName())
                                .build(), Collectors.toList())));
    }

    /**
     * Grouping variables with data entries by actionInstances
     *
     * @param testCaseSteps actions with variables and values
     * @return map with actionInstanceId as key and variableDto with dataEntry as value
     */
    private Map<Integer, List<VariableDto>> getActionVariablesWithDataEntries(List<TestCaseStep> testCaseSteps) {
        return testCaseSteps.stream()
                .collect(Collectors.groupingBy(tcs -> tcs.getActionInstanceJoined().getId(),
                        Collectors.mapping(tcs -> VariableDto.builder()
                                .variableValueId(tcs.getActionInstanceJoined().getVariableValueId())
                                .id(tcs.getActionInstanceJoined().getVariable().getId())
                                .name(tcs.getActionInstanceJoined().getVariable().getName())
                                .dataEntry(tcs.getDataEntry())
                                .build(), Collectors.toList())));
    }

    /**
     * Get scenario steps that are compounds
     * @param actionInstanceJoinedList scenario steps
     * @param actionsVariables map of actionInstanceIds and variables
     * @return List of scenario step dtos that are compounds
     */
    private List<ScenarioStepDto> getCompoundsByPriorities(List<ActionInstanceJoined> actionInstanceJoinedList,
                                                           Map<Integer, List<VariableDto>> actionsVariables) {
        Map<Integer, List<ActionInstanceJoined>> priorityCompound = actionInstanceJoinedList.stream()
                .filter(ai -> ai.getCompoundInstance() != null)
                .collect(Collectors.groupingBy(ai -> ai.getCompoundInstance().getPriority(),
                        Collectors.toList()));

        return priorityCompound.entrySet().stream()
                .map(e -> ScenarioStepDto.builder()
                        .priority(e.getKey())
                        .compound(e.getValue().get(0).getCompoundInstance().getCompound())
                        .actionDto(e.getValue().stream()
                                //filtering unique action instances by priority
                                .filter(distinctBy(ActionInstanceJoined::getPriority))
                                //sorting actions in compound by priorities in them
                                .sorted(Comparator.comparingLong(ActionInstanceJoined::getPriority))
                                .map(ai -> ActionDto.builder()
                                        .actionInstanceId(ai.getId())
                                        .name(ai.getAction().getActionName())
                                        .variables(actionsVariables.getOrDefault(ai.getId(), new ArrayList<>())).build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * Get scenario steps that are actions
     * @param actionInstanceJoinedList scenario steps
     * @param actionsVariables map of actionInstanceIds and variables
     * @return List of scenario step dtos that are actions
     */
    private List<ScenarioStepDto> getActionsByPriorities(List<ActionInstanceJoined> actionInstanceJoinedList,
                                                         Map<Integer, List<VariableDto>> actionsVariables) {
        return actionInstanceJoinedList.stream()
                .filter(ai -> ai.getCompoundInstance() == null)
                .map(ai -> ScenarioStepDto.builder()
                        .priority(ai.getPriority())
                        .actionDto(Collections.singletonList(ActionDto.builder()
                                .actionInstanceId(ai.getId())
                                .name(ai.getAction().getActionName())
                                .variables(actionsVariables.getOrDefault(ai.getId(), new ArrayList<>())).build()))
                        .build())
                .filter(distinctBy(ScenarioStepDto::getPriority))
                .collect(Collectors.toList());
    }

    private List<ScenarioStepDto> buildTestScenarioStep(List<ActionInstanceJoined> actionInstanceJoinedList, Map<Integer, List<VariableDto>> actionsVariables) {
        //getting compounds by priorities
        List<ScenarioStepDto> priorityCompound = getCompoundsByPriorities(actionInstanceJoinedList, actionsVariables);

        //getting actions that not in compounds by priorities
        List<ScenarioStepDto> priorityActionNotCompound = getActionsByPriorities(actionInstanceJoinedList, actionsVariables);

        //concatenating steps and sorting by priority
        return Stream.concat(priorityCompound.stream(), priorityActionNotCompound.stream()).sorted(Comparator.comparing(ScenarioStepDto::getPriority))
                .collect(Collectors.toList());
    }


    /**
     * Getting scenario step (test case creation)
     */
    @Override
    public List<ScenarioStepDto> getTestScenarioStep(Integer testCaseId) {

        List<ActionInstanceJoined> actionInstanceJoinedList = actionInstanceDao.getActionInstanceJoinedByTestCaseId(testCaseId);

        //getting list if variables for each action
        Map<Integer, List<VariableDto>> actionsVariables = getActionVariables(actionInstanceJoinedList);

        return buildTestScenarioStep(actionInstanceJoinedList, actionsVariables);

    }

    @Override
    public TestCaseDto getTestCase(Integer testCaseId) {

        List<TestCaseStep> testCaseSteps = testCaseDao.getTestCaseSteps(testCaseId);

        //getting list if variables for each action
        Map<Integer, List<VariableDto>> actionsVariables = getActionVariablesWithDataEntries(testCaseSteps);

        List<ActionInstanceJoined> actionInstanceJoinedList = testCaseSteps.stream()
                .map(TestCaseStep::getActionInstanceJoined).collect(Collectors.toList());

        List<ScenarioStepDto> scenarioStepsWithData = buildTestScenarioStep(actionInstanceJoinedList, actionsVariables);

        return TestCaseDto.builder()
                .projectName(testCaseSteps.get(0).getProjectName())
                .projectLink(testCaseSteps.get(0).getProjectLink())
                .testCase(testCaseSteps.get(0).getTestCase())
                .scenarioStepsWithData(scenarioStepsWithData)
                .build();

    }

    @Override
    @Transactional
    public void createTestCase(CreateUpdateTestCaseDto createUpdateTestCaseDto, Integer userId) {

        TestCase testCase = TestCase.builder()
                .name(createUpdateTestCaseDto.getTestCaseName())
                .testScenarioId(createUpdateTestCaseDto.getTestScenarioId())
                .dataSetId(createUpdateTestCaseDto.getDataSetId())
                .projectId(createUpdateTestCaseDto.getProjectId())
                .userId(userId)
                .build();

        Integer testCaseId = testCaseDao.insert(testCase);
        variableValueDao.insert(createUpdateTestCaseDto.getVariableValues(), testCaseId);
        log.info("Test case created: {} with id: {}", createUpdateTestCaseDto, testCaseId);
    }

    @Override
    public List<TestCaseUpd> getAllTestCases() {
        return testCaseDao.getTestCases();
    }

    /**
     * @return list of five TestCase objects those have the greatest number of subscribers.
     */
    @Override
    public List<TestCaseTopSubscribed> getFiveTopSubscribedTestCases() {
        List<TestCaseTopSubscribed> testCases = testCaseDao.getTopFiveSubscribedTestCases();
        if(testCases.isEmpty()) {
            log.warn("IN getFiveTopSubscribedTestCases - no test cases found");
            return testCases;
        }
        log.info("IN getFiveTopSubscribedTestCases - test cases: {} found", testCases);
        return testCases;
    }

    @Override
    public Integer countTestCasesByProject(Integer pageSize, Integer projectId) {
        return pagination.countPages(testCaseDao.countTestCasesByProject(projectId), pageSize);
    }

    @Override
    public List<TestCaseWithUserDto> getTestCasesWithUser(Integer projectID, Pageable pageable, String name) throws ValidationException {
        pageable = pagination.replaceNullsUserPage(pageable);
        pagination.validate(pageable, TEST_CASE_UPD_WITH_USER_TABLE_FIELDS);
        return testCaseDao.getTestCasesWithUserPageSorted(projectID, pagination.formSqlPostgresPaginationPiece(pageable),
                replaceNullsForSearch(name));
    }

    @Transactional
    @Override
    public void updateTestCase(CreateUpdateTestCaseDto createUpdateTestCaseDto) {
        testCaseDao.update(createUpdateTestCaseDto.getId(), createUpdateTestCaseDto.getTestCaseName());
        variableValueDao.updateDataEntry(createUpdateTestCaseDto.getVariableValues());
        log.info("Test case updated: {}", createUpdateTestCaseDto);
    }

    @Override
    public void addSubscriber(Integer testCaseId, Integer userId) {
        testCaseDao.addSubscriber(testCaseId, userId);
        log.info("User with id: {} is subscribed to test case with id: {}", userId, testCaseId);
    }

    @Override
    public Boolean isFollowedByUser(Integer testCaseId, Integer userId) {
        return testCaseDao.isFollowedByUser(testCaseId, userId);
    }

    @Override
    public void removeSubscriber(Integer testCaseId, Integer userId) {
        testCaseDao.removeSubscriber(testCaseId, userId);
        log.info("User with id: {} is unsubscribed to test case with id: {}", userId, testCaseId);
    }

    @Override
    public void archiveTestCase(Integer testCaseId) {
        testCaseDao.updateIsArchivedField(testCaseId, true);
        log.info("Test case with id: {} is archived", testCaseId);
    }

    @Override
    public void unarchiveTestCase(Integer testCaseId) {
        testCaseDao.updateIsArchivedField(testCaseId, false);
        log.info("Test case with id: {} is unarchived", testCaseId);
    }

    private String replaceNullsForSearch(String val) {
        return val == null ? "%" : val;
    }
}
