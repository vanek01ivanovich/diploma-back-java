package ua.kpi.diploma.controltestinghub.service;


import ua.kpi.diploma.controltestinghub.dto.ActionDtoWithIdNameVoid;
import ua.kpi.diploma.controltestinghub.dto.ActionVariableDto;
import ua.kpi.diploma.controltestinghub.model.Action;
import ua.kpi.diploma.controltestinghub.util.Pageable;

import java.util.List;

public interface ActionService {

    List<Action> getAllActions(Pageable pageable);
    List<Action> getAllActions();
    List<Action> findActionsByName(String name,Pageable pageable);
    Integer getNumberOfActions();

    List<ActionDtoWithIdNameVoid> getAllActionsWithIdName();

    Integer createAction(String name, String description);

    List<ActionVariableDto> getActionVariableById(Long id);

    void updateActionDescription(Long id, Action action);
}
