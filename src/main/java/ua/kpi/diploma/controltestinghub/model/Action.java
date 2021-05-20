package ua.kpi.diploma.controltestinghub.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Action {
    private Integer actionId;
    private String actionName;
    private String actionDescription;
    private Boolean isVoid;
}
