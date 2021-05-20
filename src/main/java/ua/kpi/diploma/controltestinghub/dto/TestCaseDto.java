package ua.kpi.diploma.controltestinghub.dto;

import lombok.Builder;
import lombok.Data;
import ua.kpi.diploma.controltestinghub.model.TestCase;
import java.util.List;

@Data
@Builder
public class TestCaseDto {
    private TestCase testCase;
    private String projectName;
    private String projectLink;
    private List<ScenarioStepDto> scenarioStepsWithData;
}
