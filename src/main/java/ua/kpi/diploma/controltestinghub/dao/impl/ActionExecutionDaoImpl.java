package ua.kpi.diploma.controltestinghub.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dao.ActionExecutionDao;
import ua.kpi.diploma.controltestinghub.dto.ActionExecutionDto;
import ua.kpi.diploma.controltestinghub.mapper.ActionExecutionMapper;
import ua.kpi.diploma.controltestinghub.mapper.ActionExecutionPassedFailedMapper;
import ua.kpi.diploma.controltestinghub.model.ActionExecution;
import ua.kpi.diploma.controltestinghub.model.ActionExecutionPassedFailed;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ActionExecutionDaoImpl implements ActionExecutionDao {

    private final JdbcTemplate jdbcTemplate;
    private final ActionExecutionMapper actionExecutionMapper;
    private final ActionExecutionPassedFailedMapper actionExecutionPassedFailedMapper;

    @Value("${create.action.executions}")
    private String CREATE_ACTION_EXECUTIONS;

    @Value("${get.list.of.action.executions}")
    private String GET_ALL_ACTION_EXECUTIONS;

    @Value("${get.number.of.failed.passed.action.executions}")
    private String GET_NUMBER_ACTION_EXECUTION;

    @Value("${get.count.action.executions}")
    private String GET_COUNT_ACTION_EXECUTIONS;

    @Value("${action.execution.search.name.sql}")
    private String ACTION_EXECUTION_SEARCH_NAME;

    @Value("${action.execution.count.search.name.sql}")
    private String ACTION_EXECUTION_COUNT_SEARCH_NAME;

    @Autowired
    public ActionExecutionDaoImpl(JdbcTemplate jdbcTemplate, ActionExecutionMapper actionExecutionMapper, ActionExecutionPassedFailedMapper actionExecutionPassedFailedMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.actionExecutionMapper = actionExecutionMapper;
        this.actionExecutionPassedFailedMapper = actionExecutionPassedFailedMapper;
    }

    @Override
    public void addActionExecution(List<ActionExecution> actionExecutionList) {
        jdbcTemplate.batchUpdate(CREATE_ACTION_EXECUTIONS,actionExecutionList,actionExecutionList.size(),
                ((preparedStatement, actionExecutionValue) -> {
                    preparedStatement.setLong(1,actionExecutionValue.getTestCaseExecutionId());
                    preparedStatement.setLong(2,actionExecutionValue.getActionInstanceId());
                    preparedStatement.setString(3,actionExecutionValue.getStatus());
                }));
    }

    @Override
    public List<ActionExecutionDto> getAllActionExecution(Integer testCaseExecutionId, String pagination, String search) {
        String searchNameSql = search == null || search.equals("") ? "" :
                String.format(ACTION_EXECUTION_SEARCH_NAME,search);
        return jdbcTemplate.queryForStream(GET_ALL_ACTION_EXECUTIONS + searchNameSql + pagination,actionExecutionMapper,testCaseExecutionId,testCaseExecutionId)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActionExecutionPassedFailed> getActionExecutionPassedFailed(String status) {
        return jdbcTemplate.queryForStream(GET_NUMBER_ACTION_EXECUTION, actionExecutionPassedFailedMapper, status).collect(Collectors.toList());
    }

    @Override
    public Integer getQuantityActionsExecutions(Integer testCaseExecutionId, String searchName) {
        String searchNameSql = searchName == null || searchName.equals("") ? "" :
                String.format(ACTION_EXECUTION_COUNT_SEARCH_NAME,searchName);
        return jdbcTemplate.queryForObject(GET_COUNT_ACTION_EXECUTIONS + searchNameSql,Integer.class,testCaseExecutionId);
    }
}
