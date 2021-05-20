package ua.kpi.diploma.controltestinghub.dto;

import lombok.*;
import ua.kpi.diploma.controltestinghub.model.VariableValue;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUpdateTestCaseDto {
    private Integer id;
    private String testCaseName;
    private Integer projectId;
    private Integer dataSetId;
    private Integer testScenarioId;
    private List<VariableValue> variableValues;
}
