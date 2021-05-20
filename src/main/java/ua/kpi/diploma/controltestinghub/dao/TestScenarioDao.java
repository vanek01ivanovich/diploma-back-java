package ua.kpi.diploma.controltestinghub.dao;

import ua.kpi.diploma.controltestinghub.dto.TestScenarioDto;
import ua.kpi.diploma.controltestinghub.model.TestScenario;

import java.util.List;

public interface TestScenarioDao {
    void updateTestScenarioById(TestScenario testScenario);

    Integer saveTestScenario(TestScenarioDto testScenarioDto);

    boolean checkExistTestScenarioByName(String name);

    List<TestScenario> getAll();

    List<TestScenario> getTestScenarioById(Integer id);

    List<TestScenario> getTestScenariosPageSorted(String orderByLimitOffsetWithValues, String name);

    Integer countUsers();
}
