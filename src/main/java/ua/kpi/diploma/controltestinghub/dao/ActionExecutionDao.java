package ua.kpi.diploma.controltestinghub.dao;

import ua.kpi.diploma.controltestinghub.dto.ActionDtoWithIdNameVoid;
import ua.kpi.diploma.controltestinghub.dto.ActionExecutionDto;
import ua.kpi.diploma.controltestinghub.dto.ActionVariableDto;
import ua.kpi.diploma.controltestinghub.model.Action;
import ua.kpi.diploma.controltestinghub.model.ActionExecution;
import ua.kpi.diploma.controltestinghub.model.ActionExecutionPassedFailed;

import java.util.List;

public interface ActionExecutionDao {
    void addActionExecution(List<ActionExecution> actionExecutionList);
    List<ActionExecutionDto> getAllActionExecution(Integer testCaseExecutionId, String pagination, String search);
    List<ActionExecutionPassedFailed> getActionExecutionPassedFailed(String status);

    Integer getQuantityActionsExecutions(Integer testCaseExecutionId,String searchName);


}
