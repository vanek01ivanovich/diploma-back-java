package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapperNoPassword implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return User.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .role(resultSet.getString("role"))
                .isEnabled(resultSet.getBoolean("is_enabled"))
                .build();
    }
}
