package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.kpi.diploma.controltestinghub.model.TestScenario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestScenarioWithIdNameArchivedMapper implements RowMapper<TestScenario> {

    @Override
    public TestScenario mapRow(ResultSet rs, int i) throws SQLException {
        return new TestScenario(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getBoolean("is_archived")
        );
    }
}
