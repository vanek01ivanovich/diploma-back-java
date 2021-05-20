package ua.kpi.diploma.controltestinghub.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import ua.kpi.diploma.controltestinghub.model.Action;
import ua.kpi.diploma.controltestinghub.model.DataEntry;
import ua.kpi.diploma.controltestinghub.model.Variable;

@Builder
@Data
@ToString
public class ActionExecutionDto {
    private Integer id;
    private Action action;
    private Variable variable;
    private DataEntry dataEntry;
    private Integer testcaseId;
    private Integer testcaseExecutionId;
    private String status;
}
