package ua.kpi.diploma.controltestinghub.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class GroupedTestCaseExecutionDto {
    private Integer testCaseId;
    private String testCaseName;
    private Integer numberOfTestCaseExecution;
}
