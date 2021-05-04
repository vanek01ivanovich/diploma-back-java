package ua.kpi.diploma.controltestinghub.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TestScenarioServiceImpl implements TestScenarioService {

    private final TestScenarioDAO testScenarioDAO;
    private final Pagination pagination;
    private final List<String> TEST_SCENARIO_TABLE_FIELDS = Arrays.asList("id", "name");
    private final CompoundInstanceDAO compoundInstanceDAO;
    private final CompoundDAO compoundDAO;
    private final ActionInstanceDAO actionInstanceDAO;

    public TestScenarioServiceImpl(TestScenarioDAO testScenarioDAO, Pagination pagination, CompoundInstanceDAO compoundInstanceDAO, CompoundDAO compoundDAO, ActionInstanceDAO actionInstanceDAO) {
        this.testScenarioDAO = testScenarioDAO;
        this.pagination = pagination;
        this.compoundInstanceDAO = compoundInstanceDAO;
        this.compoundDAO = compoundDAO;
        this.actionInstanceDAO = actionInstanceDAO;
    }

    /**
     * @param id id of TestScenario object in database.
     * @return list that contains or one TestScenario object, or nothing.
     */
    @Override
    public List<TestScenario> getTestScenarioById(long id) {
        List<TestScenario> testScenarios = testScenarioDAO.getTestScenarioById(id);
        if(testScenarios.isEmpty()) {
            log.warn("IN getTestScenarioById - no test scenario found by id: {}", id);
            return testScenarios;
        }
        log.info("IN getTestScenarioById - test scenario: {} found by id: {}", testScenarios, id);
        return testScenarios;
    }

    /**
     * @param testScenarioDto object that will be updated in database.
     * @return TRUE - if TestScenario object was updated or
     * FALSE - if the one wasn't updated.
     */
    @Override
    public boolean updateTestScenario(TestScenarioDtoWithIdNameArchived testScenarioDto) {
        String testScenarioName = testScenarioDto.getName();
        if(testScenarioDAO.checkExistTestScenarioByName(testScenarioName)) {
            log.warn("IN updateTestScenario - test scenario already exists with name: {}", testScenarioName);
            return false;
        }
        TestScenario testScenario = new TestScenario(
                testScenarioDto.getId(),
                testScenarioDto.getName(),
                testScenarioDto.isArchived()
        );
        testScenarioDAO.updateTestScenarioById(testScenario);
        log.info("IN updateTestScenario - test scenario: {} is updated", testScenarioDto);
        return true;
    }

    /**
     * It checks existence TestScenario object by name in database.
     * If the one exists - return boolean FALSE.
     * If the one doesn't exist - create TestScenario object in database and return his id,
     * then actions and compounds will be created via TestScenario id,
     * after this return boolean TRUE.
     *
     * @param testScenarioDto object will be created in database.
     * @return TRUE - if TestScenario object was created or
     * FALSE - if the one wasn't created.
     */
    @Override
    public boolean saveTestScenario(TestScenarioDto testScenarioDto) {
        String testScenarioName = testScenarioDto.getName();
        if(testScenarioDAO.checkExistTestScenarioByName(testScenarioName)) {
            log.warn("IN saveTestScenario - test scenario already exists with name: {}", testScenarioName);
            return false;
        }
        long testScenarioId = testScenarioDAO.saveTestScenario(testScenarioDto);
        List<TestScenarioItemDto> actionsWithoutCompoundInstanceId =
                getItemsByType("Action", testScenarioDto.getItems());
        actionInstanceDAO.saveActionInstancesWithoutCompoundInstanceId(
                actionsWithoutCompoundInstanceId, testScenarioId
        );
        log.info("IN saveTestScenario - action instances: {} are saved without compound instance id", actionsWithoutCompoundInstanceId);
        List<TestScenarioItemDto> compounds =
                getItemsByType("Compound", testScenarioDto.getItems());
        for(TestScenarioItemDto compound : compounds) {
            long compoundId = compoundInstanceDAO.saveCompoundInstanceAndGetGeneratedId(compound, testScenarioId);
            List<TestScenarioItemDto> actions = compound.getItems();
            actionInstanceDAO.saveActionInstancesWithCompoundInstanceId(
                    actions,
                    testScenarioId,
                    compoundId
            );
            log.info("IN saveTestScenario - action instances: {} are saved with compound instance id: {}", actions, compoundId);
        }
        log.info("IN saveTestScenario - test scenario with name: {} is saved", testScenarioName);
        return true;
    }

    /**
     * @param id id of Compound object.
     * @return list of actions by compound id.
     * Action objects have fields id and priority.
     */
    @Override
    public List<CompoundActionWithActionIdAndPriority> getAllCompoundActionsByCompoundId(long id) {
        List<CompoundActionWithActionIdAndPriority> actions =
                compoundDAO.findAllCompoundActionsWithActionIdAndPriorityByCompoundId(id);
        if(actions.isEmpty()) {
            log.warn("IN getAllCompoundActionsByCompoundId - no actions found with compound is: {}", id);
            return actions;
        }
        log.info("IN getAllCompoundActionsByCompoundId - actions: {} are found with compound id: {}", actions, id);
        return actions;
    }

    @Override
    public List<TestScenario> getTestScenarios(Pageable pageable, String name) throws ValidationException {
        pageable = pagination.replaceNullsUserPage(pageable);
        pagination.validate(pageable,TEST_SCENARIO_TABLE_FIELDS);
        return testScenarioDAO.getTestScenariosPageSorted(pagination.formSqlPostgresPaginationPiece(pageable),
                replaceNullsForSearch(name));
    }

    @Override
    public List<TestScenario> getAll(){
        return testScenarioDAO.getAll();
    }

    /**
     * @param type type can be: Action or Compound.
     * @return list of TestScenarioItemDto objects,
     * that is filtered by String type: Action or Compound.
     */
    private List<TestScenarioItemDto> getItemsByType(String type, List<TestScenarioItemDto> items) {
        return items.stream()
                .filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());

    }

    @Override
    public Integer countPages(Integer pageSize) {
        return pagination.countPages(testScenarioDAO.countUsers(), pageSize);
    }

    private String replaceNullsForSearch(String val) {
        return val == null ? "%" : val;
    }
}
