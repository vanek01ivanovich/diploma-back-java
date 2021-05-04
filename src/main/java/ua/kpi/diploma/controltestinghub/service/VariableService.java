package ua.kpi.diploma.controltestinghub.service;

import java.util.List;

public interface VariableService {
    void createVariables(Long actionId, List<String> names);
}
