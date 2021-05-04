package ua.kpi.diploma.controltestinghub.service;


import java.util.List;

public interface TestCaseExecService {

    List<TestCaseExecution> getAllTestCaseExecutions();

    Integer countTestCaseExecutions(String testCaseName, String projectName, String status);

    List<TestCaseExecutionDto> getAllTestCaseExecutionWithFailedActionNumber(long limit,long offset, String orderBy, String orderByClause, String testCaseName, String projectName, String status);

    Long createTestCaseExecution(Long testCaseId, long userId);

    void updateTestCaseExecution(String status, long testCaseExecutionId);

    List<TestCaseExecutionsCountsByStartDatesDto> getExecutionsByDatesForLastDays(Integer daysFromToday);

    List<GroupedTestCaseExecutionDto> getGroupedTestCaseExecution();
}
