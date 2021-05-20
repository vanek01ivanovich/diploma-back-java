package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.model.TestCaseExecution;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TestCaseExecutionStatusStartDateMapper implements RowMapper<TestCaseExecution> {
    @Override
    public TestCaseExecution mapRow(ResultSet resultSet, int i) throws SQLException {
        return TestCaseExecution.builder()
                .status(resultSet.getString(1))
                .startDateTime(resultSet.getString(2))
                .build();
    }
}
