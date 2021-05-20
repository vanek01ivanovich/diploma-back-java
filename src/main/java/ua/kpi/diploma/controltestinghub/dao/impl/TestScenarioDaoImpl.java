package ua.kpi.diploma.controltestinghub.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.kpi.diploma.controltestinghub.dao.TestScenarioDao;
import ua.kpi.diploma.controltestinghub.dto.TestScenarioDto;
import ua.kpi.diploma.controltestinghub.mapper.TestScenarioMapper;
import ua.kpi.diploma.controltestinghub.mapper.TestScenarioWithIdNameArchivedMapper;
import ua.kpi.diploma.controltestinghub.model.TestScenario;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class TestScenarioDaoImpl implements TestScenarioDao {

    private final JdbcTemplate jdbcTemplate;
    private final TestScenarioMapper testScenarioMapper;

    @Value("${insert.test.scenario}")
    private String INSERT_TEST_SCENARIO;

    @Value("${update.test.scenario.by.id}")
    private String UPDATE_TEST_SCENARIO_BY_ID;

    @Value("${get.test.scenarios}")
    private String GET_ALL;

    @Value("${get.test.scenario.by.id}")
    private String GET_TEST_SCENARIO_BY_ID;

    @Value("${get.test.scenario.page}")
    private String GET_PAGE;

    @Value("${count.test.scenario}")
    private String COUNT_TEST_SCENARIO;


    @Value("${select.test.scenario.exist}")
    private String CHECK_EXIST_TEST_SCENARIO_BY_NAME;

    @Autowired
    public TestScenarioDaoImpl(JdbcTemplate jdbcTemplate, TestScenarioMapper testScenarioMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.testScenarioMapper = testScenarioMapper;
    }

    @Override
    public void updateTestScenarioById(TestScenario testScenario) {
        jdbcTemplate.update(
                UPDATE_TEST_SCENARIO_BY_ID,
                testScenario.getName(),
                testScenario.isArchived(),
                testScenario.getId()
        );
    }

    @Override
    public Integer saveTestScenario(TestScenarioDto testScenarioDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_TEST_SCENARIO, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, testScenarioDto.getName());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public boolean checkExistTestScenarioByName(String name) {
        return Objects.requireNonNull(jdbcTemplate.queryForObject(CHECK_EXIST_TEST_SCENARIO_BY_NAME, Boolean.class, name));
    }

    @Override
    public List<TestScenario> getTestScenarioById(Integer id) {
        RowMapper<TestScenario> mapper =
                new TestScenarioWithIdNameArchivedMapper();
        return jdbcTemplate.query(GET_TEST_SCENARIO_BY_ID, mapper, id);
    }

    @Override
    public List<TestScenario> getAll() {
        return jdbcTemplate.queryForStream(GET_ALL, testScenarioMapper).collect(Collectors.toList());
    }

    @Override
    public List<TestScenario> getTestScenariosPageSorted(String orderByLimitOffsetWithValues, String name) {
        return jdbcTemplate.queryForStream(GET_PAGE + orderByLimitOffsetWithValues,
                testScenarioMapper, name)
                .collect(Collectors.toList());
    }
    @Override
    public Integer countUsers() {
        return jdbcTemplate.queryForObject(COUNT_TEST_SCENARIO, Integer.class);
    }
}
