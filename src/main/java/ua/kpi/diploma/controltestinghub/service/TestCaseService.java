package ua.kpi.diploma.controltestinghub.service;

import org.springframework.transaction.annotation.Transactional;


public interface TestCaseService {

    void createTestCase(CreateUpdateTestCaseDto createUpdateTestCaseDto, Long userId);

    List<ScenarioStepDto> getTestScenarioStep(Long testCaseId);

    TestCaseDto getTestCase(Long testCaseId);

    List<TestCaseWithUserDto> getTestCasesWithUser(Long projectID, Pageable pageable, String name) throws ValidationException;

    List<TestCaseUpd> getAllTestCases();

    Integer countTestCasesByProject(Integer pageSize, Long projectId);

    List<TestCaseTopSubscribed> getFiveTopSubscribedTestCases();

    void updateTestCase(CreateUpdateTestCaseDto createUpdateTestCaseDto);

    void addSubscriber(Long testCaseId, Long userId);

    Boolean isFollowedByUser(Long testCaseId, Long userId);

    void removeSubscriber(Long testCaseId, Long userId);

    void archiveTestCase(Long testCaseId);

    void unarchiveTestCase(Long testCaseId);

}
