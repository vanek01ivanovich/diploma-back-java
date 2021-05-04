package ua.kpi.diploma.controltestinghub.service.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariableServiceImpl implements VariableService {

    VariableDAO variableDAO;

    @Autowired
    public VariableServiceImpl(VariableDAO variableDAO) {
        this.variableDAO = variableDAO;
    }

    @Override
    public void createVariables(Long actionId, List<String> names) {
        this.variableDAO.createVariables(actionId, names);
    }
}
