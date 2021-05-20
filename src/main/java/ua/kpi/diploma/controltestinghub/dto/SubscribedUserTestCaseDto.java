package ua.kpi.diploma.controltestinghub.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscribedUserTestCaseDto {
    private Integer id;
    private String userName;
    private String email;
    private Integer testCaseId;
    private String testCaseName;
}
