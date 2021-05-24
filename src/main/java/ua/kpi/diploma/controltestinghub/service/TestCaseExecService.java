package ua.kpi.diploma.controltestinghub.service;


import ua.kpi.diploma.controltestinghub.dto.GroupedTestCaseExecutionDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseExecutionDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseExecutionsCountsByStartDatesDto;
import ua.kpi.diploma.controltestinghub.model.TestCaseExecution;

import java.util.List;

public interface TestCaseExecService {

    List<TestCaseExecution> getAllTestCaseExecutions();

    Integer countTestCaseExecutions(String testCaseName, String projectName, String status);

    List<TestCaseExecutionDto> getAllTestCaseExecutionWithFailedActionNumber(Integer limit, Integer offset, String orderBy, String orderByClause, String testCaseName, String projectName, String status);

    Integer createTestCaseExecution(Integer testCaseId, Integer userId);

    void updateTestCaseExecution(String status, Integer testCaseExecutionId);

    List<TestCaseExecutionsCountsByStartDatesDto> getExecutionsByDatesForLastDays(Integer daysFromToday);

    List<GroupedTestCaseExecutionDto> getGroupedTestCaseExecution();
}
