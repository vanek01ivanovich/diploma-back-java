package ua.kpi.diploma.controltestinghub.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActionExecution {
    private Integer id;
    private Integer testCaseExecutionId;
    private Integer actionInstanceId;
    private String status;
}
