package ua.kpi.diploma.controltestinghub.service.serviceImpl;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dao.TestCaseExecutionDao;
import ua.kpi.diploma.controltestinghub.dto.GroupedTestCaseExecutionDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseExecutionDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseExecutionsCountsByStartDatesDto;
import ua.kpi.diploma.controltestinghub.model.TestCaseExecution;
import ua.kpi.diploma.controltestinghub.service.TestCaseExecService;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Service
public class TestCaseExecServiceImpl implements TestCaseExecService {

    private final TestCaseExecutionDao testCaseExecutionDao;
    private String whereByStatus = "";
    private String testCaseName = "";
    private String projectName = "";

    public TestCaseExecServiceImpl(TestCaseExecutionDao testCaseExecutionDao) {
        this.testCaseExecutionDao = testCaseExecutionDao;
    }

    @Override
    public List<TestCaseExecution> getAllTestCaseExecutions() {
        return testCaseExecutionDao.getAllTestCaseExecutions();
    }

    @Override
    public Integer countTestCaseExecutions(String testCaseName, String projectName, String status) {
        filterTestCase(testCaseName, projectName, status);
        return testCaseExecutionDao.countTestCaseExecutions(this.testCaseName, this.projectName, whereByStatus);
    }

    @Override
    public List<TestCaseExecutionDto> getAllTestCaseExecutionWithFailedActionNumber(Integer limit, Integer offset, String orderBy, String orderByClause, String testCaseName, String projectName, String status) {
        filterTestCase(testCaseName, projectName, status);
        return testCaseExecutionDao.getAllTestCaseExecutionWithFailedActionNumber(limit, offset, orderBy, orderByClause, this.testCaseName, this.projectName, status, whereByStatus);
    }

    @Override
    public Integer createTestCaseExecution(Integer testCaseId, Integer userId) {
        return testCaseExecutionDao.createTestCaseExecution(testCaseId, userId);
    }

    @Override
    public void updateTestCaseExecution(String status, Integer testCaseExecutionId) {
        testCaseExecutionDao.updateTestCaseExecution(status, testCaseExecutionId);
    }

    @Override
    public List<TestCaseExecutionsCountsByStartDatesDto> getExecutionsByDatesForLastDays(Integer daysFromToday){
        LocalDate today = LocalDate.now();
        LocalDate dateToCountFrom = today.minusDays(daysFromToday);
        return testCaseExecutionDao.getExecutionsByStartDate(Date.valueOf(dateToCountFrom), Date.valueOf(today));
    }

    /**
     * Method needed for chart on dashboard
     * @return list of GroupedTestCaseExecutionDto
     */
    @Override
    public List<GroupedTestCaseExecutionDto> getGroupedTestCaseExecution() {
        return testCaseExecutionDao.getGroupedTestCaseExecution();
    }

    private void filterTestCase(String testCaseName, String projectName, String status) {
        this.testCaseName = testCaseName.equals("undefined") ?  "" :  testCaseName;
        this.projectName = projectName.equals("undefined") ? "" : projectName;
        if (status.equals("all")) whereByStatus = " ";
        if (status.equals("failed")) whereByStatus = " and passed_actions < all_actions ";
        if (status.equals("passed")) whereByStatus = " and passed_actions = all_actions ";
    }

}
