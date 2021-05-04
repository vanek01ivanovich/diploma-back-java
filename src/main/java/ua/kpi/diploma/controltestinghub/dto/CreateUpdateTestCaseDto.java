package ua.kpi.diploma.controltestinghub.dto;

import lombok.*;
import ua.netcracker.group3.automaticallytesting.model.VariableValue;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUpdateTestCaseDto {
    private Long id;
    private String testCaseName;
    private Long projectId;
    private Long dataSetId;
    private Long testScenarioId;
    private List<VariableValue> variableValues;
}
