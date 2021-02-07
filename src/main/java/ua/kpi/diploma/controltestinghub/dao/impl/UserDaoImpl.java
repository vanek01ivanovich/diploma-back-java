package ua.kpi.diploma.controltestinghub.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.kpi.diploma.controltestinghub.dao.UserDao;
import ua.kpi.diploma.controltestinghub.model.User;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }
}
