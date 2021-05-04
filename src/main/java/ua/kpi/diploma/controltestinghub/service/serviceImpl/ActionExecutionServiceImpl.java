package ua.kpi.diploma.controltestinghub.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ActionExecutionServiceImpl implements ActionExecutionService {

    private final ActionExecutionDAO actionExecutionDAO;
    private final Pagination pagination;

    @Autowired
    public ActionExecutionServiceImpl(ActionExecutionDAO actionExecutionDAO,Pagination pagination){
        this.actionExecutionDAO = actionExecutionDAO;
        this.pagination = pagination;
    }

    /**
     * Service that returns the List of ActionExecutionDto with pagination
     * Using Pageable class
     * @param testCaseExecutionId the id, which helps to get action executions by testCaseExecutionId
     * @param pageable object that contains pageSize,pageNumber and  needed for pagination
     * @return List of ActionExecutionDto
     */
    @Override
    public List<ActionExecutionDto> getAllActionExecutions(Long testCaseExecutionId, Pageable pageable) {
        return actionExecutionDAO.getAllActionExecution(testCaseExecutionId,
                                                        pagination.formSqlPostgresPaginationPiece(pageable),
                                                        pageable.getSearch());
    }

    @Override
    public List<ActionExecutionPassedFailed> getActionExecutionPassedFailed(String status) {
        return actionExecutionDAO.getActionExecutionPassedFailed(status);
    }

    /**
     * Returns the Integer of Quantity action executions
     * @param testCaseExecutionId the id, which helps to get action executions by testCaseExecutionId
     * @param searchName an item that used in DB query for searching info
     * @return the Integer of Quantity action executions
     */
    @Override
    public Integer getQuantityActionsExecutions(Long testCaseExecutionId,String searchName) {
        return actionExecutionDAO.getQuantityActionsExecutions(testCaseExecutionId,searchName);
    }
}
