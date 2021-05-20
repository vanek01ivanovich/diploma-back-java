package ua.kpi.diploma.controltestinghub.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionExecutionPassedFailed {
    private Integer quantity;
    private String date;
}
