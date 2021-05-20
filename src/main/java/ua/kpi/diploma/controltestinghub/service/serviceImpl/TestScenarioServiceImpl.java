package ua.kpi.diploma.controltestinghub.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dto.TestScenarioDto;
import ua.kpi.diploma.controltestinghub.dto.TestScenarioDtoWithIdNameArchived;
import ua.kpi.diploma.controltestinghub.exception.ValidationException;
import ua.kpi.diploma.controltestinghub.model.CompoundActionWithActionIdAndPriority;
import ua.kpi.diploma.controltestinghub.model.TestScenario;
import ua.kpi.diploma.controltestinghub.service.TestScenarioService;
import ua.kpi.diploma.controltestinghub.util.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TestScenarioServiceImpl implements TestScenarioService {

    @Override
    public boolean updateTestScenario(TestScenarioDtoWithIdNameArchived testScenario) {
        return false;
    }

    @Override
    public boolean saveTestScenario(TestScenarioDto testScenarioDto) {
        return false;
    }

    @Override
    public List<TestScenario> getTestScenarios(Pageable pageable, String name) throws ValidationException {
        return null;
    }

    @Override
    public List<CompoundActionWithActionIdAndPriority> getAllCompoundActionsByCompoundId(Integer id) {
        return null;
    }

    @Override
    public List<TestScenario> getTestScenarioById(Integer id) {
        return null;
    }

    @Override
    public List<TestScenario> getAll() {
        return null;
    }

    @Override
    public Integer countPages(Integer pageSize) {
        return null;
    }
}
