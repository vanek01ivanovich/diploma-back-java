package ua.kpi.diploma.controltestinghub.dao;

import ua.kpi.diploma.controltestinghub.dto.TestCaseWithUserDto;
import ua.kpi.diploma.controltestinghub.model.TestCase;
import ua.kpi.diploma.controltestinghub.model.TestCaseStep;
import ua.kpi.diploma.controltestinghub.model.TestCaseTopSubscribed;
import ua.kpi.diploma.controltestinghub.model.TestCaseUpd;

import java.util.List;

public interface TestCaseDao {
    Integer insert(TestCase testCase);

    List<TestCaseStep> getTestCaseSteps(Integer testCaseId);

    List<TestCaseUpd> getTestCases();

    List<TestCaseWithUserDto> getTestCasesWithUserPageSorted(Integer projectID, String orderByLimitOffsetWithValues, String name);

    Integer countTestCasesByProject(Integer projectId);

    List<TestCaseTopSubscribed> getTopFiveSubscribedTestCases();

    void update(Integer testCaseId, String newTestCaseName);

    void addSubscriber(Integer testCaseId, Integer userId);

    Boolean isFollowedByUser(Integer testCaseId, Integer userId);

    void removeSubscriber(Integer testCaseId, Integer userId);

    TestCase getTestCaseById(Integer testCaseId);

    void updateIsArchivedField(Integer projectId, boolean isArchived);

}
