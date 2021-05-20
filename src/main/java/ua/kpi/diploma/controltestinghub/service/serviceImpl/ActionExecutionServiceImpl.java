package ua.kpi.diploma.controltestinghub.service.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dao.ActionExecutionDao;
import ua.kpi.diploma.controltestinghub.dto.ActionExecutionDto;
import ua.kpi.diploma.controltestinghub.model.ActionExecutionPassedFailed;
import ua.kpi.diploma.controltestinghub.service.ActionExecutionService;
import ua.kpi.diploma.controltestinghub.util.Pageable;
import ua.kpi.diploma.controltestinghub.util.Pagination;


import java.util.List;

@Service
public class    ActionExecutionServiceImpl implements ActionExecutionService {

    private final ActionExecutionDao actionExecutionDao;
    private final Pagination pagination;

    @Autowired
    public ActionExecutionServiceImpl(ActionExecutionDao actionExecutionDao, Pagination pagination) {
        this.actionExecutionDao = actionExecutionDao;
        this.pagination = pagination;
    }


    @Override
    public List<ActionExecutionDto> getAllActionExecutions(Integer testCaseExecutionId, Pageable pageable) {
        return actionExecutionDao.getAllActionExecution(testCaseExecutionId,
                pagination.formSqlPostgresPaginationPiece(pageable),
                pageable.getSearch());
    }

    @Override
    public List<ActionExecutionPassedFailed> getActionExecutionPassedFailed(String status) {
        return actionExecutionDao.getActionExecutionPassedFailed(status);
    }

    @Override
    public Integer getQuantityActionsExecutions(Integer testCaseExecutionId, String searchName) {
        return actionExecutionDao.getQuantityActionsExecutions(testCaseExecutionId,searchName);
    }
}