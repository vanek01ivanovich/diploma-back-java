package ua.kpi.diploma.controltestinghub.service;


import java.util.List;

public interface ActionExecutionService {
    List<ActionExecutionDto> getAllActionExecutions(Long testCaseExecutionId, Pageable pageable);
    List<ActionExecutionPassedFailed> getActionExecutionPassedFailed(String status);

    Integer getQuantityActionsExecutions(Long testCaseExecutionId,String searchName);
}
