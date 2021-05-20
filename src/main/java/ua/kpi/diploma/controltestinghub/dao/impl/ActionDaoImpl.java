package ua.kpi.diploma.controltestinghub.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.kpi.diploma.controltestinghub.dao.ActionDao;
import ua.kpi.diploma.controltestinghub.dto.ActionDtoWithIdNameVoid;
import ua.kpi.diploma.controltestinghub.dto.ActionVariableDto;
import ua.kpi.diploma.controltestinghub.mapper.ActionMapper;
import ua.kpi.diploma.controltestinghub.mapper.ActionVariableMapper;
import ua.kpi.diploma.controltestinghub.mapper.ActionWithIdNameVoidMapper;
import ua.kpi.diploma.controltestinghub.model.Action;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ActionDaoImpl implements ActionDao {

    private final JdbcTemplate jdbcTemplate;
    private final ActionMapper actionMapper;
    private final ActionVariableMapper actionVariableMapper;
    private final ActionWithIdNameVoidMapper actionWithIdNameVoidMapper;

    @Value("${get.all.actions}")
    private String GET_ALL_ACTIONS;

    @Value("${get.action.variable.by.action.id}")
    private String GET_ACTION_VARIABLE_BY_ID;

    @Value("${find.actions.by.name}")
    private String FIND_ACTIONS_BY_NAME;

    @Value("${get.number.of.actions}")
    private String GET_NUMBER_OF_ACTIONS;

    @Value("${find.action.all.with.id.name}")
    private String FIND_ALL_WITH_ID_NAME;

    @Value("${update.action.description}")
    private String UPDATE_ACTION_DESCRIPTION;

    @Autowired
    public ActionDaoImpl(JdbcTemplate jdbcTemplate,
                         ActionMapper actionMapper,
                         ActionVariableMapper actionVariableMapper,
                         ActionWithIdNameVoidMapper actionWithIdNameVoidMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.actionMapper = actionMapper;
        this.actionVariableMapper = actionVariableMapper;
        this.actionWithIdNameVoidMapper = actionWithIdNameVoidMapper;
    }

    @Override
    public List<Action> getPageActions(String pageActionSql) {
        return jdbcTemplate.query(GET_ALL_ACTIONS + pageActionSql,actionMapper);
    }

    @Override
    public List<Action> findActionsByName(String pageActionSql, String name) {
        return jdbcTemplate.queryForStream(FIND_ACTIONS_BY_NAME + pageActionSql,actionMapper,name).collect(Collectors.toList());
    }

    @Override
    public List<ActionDtoWithIdNameVoid> findAllWithIdName() {
        return jdbcTemplate.query(FIND_ALL_WITH_ID_NAME, actionWithIdNameVoidMapper);
    }

    @Override
    public Integer getNumberOfActions() {
        return jdbcTemplate.queryForObject(GET_NUMBER_OF_ACTIONS,Integer.class);
    }

    @Override
    public long createAction(String name, String description) {
        return 0;
    }

    @Override
    public List<Action> getAllActions() {
        return jdbcTemplate.query(GET_ALL_ACTIONS,actionMapper);
    }

    @Override
    public List<ActionVariableDto> getActionVariable(Integer id) {
        return jdbcTemplate.queryForStream(GET_ACTION_VARIABLE_BY_ID,actionVariableMapper,id).collect(Collectors.toList());
    }

    @Override
    public void updateActionDescription(Integer id, Action action) {
        jdbcTemplate.update(UPDATE_ACTION_DESCRIPTION,action.getActionDescription(),id);
    }
}
