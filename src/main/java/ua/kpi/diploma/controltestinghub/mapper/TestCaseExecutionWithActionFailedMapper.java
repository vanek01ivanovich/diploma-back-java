package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.dto.TestCaseExecutionDto;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TestCaseExecutionWithActionFailedMapper implements RowMapper<TestCaseExecutionDto> {
    @Override
    public TestCaseExecutionDto mapRow(ResultSet rs, int i) throws SQLException {
        return new TestCaseExecutionDto(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getString(6),
                rs.getString(7),
                rs.getInt(8),
                rs.getInt(9)
        );
    }
}
