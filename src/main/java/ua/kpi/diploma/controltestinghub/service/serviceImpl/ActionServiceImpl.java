package ua.kpi.diploma.controltestinghub.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dto.ActionDtoWithIdNameVoid;
import ua.kpi.diploma.controltestinghub.dto.ActionVariableDto;
import ua.kpi.diploma.controltestinghub.model.Action;
import ua.kpi.diploma.controltestinghub.service.ActionService;
import ua.kpi.diploma.controltestinghub.util.Pageable;

import java.util.List;

@Service
@Slf4j
public class ActionServiceImpl implements ActionService {

    @Override
    public List<Action> getAllActions(Pageable pageable) {
        return null;
    }

    @Override
    public List<Action> getAllActions() {
        return null;
    }

    @Override
    public List<Action> findActionsByName(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Integer getNumberOfActions() {
        return null;
    }

    @Override
    public List<ActionDtoWithIdNameVoid> getAllActionsWithIdName() {
        return null;
    }

    @Override
    public Integer createAction(String name, String description) {
        return null;
    }

    @Override
    public List<ActionVariableDto> getActionVariableById(Long id) {
        return null;
    }

    @Override
    public void updateActionDescription(Long id, Action action) {

    }
}
