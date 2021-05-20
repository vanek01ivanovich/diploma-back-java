package ua.kpi.diploma.controltestinghub.dao.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.kpi.diploma.controltestinghub.dao.TestCaseExecutionDao;
import ua.kpi.diploma.controltestinghub.dto.GroupedTestCaseExecutionDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseExecutionDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseExecutionsCountsByStartDatesDto;
import ua.kpi.diploma.controltestinghub.mapper.*;
import ua.kpi.diploma.controltestinghub.model.TestCaseExecution;
import ua.kpi.diploma.controltestinghub.model.TestCaseExecutionStatus;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class TestCaseExecutionDaoImpl implements TestCaseExecutionDao {
    @Value("${get.page.test.case.executions}")
    public String GET_PAGE_TEST_CASE_EXECUTION;

    @Value("${count.test.case.executions.search}")
    public String COUNT_TEST_CASE_EXECUTIONS;

    @Value("${select.test.case.executions.group.by.creation.date}")
    public String GET_EXECUTIONS_GROUP_BY_START_DATE;

    @Value("${dashboard.grouped.number.of.test.case.execution}")
    private String GET_GROUPED_TEST_CASE_EXECUTIONS;

    @Value("${get.all.test.case.execution}")
    private String GET_ALL_TEST_CASE_EXECUTION;

    @Value("${create.test.case.execution}")
    private String CREATE_TEST_CASE_EXECUTION;

    @Value("${update.test.case.execution}")
    private String UPDATE_TEST_CASE_EXECUTION;

    @Value("${get.test.case.execution.by.id}")
    private String GET_EXECUTION_BY_ID;

    private final JdbcTemplate jdbcTemplate;
    private final TestCaseExecutionMapper testCaseExecutionMapper;
    private final TestCaseExecutionWithActionFailedMapper testCaseExecutionWithActionFailedMapper;
    private final TestCaseExecutionsCountsByStartDatesMapper testCaseExecutionsCountsByStartDatesMapper;
    private final GroupedTestCaseExecutionMapper groupedTestCaseExecutionMapper;
    private final TestCaseExecutionStatusStartDateMapper testCaseExecutionStatusStartDateMapper;

    public TestCaseExecutionDaoImpl(JdbcTemplate jdbcTemplate,
                                    TestCaseExecutionMapper testCaseExecutionMapper,
                                    TestCaseExecutionWithActionFailedMapper testCaseExecutionWithActionFailedMapper,
                                    TestCaseExecutionsCountsByStartDatesMapper testCaseExecutionsCountsByStartDatesMapper,
                                    GroupedTestCaseExecutionMapper groupedTestCaseExecutionMapper,
                                    TestCaseExecutionStatusStartDateMapper testCaseExecutionStatusStartDateMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.testCaseExecutionMapper = testCaseExecutionMapper;
        this.testCaseExecutionWithActionFailedMapper = testCaseExecutionWithActionFailedMapper;
        this.testCaseExecutionsCountsByStartDatesMapper = testCaseExecutionsCountsByStartDatesMapper;
        this.groupedTestCaseExecutionMapper = groupedTestCaseExecutionMapper;
        this.testCaseExecutionStatusStartDateMapper = testCaseExecutionStatusStartDateMapper;
    }

    @Override
    public List<TestCaseExecution> getAllTestCaseExecutions() {
        return jdbcTemplate.query(GET_ALL_TEST_CASE_EXECUTION, testCaseExecutionMapper);
    }

    @Override
    public Integer countTestCaseExecutions(String testCaseName, String projectName, String whereByStatus) {
        String sql = COUNT_TEST_CASE_EXECUTIONS + whereByStatus;
        return jdbcTemplate.queryForObject(sql, Integer.class,testCaseName + "%", projectName + "%");
    }

    @Override
    public List<TestCaseExecutionDto> getAllTestCaseExecutionWithFailedActionNumber(Integer limit, Integer offset, String orderBy, String orderByClause, String testCaseName, String projectName, String status, String whereByStatus) {

        String sql = GET_PAGE_TEST_CASE_EXECUTION + whereByStatus + " order by " + orderBy + " " + orderByClause + " limit " + limit + " offset " + offset;
        return jdbcTemplate.query(sql, testCaseExecutionWithActionFailedMapper, testCaseName + "%", projectName + "%");
    }

    @Override
    public Integer createTestCaseExecution(Integer testCaseId, Integer userId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_TEST_CASE_EXECUTION, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,testCaseId);
            ps.setString(2, String.valueOf(TestCaseExecutionStatus.IN_PROGRESS));
            ps.setLong(3,userId);
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void updateTestCaseExecution(String status, Integer testCaseExecutionId) {
        jdbcTemplate.update(UPDATE_TEST_CASE_EXECUTION, status, testCaseExecutionId);
    }

    @Override
    public List<TestCaseExecutionsCountsByStartDatesDto> getExecutionsByStartDate(Date fromDate, Date tillDate){
        return jdbcTemplate.queryForStream(GET_EXECUTIONS_GROUP_BY_START_DATE, testCaseExecutionsCountsByStartDatesMapper, fromDate, tillDate)
                .collect(Collectors.toList());
    }

    /**
     * Mehtod needed for chart on dashboard
     * @return list of grouped test case executions
     */
    @Override
    public List<GroupedTestCaseExecutionDto> getGroupedTestCaseExecution() {
        return jdbcTemplate.queryForStream(GET_GROUPED_TEST_CASE_EXECUTIONS,groupedTestCaseExecutionMapper).collect(Collectors.toList());
    }

    @Override
    public TestCaseExecution getTestCaseExecutionById(Integer testCaseExecutionId) {
        return jdbcTemplate.queryForObject(GET_EXECUTION_BY_ID, testCaseExecutionStatusStartDateMapper, testCaseExecutionId);
    }
}
