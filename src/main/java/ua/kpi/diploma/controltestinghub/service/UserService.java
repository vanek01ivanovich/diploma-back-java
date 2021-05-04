package ua.kpi.diploma.controltestinghub.service;

import ua.kpi.diploma.controltestinghub.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User getUserByEmail(String email);

    void updateUserById(User user);

    Integer countPages(Integer pageSize);

    List<User> getUsers();

    User getUserById(Integer id);

    Integer getUserIdByEmail(String email);

    void updateUserPasswordByToken(String token,String password);

    void updateUserSettings(User user);

    void updateUserPassword(User user);

    Integer countPagesSearch();

    Boolean checkIfEmailExists(String email);
}
