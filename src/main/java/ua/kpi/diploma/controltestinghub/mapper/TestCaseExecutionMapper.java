package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.model.TestCaseExecution;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TestCaseExecutionMapper implements RowMapper<TestCaseExecution> {
    @Override
    public TestCaseExecution mapRow(ResultSet rs, int i) throws SQLException {
        return new TestCaseExecution(
                rs.getInt(1),
                rs.getInt(2),
                rs.getString(3),
                //rs.getTimestamp(4),
                rs.getString(4),
                //rs.getTimestamp(5),
                rs.getString(5),
                rs.getInt(6)
        );
    }
}
