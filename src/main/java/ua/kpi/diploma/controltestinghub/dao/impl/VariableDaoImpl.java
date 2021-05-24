package ua.kpi.diploma.controltestinghub.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.kpi.diploma.controltestinghub.dao.VariableDao;
import java.util.List;

@Repository
public class VariableDaoImpl implements VariableDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public VariableDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createVariables(Integer actionId, List<String> names) {
        String sql = "insert into variable (name, action_id) values (?, ?)";
        jdbcTemplate.batchUpdate(sql, names, names.size(), (ps, name) -> {
            ps.setString(1, name);
            ps.setLong(2, actionId);
        });
    }
}
