package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.model.TestCase;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TestCaseNameMapper implements RowMapper<TestCase> {
    @Override
    public TestCase mapRow(ResultSet resultSet, int i) throws SQLException {
        return TestCase.builder()
                .name(resultSet.getString(1))
                .build();
    }
}
