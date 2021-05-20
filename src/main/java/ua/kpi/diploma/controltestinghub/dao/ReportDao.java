package ua.kpi.diploma.controltestinghub.dao;

import ua.kpi.diploma.controltestinghub.dto.SubscribedUserTestCaseDto;

import java.util.List;

public interface ReportDao {
    List<SubscribedUserTestCaseDto> getSubscribedUsers(Integer testCaseExecutionId);

}
