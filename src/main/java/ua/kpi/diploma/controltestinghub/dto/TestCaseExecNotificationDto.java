package ua.kpi.diploma.controltestinghub.dto;

import lombok.Data;

@Data
public class TestCaseExecNotificationDto {
    long id;
    String name;
    String status;
}
