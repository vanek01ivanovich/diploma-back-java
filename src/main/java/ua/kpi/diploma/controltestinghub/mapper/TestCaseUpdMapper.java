package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.model.TestCaseUpd;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TestCaseUpdMapper implements RowMapper<TestCaseUpd> {
    @Override
    public TestCaseUpd mapRow(ResultSet rs, int i) throws SQLException {
                return TestCaseUpd.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name")).build();

    }
}