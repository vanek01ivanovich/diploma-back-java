package ua.kpi.diploma.controltestinghub.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionExecutionPassedFailed {
    long quantity;
    String date;
}
