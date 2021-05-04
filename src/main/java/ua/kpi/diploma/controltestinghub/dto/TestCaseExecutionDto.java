package ua.kpi.diploma.controltestinghub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestCaseExecutionDto {
    long id;
    String status;
    String startDateTime;
    String endDateTime;
    long userId;
    String testCaseName;
    String projectName;
    long allActions;
    long passedActions;
}
