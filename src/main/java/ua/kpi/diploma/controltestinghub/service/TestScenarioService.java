package ua.kpi.diploma.controltestinghub.service;


import ua.kpi.diploma.controltestinghub.dto.TestScenarioDto;
import ua.kpi.diploma.controltestinghub.dto.TestScenarioDtoWithIdNameArchived;
import ua.kpi.diploma.controltestinghub.exception.ValidationException;
import ua.kpi.diploma.controltestinghub.model.CompoundActionWithActionIdAndPriority;
import ua.kpi.diploma.controltestinghub.model.TestScenario;
import ua.kpi.diploma.controltestinghub.util.Pageable;

import java.util.List;

public interface TestScenarioService {

    boolean updateTestScenario(TestScenarioDtoWithIdNameArchived testScenario);

    boolean saveTestScenario(TestScenarioDto testScenarioDto);

    List<TestScenario> getTestScenarios(Pageable pageable, String name) throws ValidationException, ValidationException;

    List<CompoundActionWithActionIdAndPriority> getAllCompoundActionsByCompoundId(Integer id);

    List<TestScenario> getTestScenarioById(Integer id);

    List<TestScenario> getAll();

    Integer countPages(Integer pageSize);


    //     List<User> getUsers(Pageable pageable, String name, String surname, String email, String role);
}
