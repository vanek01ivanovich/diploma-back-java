package ua.kpi.diploma.controltestinghub.dto;

import lombok.Builder;
import lombok.Data;
import ua.netcracker.group3.automaticallytesting.model.Variable;

import java.util.List;

@Builder
@Data
public class ActionVariableDto {
    private Long actionId;
    private String actionName;
    private String actionDescription;
    private String variableName;
}
