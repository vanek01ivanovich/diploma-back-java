package ua.kpi.diploma.controltestinghub.dao;

import java.util.List;

public interface VariableDao {
    void createVariables(Integer actionId, List<String> names);

}
