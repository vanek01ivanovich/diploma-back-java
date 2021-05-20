package ua.kpi.diploma.controltestinghub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestCaseExecution {
    private Integer id;
    private Integer testCaseId;
    private String status;
    private String startDateTime;
    private String endDateTime;
    private Integer userId;
}
