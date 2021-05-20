package ua.kpi.diploma.controltestinghub.dao;

import ua.kpi.diploma.controltestinghub.dto.TestScenarioItemDto;

public interface CompoundInstanceDao {
    Integer saveCompoundInstanceAndGetGeneratedId(TestScenarioItemDto dto, Integer testScenarioId);

}
