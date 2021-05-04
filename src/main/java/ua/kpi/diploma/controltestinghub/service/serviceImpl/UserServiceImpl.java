package ua.kpi.diploma.controltestinghub.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dao.UserDao;
import ua.kpi.diploma.controltestinghub.model.User;
import ua.kpi.diploma.controltestinghub.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveUser(User user) {
        User newUser = User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .surname(user.getSurname())
                .isEnabled(true)
                .role(user.getRole())
                .build();
        userDao.saveUser(newUser);

    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public void updateUserById(User user) {
        userDao.updateUserById(user);
    }

    @Override
    public Integer countPages(Integer pageSize) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.findUserById(id).orElseThrow(() -> new RuntimeException("user with id " + id + " not found"));
    }

    @Override
    public Integer getUserIdByEmail(String email) {
        return userDao.getUserIdByEmail(email);
    }

    @Override
    public void updateUserPasswordByToken(String token, String password) {

    }

    @Override
    public void updateUserSettings(User user) {
        userDao.updateUserSettings(user);
    }

    @Override
    public void updateUserPassword(User user) {
        userDao.updateUserSettings(user);
    }

    @Override
    public Integer countPagesSearch() {
        return null;
    }

    @Override
    public Boolean checkIfEmailExists(String email) {
        return userDao.checkIfEmailExists(email);
    }
}
