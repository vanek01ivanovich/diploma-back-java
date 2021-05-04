package ua.kpi.diploma.controltestinghub.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import ua.netcracker.group3.automaticallytesting.model.Action;
import ua.netcracker.group3.automaticallytesting.model.DataEntry;
import ua.netcracker.group3.automaticallytesting.model.Variable;

@Builder
@Data
@ToString
public class ActionExecutionDto {
    private Integer id;
    private Action action;
    private Variable variable;
    private DataEntry dataEntry;
    private Long testcaseId;
    private Long testcaseExecutionId;
    private String status;
}
