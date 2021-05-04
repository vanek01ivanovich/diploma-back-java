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
    long id;
    long testCaseId;
    String status;
    //Timestamp startDateTime;
    String startDateTime;
    //Timestamp endDateTime;
    String endDateTime;
    long userId;
}
