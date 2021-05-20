package ua.kpi.diploma.controltestinghub.service;

import org.springframework.transaction.annotation.Transactional;
import ua.kpi.diploma.controltestinghub.dto.CreateUpdateTestCaseDto;
import ua.kpi.diploma.controltestinghub.dto.ScenarioStepDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseWithUserDto;
import ua.kpi.diploma.controltestinghub.exception.ValidationException;
import ua.kpi.diploma.controltestinghub.model.TestCaseTopSubscribed;
import ua.kpi.diploma.controltestinghub.model.TestCaseUpd;
import ua.kpi.diploma.controltestinghub.util.Pageable;

import java.util.List;


public interface TestCaseService {

    void createTestCase(CreateUpdateTestCaseDto createUpdateTestCaseDto, Integer userId);

    List<ScenarioStepDto> getTestScenarioStep(Integer testCaseId);

    TestCaseDto getTestCase(Integer testCaseId);

    List<TestCaseWithUserDto> getTestCasesWithUser(Integer projectID, Pageable pageable, String name) throws ValidationException;

    List<TestCaseUpd> getAllTestCases();

    Integer countTestCasesByProject(Integer pageSize, Long projectId);

    List<TestCaseTopSubscribed> getFiveTopSubscribedTestCases();

    void updateTestCase(CreateUpdateTestCaseDto createUpdateTestCaseDto);

    void addSubscriber(Integer testCaseId, Integer userId);

    Boolean isFollowedByUser(Integer testCaseId, Integer userId);

    void removeSubscriber(Integer testCaseId, Integer userId);

    void archiveTestCase(Integer testCaseId);

    void unarchiveTestCase(Integer testCaseId);

}
