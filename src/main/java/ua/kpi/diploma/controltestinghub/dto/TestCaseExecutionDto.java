package ua.kpi.diploma.controltestinghub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestCaseExecutionDto {
    private Integer id;
    private String status;
    private String startDateTime;
    private String endDateTime;
    private Integer userId;
    private String testCaseName;
    private String projectName;
    private Integer allActions;
    private Integer passedActions;
}
