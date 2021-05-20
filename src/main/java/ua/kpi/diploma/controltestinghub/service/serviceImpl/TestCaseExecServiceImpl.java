package ua.kpi.diploma.controltestinghub.service.serviceImpl;
import org.springframework.stereotype.Service;
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


    @Override
    public List<TestCaseExecution> getAllTestCaseExecutions() {
        return null;
    }

    @Override
    public Integer countTestCaseExecutions(String testCaseName, String projectName, String status) {
        return null;
    }

    @Override
    public List<TestCaseExecutionDto> getAllTestCaseExecutionWithFailedActionNumber(Integer limit, Integer offset, String orderBy, String orderByClause, String testCaseName, String projectName, String status) {
        return null;
    }

    @Override
    public Long createTestCaseExecution(Integer testCaseId, Integer userId) {
        return null;
    }

    @Override
    public void updateTestCaseExecution(String status, Integer testCaseExecutionId) {

    }

    @Override
    public List<TestCaseExecutionsCountsByStartDatesDto> getExecutionsByDatesForLastDays(Integer daysFromToday) {
        return null;
    }

    @Override
    public List<GroupedTestCaseExecutionDto> getGroupedTestCaseExecution() {
        return null;
    }
}
