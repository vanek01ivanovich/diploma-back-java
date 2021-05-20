package ua.kpi.diploma.controltestinghub.dao;

import ua.kpi.diploma.controltestinghub.dto.GroupedTestCaseExecutionDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseExecutionDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseExecutionsCountsByStartDatesDto;
import ua.kpi.diploma.controltestinghub.model.TestCaseExecution;

import java.sql.Date;
import java.util.List;

public interface TestCaseExecutionDao {
    List<TestCaseExecution> getAllTestCaseExecutions();
    List<TestCaseExecutionDto> getAllTestCaseExecutionWithFailedActionNumber(Integer limit, Integer offset, String orderBy, String orderByClause, String testCaseName, String projectName, String status, String whereByStatus);
    Integer createTestCaseExecution(Integer testCaseId, Integer userId);
    void updateTestCaseExecution(String status, Integer testCaseExecutionId);

    List<TestCaseExecutionsCountsByStartDatesDto> getExecutionsByStartDate(Date fromDate, Date tillDate);

    List<GroupedTestCaseExecutionDto> getGroupedTestCaseExecution();

    TestCaseExecution getTestCaseExecutionById(Integer testCaseExecutionId);

    Integer countTestCaseExecutions(String testCaseName, String projectName, String status);

}
