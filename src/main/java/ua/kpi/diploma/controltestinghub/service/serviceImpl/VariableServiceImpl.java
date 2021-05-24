package ua.kpi.diploma.controltestinghub.service.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dao.VariableDao;
import ua.kpi.diploma.controltestinghub.service.VariableService;

import java.util.List;

@Service
public class VariableServiceImpl implements VariableService {


    VariableDao variableDao;

    @Autowired
    public VariableServiceImpl(VariableDao variableDao) {
        this.variableDao = variableDao;
    }

    @Override
    public void createVariables(Integer actionId, List<String> names) {
        this.variableDao.createVariables(actionId, names);
    }
}
