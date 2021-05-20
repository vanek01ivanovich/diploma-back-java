package ua.kpi.diploma.controltestinghub.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ActionVariableDto {
    private Integer actionId;
    private String actionName;
    private String actionDescription;
    private String variableName;
}
