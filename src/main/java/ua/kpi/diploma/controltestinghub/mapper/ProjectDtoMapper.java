package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.dto.ProjectDto;
import ua.kpi.diploma.controltestinghub.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProjectDtoMapper implements RowMapper<ProjectDto> {

    @Override
    public ProjectDto mapRow(ResultSet resultSet, int i) throws SQLException {
        return ProjectDto.builder()
                .id(resultSet.getInt("id"))
                .link(resultSet.getString("link"))
                .name(resultSet.getString("name"))
                .isArchived(resultSet.getBoolean("is_archived"))
                .user(User.builder()
                        .id(resultSet.getInt("user_id"))
                        .role(resultSet.getString("user_role"))
                        .isEnabled(resultSet.getBoolean("user_is_enabled"))
                        .email(resultSet.getString("user_email"))
                        .surname(resultSet.getString("user_surname"))
                        .name(resultSet.getString("user_name"))
                        .build())
                .build();
    }
}
