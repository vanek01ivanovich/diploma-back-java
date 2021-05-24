package ua.kpi.diploma.controltestinghub.service;

import ua.kpi.diploma.controltestinghub.dto.UserSearchDto;
import ua.kpi.diploma.controltestinghub.exception.ValidationException;
import ua.kpi.diploma.controltestinghub.model.User;
import ua.kpi.diploma.controltestinghub.util.Pageable;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User getUserByEmail(String email);

    void updateUserById(User user);

    Integer countPages(Integer pageSize);

    List<User> getUsers(UserSearchDto userSearchDto, Pageable pageable) throws ValidationException;

    User getUserById(Integer id);

    Integer getUserIdByEmail(String email);

    void updateUserPasswordByToken(String token,String password);

    void updateUserSettings(User user);

    void updateUserPassword(User user);

    Integer countPagesSearch(UserSearchDto userSearchDto, Integer pageSize);

    Boolean checkIfEmailExists(String email);
}
