package ua.kpi.diploma.controltestinghub.service.serviceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationDAO notificationDAO;

    public NotificationServiceImpl(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    @Override
    public void addNotifications(long testCaseId, long testCaseExecutionId) {
        notificationDAO.insertNotifications(testCaseId, testCaseExecutionId);

    }
}
