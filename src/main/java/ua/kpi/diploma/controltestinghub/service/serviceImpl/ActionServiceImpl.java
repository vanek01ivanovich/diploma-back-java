package ua.kpi.diploma.controltestinghub.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dao.ActionDao;
import ua.kpi.diploma.controltestinghub.dto.ActionDtoWithIdNameVoid;
import ua.kpi.diploma.controltestinghub.dto.ActionVariableDto;
import ua.kpi.diploma.controltestinghub.model.Action;
import ua.kpi.diploma.controltestinghub.service.ActionService;
import ua.kpi.diploma.controltestinghub.util.Pageable;
import ua.kpi.diploma.controltestinghub.util.Pagination;

import java.util.List;


@Service
@Slf4j
public class ActionServiceImpl implements ActionService {

    private final ActionDao actionDao;
    Pagination pagination;

    @Autowired
    public ActionServiceImpl(Pagination pagination,ActionDao actionDAO) {
        this.actionDao = actionDAO;
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
        return actionDao.getPageActions(pagination.formSqlPostgresPaginationPiece(pageable));
    }

    /**
     * Returns list of all actions without pagination
     * @return list of actions
     */
    @Override
    public List<Action> getAllActions() {
        return actionDao.getAllActions();
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
        return actionDao.findActionsByName(pagination.formSqlPostgresPaginationPiece(pageable),name);
    }

    /**
     * @return list of all Action objects with fields id and name.
     */
    @Override
    public List<ActionDtoWithIdNameVoid> getAllActionsWithIdName() {
        List<ActionDtoWithIdNameVoid> actions = actionDao.findAllWithIdName();
        log.info("IN getAllActionsWithIdName - {} actions found", actions.size());
        return actions;
    }

    /**
     * Returns Integer number of all actions
     * @return Integer number
     */
    @Override
    public Integer getNumberOfActions() {
        return actionDao.getNumberOfActions();
    }

    @Override
    public Integer createAction(String name, String description) {
        return actionDao.createAction(name, description);
    }

    /**
     * Returns the list of ActionVariableDto
     * @param id needed for getting values from DB by id
     * @return list of ActionVariableDto
     */
    @Override
    public List<ActionVariableDto> getActionVariableById(Integer id) {
        return actionDao.getActionVariable(id);
    }

    /**
     * Void method that update action`s description
     * @param id needed for updating action by id
     * @param action that contains of updated values
     */
    @Override
    public void updateActionDescription(Integer id, Action action) {
        actionDao.updateActionDescription(id,action);
    }
}
