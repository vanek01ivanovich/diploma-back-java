package ua.kpi.diploma.controltestinghub.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.kpi.diploma.controltestinghub.dao.VariableValueDao;
import ua.kpi.diploma.controltestinghub.model.VariableValue;

import java.util.List;

@Repository
@PropertySource("classpath:queriesDB/queries.properties")
public class VariableValueDaoImpl implements VariableValueDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VariableValueDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${insert.variable.value}")
    public String INSERT;


    @Value("${update.variable.value.data.entry}")
    public String UPDATE_DATA_ENTRY;

    @Override
    public void insert(List<VariableValue> variableValues, Integer testCaseId) {
        jdbcTemplate.batchUpdate(INSERT, variableValues, variableValues.size(), (ps, variableValue) -> {
            ps.setInt(1, variableValue.getVariableId());
            ps.setInt(2, variableValue.getActionInstanceId());
            ps.setInt(3, variableValue.getDataEntryId());
            ps.setInt(4, testCaseId);
        });
    }

    @Override
    public void updateDataEntry(List<VariableValue> variableValues) {
        jdbcTemplate.batchUpdate(UPDATE_DATA_ENTRY, variableValues, variableValues.size(), (ps, variableValue) -> {
            ps.setInt(1, variableValue.getDataEntryId());
            ps.setInt(2, variableValue.getId());
        });
    }
}
