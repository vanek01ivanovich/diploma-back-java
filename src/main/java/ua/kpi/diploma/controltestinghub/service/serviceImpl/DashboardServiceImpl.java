package ua.kpi.diploma.controltestinghub.service.serviceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService{
    UserDAO userDAO;

    public DashboardServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserCountDto getCountOfUsersByRole() {
        return userDAO.countOfUsersByRole();
    }
}
