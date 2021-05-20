package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.dto.TestCaseWithUserDto;
import ua.kpi.diploma.controltestinghub.model.TestCaseUpd;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TestCaseDtoWithUserMapper implements RowMapper<TestCaseWithUserDto> {
    @Override
    public TestCaseWithUserDto mapRow(ResultSet rs, int i) throws SQLException {
        return TestCaseWithUserDto.builder()
                .testCaseUpd(TestCaseUpd.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build())
                .email(rs.getString("email"))
                .build();

    }
}
