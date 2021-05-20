package ua.kpi.diploma.controltestinghub.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class VariableValue {
    private Integer id;
    private Integer actionInstanceId;
    private Integer variableId;
    private Integer dataEntryId;
    private Integer testScenarioId;
}
