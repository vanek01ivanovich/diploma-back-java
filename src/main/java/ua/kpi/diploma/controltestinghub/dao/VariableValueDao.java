package ua.kpi.diploma.controltestinghub.dao;

import ua.kpi.diploma.controltestinghub.model.VariableValue;

import java.util.List;

public interface VariableValueDao {
    void insert(List<VariableValue> variableValues, Integer testCaseId);

    void updateDataEntry(List<VariableValue> variableValues);

}
