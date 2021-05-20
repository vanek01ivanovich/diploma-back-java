package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.dto.UserCountDto;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserCountMapper implements RowMapper<UserCountDto> {
    @Override
    public UserCountDto mapRow(ResultSet rs, int i) throws SQLException {
        return UserCountDto.builder()
                .userCount(rs.getInt("user_count"))
                .adminCount(rs.getInt("admin_count"))
                .managerCount(rs.getInt("manager_count"))
                .engineerCount(rs.getInt("engineer_count"))
                .build();

    }
}
