package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.dto.NotificationDto;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class NotificationMapper implements RowMapper<NotificationDto> {
    @Override
    public NotificationDto mapRow(ResultSet resultSet, int i) throws SQLException {
        return NotificationDto.builder()
                .status(resultSet.getString(1))
                .startTime(resultSet.getString(2))
                .name(resultSet.getString(3))
                .id(resultSet.getInt(4))
                .build();
    }
}
