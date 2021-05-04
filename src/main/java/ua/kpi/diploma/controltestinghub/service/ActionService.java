package ua.kpi.diploma.controltestinghub.service;


import java.util.List;

public interface ActionService {

    List<Action> getAllActions(Pageable pageable);
    List<Action> getAllActions();
    List<Action> findActionsByName(String name,Pageable pageable);
    Integer getNumberOfActions();

    List<ActionDtoWithIdNameVoid> getAllActionsWithIdName();

    long createAction(String name, String description);

    List<ActionVariableDto> getActionVariableById(Long id);

    void updateActionDescription(Long id, Action action);
}
