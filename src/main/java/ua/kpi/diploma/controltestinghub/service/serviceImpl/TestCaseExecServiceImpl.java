package ua.kpi.diploma.controltestinghub.service.serviceImpl;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class TestCaseExecServiceImpl implements TestCaseExecService {

    private final TestCaseExecutionDAO testCaseExecutionDAO;
    private String whereByStatus = "";
    private String testCaseName = "";
    private String projectName = "";

    public TestCaseExecServiceImpl(TestCaseExecutionDAO testCaseExecutionDAO) {
        this.testCaseExecutionDAO = testCaseExecutionDAO;
    }

    @Override
    public List<TestCaseExecution> getAllTestCaseExecutions() {
        return testCaseExecutionDAO.getAllTestCaseExecutions();
    }

    @Override
    public Integer countTestCaseExecutions(String testCaseName, String projectName, String status) {
        filterTestCase(testCaseName, projectName, status);
        return testCaseExecutionDAO.countTestCaseExecutions(this.testCaseName, this.projectName, whereByStatus);
    }

    @Override
    public List<TestCaseExecutionDto> getAllTestCaseExecutionWithFailedActionNumber(long limit, long offset, String orderBy, String orderByClause, String testCaseName, String projectName, String status) {
        filterTestCase(testCaseName, projectName, status);
        return testCaseExecutionDAO.getAllTestCaseExecutionWithFailedActionNumber(limit, offset, orderBy, orderByClause, this.testCaseName, this.projectName, status, whereByStatus);
    }

    @Override
    public Long createTestCaseExecution(Long testCaseId, long userId) {
        return testCaseExecutionDAO.createTestCaseExecution(testCaseId, userId);
    }

    @Override
    public void updateTestCaseExecution(String status, long testCaseExecutionId) {
        testCaseExecutionDAO.updateTestCaseExecution(status, testCaseExecutionId);
    }

    @Override
    public List<TestCaseExecutionsCountsByStartDatesDto> getExecutionsByDatesForLastDays(Integer daysFromToday){
        LocalDate today = LocalDate.now();
        LocalDate dateToCountFrom = today.minusDays(daysFromToday);
        return testCaseExecutionDAO.getExecutionsByStartDate(Date.valueOf(dateToCountFrom), Date.valueOf(today));
    }

    /**
     * Method needed for chart on dashboard
     * @return list of GroupedTestCaseExecutionDto
     */
    @Override
    public List<GroupedTestCaseExecutionDto> getGroupedTestCaseExecution() {
        return testCaseExecutionDAO.getGroupedTestCaseExecution();
    }

    private void filterTestCase(String testCaseName, String projectName, String status) {
        this.testCaseName = testCaseName.equals("undefined") ?  "" :  testCaseName;
        this.projectName = projectName.equals("undefined") ? "" : projectName;
        if (status.equals("all")) whereByStatus = " ";
        if (status.equals("failed")) whereByStatus = " and passed_actions < all_actions ";
        if (status.equals("passed")) whereByStatus = " and passed_actions = all_actions ";
    }

}
