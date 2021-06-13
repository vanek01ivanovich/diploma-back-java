package ua.kpi.diploma.controltestinghub.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dao.UserDao;
import ua.kpi.diploma.controltestinghub.dto.UserSearchDto;
import ua.kpi.diploma.controltestinghub.exception.ValidationException;
import ua.kpi.diploma.controltestinghub.model.User;
import ua.kpi.diploma.controltestinghub.service.UserService;
import ua.kpi.diploma.controltestinghub.util.Pageable;
import ua.kpi.diploma.controltestinghub.util.Pagination;
import ua.kpi.diploma.controltestinghub.util.PasswordToken;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final Pagination pagination;
    private final List<String> USER_TABLE_FIELDS = Arrays.asList("id", "name", "surname", "role", "email", "is_enabled");

    @Autowired
    public UserServiceImpl(Pagination pagination, UserDao userDao, PasswordEncoder passwordEncoder) {
        this.pagination = pagination;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
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
        return pagination.countPages(userDao.countUsers(), pageSize);
    }

    @Override
    public List<User> getUsers(UserSearchDto userSearchDto, Pageable pageable) throws ValidationException {
        pagination.validate(pageable, USER_TABLE_FIELDS);
        return userDao.getUsersPageSorted(pagination.formSqlPostgresPaginationPiece(pageable),
                userSearchDto.getOnlyEnabled() ? " and is_enabled=true " : "",
                userSearchDto.getName(),
                userSearchDto.getSurname(),
                userSearchDto.getEmail(),
                formFilter(userSearchDto.getRoles()));
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
        PasswordToken passwordResetToken = new PasswordToken();
        String resolvedToken = passwordResetToken.resolveToken(token);
        String email = passwordResetToken.getEmailFromResetToken(resolvedToken);
        userDao.updateUserPassword(email, passwordEncoder.encode(password));
    }

    @Override
    public void updateUserSettings(User user) {
        userDao.updateUserSettings(user);
    }

    @Override
    public void updateUserPassword(User user) {
        userDao.updateUserPassword(user.getEmail(), passwordEncoder.encode(user.getPassword()));
    }

    @Override
    public Integer countPagesSearch(UserSearchDto userSearchDto, Integer pageSize) {
        System.out.println(userSearchDto.toString());
        return pagination.countPages(userDao.countUsersSearch(
                userSearchDto.getOnlyEnabled() ? " and is_enabled=true" : "",
                userSearchDto.getName(),
                userSearchDto.getSurname(),
                userSearchDto.getEmail(),
                formFilter(userSearchDto.getRoles())), pageSize);
    }
    @Override
    public Boolean checkIfEmailExists(String email) {
        return userDao.checkIfEmailExists(email);
    }

    private String formFilter(List<String> roles) {
        if (roles.isEmpty()) {
            return "%";
        }
        StringBuilder sb = new StringBuilder("(");
        roles.forEach(r -> sb.append(r).append("|"));
        return sb.deleteCharAt(sb.length() - 1).append(")").toString();
    }

}
