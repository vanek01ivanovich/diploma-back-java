package ua.kpi.diploma.controltestinghub.dao;

import ua.kpi.diploma.controltestinghub.dto.TestScenarioItemDto;
import ua.kpi.diploma.controltestinghub.model.ActionInstanceJoined;

import java.util.List;

public interface ActionInstanceDao {
    List<ActionInstanceJoined> getActionInstanceJoinedByTestCaseId(Integer testCaseId);

    void saveActionInstancesWithoutCompoundInstanceId(List<TestScenarioItemDto> actions, Integer testScenarioId);

    void saveActionInstancesWithCompoundInstanceId(List<TestScenarioItemDto> actions, Integer testScenarioId, Integer compoundInstanceId);

}
