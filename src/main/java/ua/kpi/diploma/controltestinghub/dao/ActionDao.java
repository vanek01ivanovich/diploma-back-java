package ua.kpi.diploma.controltestinghub.dao;

import ua.kpi.diploma.controltestinghub.dto.ActionDtoWithIdNameVoid;
import ua.kpi.diploma.controltestinghub.dto.ActionVariableDto;
import ua.kpi.diploma.controltestinghub.model.Action;

import java.util.List;

public interface ActionDao {
    List<Action> getPageActions(String pageActionSql);

    List<Action> findActionsByName(String pageActionSql,String name);

    List<ActionDtoWithIdNameVoid> findAllWithIdName();

    Integer getNumberOfActions();

    Integer createAction(String name, String description);

    List<Action> getAllActions();

    List<ActionVariableDto> getActionVariable(Integer id);

    void updateActionDescription(Integer id, Action action);
}
