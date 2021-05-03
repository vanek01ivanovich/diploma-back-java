package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.model.User;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        Integer id = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String name = resultSet.getString("email");
        String surname = resultSet.getString("email");
        String password = resultSet.getString("email");
        String role = resultSet.getString("email");
        boolean isEnabled = resultSet.getBoolean("is_enabled");

        return User.builder()
                .id(id)
                .email(email)
                .name(name)
                .surname(surname)
                .password(password)
                .role(role)
                .isEnabled(isEnabled)
                .build();
    }
}
