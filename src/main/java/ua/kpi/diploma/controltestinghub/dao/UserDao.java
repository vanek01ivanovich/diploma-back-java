package ua.kpi.diploma.controltestinghub.dao;

import ua.kpi.diploma.controltestinghub.model.User;

public interface UserDao {
    User findUserByEmail(String email);
}
