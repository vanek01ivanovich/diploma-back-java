package ua.kpi.diploma.controltestinghub.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.kpi.diploma.controltestinghub.dao.CompoundInstanceDao;
import ua.kpi.diploma.controltestinghub.dto.TestScenarioItemDto;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class CompoundInstanceDaoImpl implements CompoundInstanceDao {

    private final JdbcTemplate jdbcTemplate;

    @Value("${insert.compound.instance}")
    private String INSERT_AND_GET_GENERATED_ID;

    @Autowired
    public CompoundInstanceDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer saveCompoundInstanceAndGetGeneratedId(TestScenarioItemDto dto, Integer testScenarioId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_AND_GET_GENERATED_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, dto.getId());
            ps.setLong(2, testScenarioId);
            ps.setLong(3, dto.getPriority());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }
}
