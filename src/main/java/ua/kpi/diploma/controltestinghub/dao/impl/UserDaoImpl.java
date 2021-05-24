package ua.kpi.diploma.controltestinghub.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.kpi.diploma.controltestinghub.dao.UserDao;
import ua.kpi.diploma.controltestinghub.dto.UserCountDto;
import ua.kpi.diploma.controltestinghub.mapper.UserCountMapper;
import ua.kpi.diploma.controltestinghub.mapper.UserMapper;
import ua.kpi.diploma.controltestinghub.mapper.UserMapperNoPassword;
import ua.kpi.diploma.controltestinghub.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PropertySource("classpath:queriesDB/queries.properties")
@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;
    private final UserMapperNoPassword userMapperNoPassword;
    private final UserCountMapper userCountMapper;


    @Value("${find.user.by.email.with.password}")
    private String FIND_USER_BY_EMAIL;
    @Value("${find.user.by.email.with.password}")
    private String FIND_USER_BY_EMAIL_WITH_PASSWORD;
    @Value("${find.user.by.id}")
    private String FIND_USER_BY_ID;
    @Value("${update.user.by.id}")
    private String UPDATE_USER_BY_ID;
    @Value("${count.users}")
    private String COUNT_USERS;
    @Value("${get.users}")
    private String GET_USERS;
    @Value("${count.users.search}")
    private String COUNT_USERS_SEARCH;
    @Value("${insert.user}")
    private String INSERT_USER;
    @Value("${get.user.email.by.id}")
    private String GET_USER_EMAIL_BY_ID;
    @Value("${update.user.password}")
    private String UPDATE_USER_PASS;
    @Value("${update.user.settings}")
    private String UPDATE_SETTINGS;
    @Value("${get.user.id.by.email}")
    private String GET_USER_ID_BY_EMAIL;
    @Value("${count.users.by.role}")
    private String COUNT_BY_ROLE;
    @Value("${check.if.user.email.exists}")
    private String CHECK_IF_EMAIL_EXISTS;
    @Value("${get.user.count}")
    private String COUNT_USERS_BY_ROLE;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate, UserMapper userMapper, UserMapperNoPassword userMapperNoPassword, UserCountMapper userCountMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
        this.userMapperNoPassword = userMapperNoPassword;
        this.userCountMapper = userCountMapper;
    }

    @Override
    public User findUserByEmail(String email) {
        return jdbcTemplate.queryForObject(FIND_USER_BY_EMAIL, userMapper, email);
    }

    @Override
    public String getEmail(Integer id) {
        return jdbcTemplate.queryForObject(GET_USER_EMAIL_BY_ID, String.class, id);
    }

    @Override
    public void saveUser(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        String name = user.getName();
        String surname = user.getSurname();
        String role = user.getRole();
        boolean enabled = user.isEnabled();
        jdbcTemplate.update(INSERT_USER, email, password, name, surname, role, enabled);

    }

    @Override
    public Optional<User> findUserById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_USER_BY_ID, userMapperNoPassword, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> getUsersPageSorted(String orderByLimitOffsetWithValues, String isEnabledFiltering, String name, String surname, String email, String roles) {
        return jdbcTemplate.queryForStream(GET_USERS + isEnabledFiltering + orderByLimitOffsetWithValues,
                userMapperNoPassword, name, surname, email, roles)
                .collect(Collectors.toList());
    }

    @Override
    public void updateUserById(User user) {
        jdbcTemplate.update(UPDATE_USER_BY_ID,user.getEmail(), user.getName(), user.getSurname(), user.getRole(), user.isEnabled(), user.getId());
    }

    @Override
    public Integer countUsers() {
        return jdbcTemplate.queryForObject(COUNT_USERS, Integer.class);
    }

    @Override
    public void updateUserPassword(String email, String password) {
        jdbcTemplate.update(UPDATE_USER_PASS, password, email);
    }

    @Override
    public void updateUserSettings(User user) {
        jdbcTemplate.update(UPDATE_SETTINGS, user.getName(), user.getSurname(), user.getEmail());
    }

    @Override
    public Integer getUserIdByEmail(String email) {
        return jdbcTemplate.queryForObject(GET_USER_ID_BY_EMAIL, Integer.class, email);
    }

    @Override
    public Integer countUsersSearch(String enabledSql, String name, String surname, String email, String roles) {
        return jdbcTemplate.queryForObject(COUNT_USERS_SEARCH + enabledSql, Integer.class, name, surname, email, roles);
    }

    @Override
    public UserCountDto countOfUsersByRole() {
        return jdbcTemplate.queryForObject(COUNT_USERS_BY_ROLE, userCountMapper);
    }

    @Override
    public Boolean checkIfEmailExists(String email) {
        Integer temp = jdbcTemplate.queryForObject(CHECK_IF_EMAIL_EXISTS, Integer.class, email);
        return temp !=null && temp != 0;
    }
}
