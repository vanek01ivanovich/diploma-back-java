package ua.kpi.diploma.controltestinghub.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.kpi.diploma.controltestinghub.dao.ReportDao;
import ua.kpi.diploma.controltestinghub.dto.SubscribedUserTestCaseDto;
import ua.kpi.diploma.controltestinghub.mapper.SubscribedUserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReportDaoImpl implements ReportDao {

    private final JdbcTemplate jdbcTemplate;
    private final SubscribedUserMapper subscribedUserMapper;

    @Value("${get.subscribed.users}")
    private String GET_SUBSCRIBED_USERS;

    @Autowired
    public ReportDaoImpl(JdbcTemplate jdbcTemplate,SubscribedUserMapper subscribedUserMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.subscribedUserMapper = subscribedUserMapper;
    }


    /**
     * @param testCaseExecutionId needed for getting data from DB by id
     * @return list of subscribed users
     */
    @Override
    public List<SubscribedUserTestCaseDto> getSubscribedUsers(Integer testCaseExecutionId) {
        return jdbcTemplate.queryForStream(GET_SUBSCRIBED_USERS,subscribedUserMapper,testCaseExecutionId).collect(Collectors.toList());
    }
}
