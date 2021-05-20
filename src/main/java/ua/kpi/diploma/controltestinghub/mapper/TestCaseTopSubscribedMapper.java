package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.model.TestCaseTopSubscribed;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TestCaseTopSubscribedMapper implements RowMapper<TestCaseTopSubscribed> {

    @Override
    public TestCaseTopSubscribed mapRow(ResultSet rs, int i) throws SQLException {
        return new TestCaseTopSubscribed(
                rs.getInt("test_case_id"),
                rs.getString("test_case_name"),
                rs.getInt("project_id"),
                rs.getString("project_name"),
                rs.getInt("quantity")
        );
    }
}
