package ua.kpi.diploma.controltestinghub.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReportService {
    ResponseEntity<?> sendReportToUser(List<ActionExecutionDto> actionExecutionList, List<SubscribedUserTestCaseDto> subscribedUsers);
    List<SubscribedUserTestCaseDto> getSubscribedUsers(Long testCaseExecutionId);
}
