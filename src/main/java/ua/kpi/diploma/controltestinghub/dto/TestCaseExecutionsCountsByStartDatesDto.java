package ua.kpi.diploma.controltestinghub.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Builder
public class TestCaseExecutionsCountsByStartDatesDto {
    private Date date;
    private Long numberOfExecutions;

}
