package ua.kpi.diploma.controltestinghub.dao;

import ua.kpi.diploma.controltestinghub.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    User findUserByEmail(String email);

    String getEmail(Long id);

    void saveUser(User user);

    Optional<User> findUserById(Integer id);

    List<User> getUsersPageSorted(String orderByLimitOffsetWithValues, String isEnabledFiltering, User user);

    void updateUserById(User user);

    Integer countUsers();

    void updateUserPassword(String email, String password);

    void updateUserSettings(User user);

    Integer getUserIdByEmail(String email);

    Integer countUsersSearch(String enabledSql, String name, String surname, String email, String formFilter);


    Boolean checkIfEmailExists(String email);
}
