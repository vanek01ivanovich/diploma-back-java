package ua.kpi.diploma.controltestinghub.service;


import ua.kpi.diploma.controltestinghub.dto.ActionExecutionDto;
import ua.kpi.diploma.controltestinghub.model.ActionExecutionPassedFailed;
import ua.kpi.diploma.controltestinghub.util.Pageable;

import java.util.List;

public interface ActionExecutionService {
    List<ActionExecutionDto> getAllActionExecutions(Integer testCaseExecutionId, Pageable pageable);
    List<ActionExecutionPassedFailed> getActionExecutionPassedFailed(String status);

    Integer getQuantityActionsExecutions(Integer testCaseExecutionId,String searchName);
}
