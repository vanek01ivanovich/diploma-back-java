package ua.kpi.diploma.controltestinghub.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dao.ReportDao;
import ua.kpi.diploma.controltestinghub.dto.ActionExecutionDto;
import ua.kpi.diploma.controltestinghub.dto.SubscribedUserTestCaseDto;
import ua.kpi.diploma.controltestinghub.service.ReportService;


import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final EmailServiceImpl emailService;
    private final ReportDao reportDao;

    @Autowired
    public ReportServiceImpl(EmailServiceImpl emailService,ReportDao reportDao){
        this.emailService = emailService;
        this.reportDao = reportDao;
    }

    /**
     * @param actionExecutionList contains action executions
     * @param subscribedUsers contains subscribed users
     * @return ResponseEntity with status
     */
    @Override
    public ResponseEntity<?> sendReportToUser(List<ActionExecutionDto> actionExecutionList, List<SubscribedUserTestCaseDto> subscribedUsers) {
        return emailService.sendReportToUser(actionExecutionList,subscribedUsers);
    }

    /**
     * @param testCaseExecutionId needed for getting value from DB by id
     * @return list of subscribed users
     */
    @Override
    public List<SubscribedUserTestCaseDto> getSubscribedUsers(Integer testCaseExecutionId) {
        return reportDao.getSubscribedUsers(testCaseExecutionId);
    }
}
