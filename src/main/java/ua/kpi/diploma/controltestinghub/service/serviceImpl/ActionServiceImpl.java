package ua.kpi.diploma.controltestinghub.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ActionServiceImpl implements ActionService {

    private ActionDAO actionDAO;
    Pagination pagination;

    @Autowired
    public ActionServiceImpl(Pagination pagination,ActionDAO actionDAO) {
        this.actionDAO = actionDAO;
        this.pagination = pagination;
    }


    /**
     * Returns list of actions with pagination
     * @param pageable needed for pagination
     * @return list of actions
     */
    @Override
    public List<Action> getAllActions(Pageable pageable) {
        pageable = pagination.setDefaultOrderValue(pageable);
        return actionDAO.getPageActions(pagination.formSqlPostgresPaginationPiece(pageable));
    }

    /**
     * Returns list of all actions without pagination
     * @return list of actions
     */
    @Override
    public List<Action> getAllActions() {
        return actionDAO.getAllActions();
    }

    /**
     * Returns list of actions that contains certain name
     * @param name needed for getting values from DB by name
     * @param pageable needed for pagination
     * @return list of actions
     */
    @Override
    public List<Action> findActionsByName(String name,Pageable pageable) {
        pageable = pagination.setDefaultOrderValue(pageable);
        return actionDAO.findActionsByName(pagination.formSqlPostgresPaginationPiece(pageable),name);
    }

    /**
     * @return list of all Action objects with fields id and name.
     */
    @Override
    public List<ActionDtoWithIdNameVoid> getAllActionsWithIdName() {
        List<ActionDtoWithIdNameVoid> actions = actionDAO.findAllWithIdName();
        log.info("IN getAllActionsWithIdName - {} actions found", actions.size());
        return actions;
    }

    /**
     * Returns Integer number of all actions
     * @return Integer number
     */
    @Override
    public Integer getNumberOfActions() {
        return actionDAO.getNumberOfActions();
    }

    @Override
    public long createAction(String name, String description) {
        return actionDAO.createAction(name, description);
    }

    /**
     * Returns the list of ActionVariableDto
     * @param id needed for getting values from DB by id
     * @return list of ActionVariableDto
     */
    @Override
    public List<ActionVariableDto> getActionVariableById(Long id) {
        return actionDAO.getActionVariable(id);
    }

    /**
     * Void method that update action`s description
     * @param id needed for updating action by id
     * @param action that contains of updated values
     */
    @Override
    public void updateActionDescription(Long id, Action action) {
        actionDAO.updateActionDescription(id,action);
    }
}
