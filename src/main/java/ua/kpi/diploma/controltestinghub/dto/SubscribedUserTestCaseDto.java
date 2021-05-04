package ua.kpi.diploma.controltestinghub.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscribedUserTestCaseDto {
    private Long id;
    private String userName;
    private String email;
    private Long testCaseId;
    private String testCaseName;
}
