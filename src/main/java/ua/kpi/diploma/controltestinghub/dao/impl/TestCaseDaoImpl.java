package ua.kpi.diploma.controltestinghub.dao.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.kpi.diploma.controltestinghub.dao.TestCaseDao;
import ua.kpi.diploma.controltestinghub.dto.TestCaseWithUserDto;
import ua.kpi.diploma.controltestinghub.mapper.*;
import ua.kpi.diploma.controltestinghub.model.TestCase;
import ua.kpi.diploma.controltestinghub.model.TestCaseStep;
import ua.kpi.diploma.controltestinghub.model.TestCaseTopSubscribed;
import ua.kpi.diploma.controltestinghub.model.TestCaseUpd;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TestCaseDaoImpl implements TestCaseDao {

    private final JdbcTemplate jdbcTemplate;
    private final TestCaseStepMapper testCaseStepMapper;
    private final TestCaseTopSubscribedMapper testCaseTopSubscribedMapper;
    private final TestCaseUpdMapper testCaseUpdMapper;
    private final TestCaseNameMapper testCaseNameMapper;
    private final TestCaseDtoWithUserMapper testCaseDtoWithUserMapper;

    public TestCaseDaoImpl(JdbcTemplate jdbcTemplate, TestCaseStepMapper testCaseStepMapper, TestCaseTopSubscribedMapper testCaseTopSubscribedMapper,
                           TestCaseUpdMapper testCaseUpdMapper,
                           TestCaseNameMapper testCaseNameMapper,
                           TestCaseDtoWithUserMapper testCaseDtoWithUserMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.testCaseStepMapper = testCaseStepMapper;
        this.testCaseTopSubscribedMapper = testCaseTopSubscribedMapper;
        this.testCaseUpdMapper = testCaseUpdMapper;
        this.testCaseNameMapper = testCaseNameMapper;
        this.testCaseDtoWithUserMapper = testCaseDtoWithUserMapper;
    }

    @Value("${insert.test.case}")
    public String INSERT;

    @Value("${get.test.case.steps}")
    private String GET_TEST_CASE_STEPS;

    @Value("select id, name from \"test_case\"")
    public String GET_ALL;

    @Value("${get.test.case.page}")
    public String GET_PAGE;

    @Value("${get.test.case.with.user.creator}")
    public String GET_PAGE_TEST_CASE_DTO;

    @Value("${count.test.cases}")
    public String COUNT_TEST_CASES;

    @Value("${update.test.case.name}")
    public String UPDATE_TEST_CASE_NAME;

    @Value("${insert.subscription}")
    public String INSERT_SUBSCRIBER;

    @Value("${delete.subscription}")
    public String DELETE_SUBSCRIBER;

    @Value("${exist.subscription}")
    public String SUBSCRIPTION_EXISTS;

    @Value("${dashboard.top.subscribed.test.cases}")
    public String GET_TOP_FIVE_SUBSCRIBED_TEST_CASES;

    @Value("${get.test.case.by.id}")
    private String GET_TEST_CASE_BY_ID;

    @Value("${update.archive.test.case}")
    private String UPDATE_IS_ARCHIVED;

    /**
     * @return created test_case_id
     */
    @Override
    public Integer insert(TestCase testCase) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT, new String[]{"id"});
            ps.setString(1, testCase.getName());
            ps.setLong(2, testCase.getUserId());
            ps.setLong(3, testCase.getProjectId());
            ps.setLong(4, testCase.getDataSetId());
            ps.setLong(5, testCase.getTestScenarioId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public List<TestCaseStep> getTestCaseSteps(Integer testCaseId) {
        return jdbcTemplate.queryForStream(GET_TEST_CASE_STEPS, testCaseStepMapper, testCaseId).collect(Collectors.toList());
    }

    @Override
    public List<TestCaseUpd> getTestCases() {
        return jdbcTemplate.queryForStream(GET_ALL, testCaseUpdMapper).collect(Collectors.toList());
    }

    @Override
    public List<TestCaseWithUserDto> getTestCasesWithUserPageSorted(Integer projectID, String orderByLimitOffsetWithValues, String name) {
        return jdbcTemplate.queryForStream(GET_PAGE_TEST_CASE_DTO + orderByLimitOffsetWithValues,
                testCaseDtoWithUserMapper,projectID,name)
                .collect(Collectors.toList());
    }



    @Override
    public List<TestCaseTopSubscribed> getTopFiveSubscribedTestCases() {
        return jdbcTemplate.query(GET_TOP_FIVE_SUBSCRIBED_TEST_CASES,
                testCaseTopSubscribedMapper);
    }

    @Override
    public Integer countTestCasesByProject(Integer projectId) {
        return jdbcTemplate.queryForObject(COUNT_TEST_CASES, Integer.class, projectId);
    }

    @Override
    public void update(Integer testCaseId, String newTestCaseName) {
        jdbcTemplate.update(UPDATE_TEST_CASE_NAME, newTestCaseName, testCaseId);
    }

    @Override
    public void addSubscriber(Integer testCaseId, Integer userId) {
        jdbcTemplate.update(INSERT_SUBSCRIBER, userId, testCaseId);
    }

    @Override
    public Boolean isFollowedByUser(Integer testCaseId, Integer userId) {
        return jdbcTemplate.queryForObject(SUBSCRIPTION_EXISTS, Boolean.class, testCaseId, userId);
    }

    @Override
    public void removeSubscriber(Integer testCaseId, Integer userId) {
        jdbcTemplate.update(DELETE_SUBSCRIBER, testCaseId, userId);
    }

    @Override
    public TestCase getTestCaseById(Integer testCaseId) {
        return jdbcTemplate.queryForObject(GET_TEST_CASE_BY_ID, testCaseNameMapper, testCaseId);
    }

    @Override
    public void updateIsArchivedField(Integer projectId, boolean isArchived) {
        jdbcTemplate.update(UPDATE_IS_ARCHIVED, isArchived, projectId);
    }

}
