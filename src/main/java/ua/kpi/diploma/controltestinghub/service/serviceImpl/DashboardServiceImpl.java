package ua.kpi.diploma.controltestinghub.service.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dao.UserDao;
import ua.kpi.diploma.controltestinghub.dto.UserCountDto;
import ua.kpi.diploma.controltestinghub.service.DashboardService;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService{
    UserDao userDao;

    @Autowired
    public DashboardServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserCountDto getCountOfUsersByRole() {
        return userDao.countOfUsersByRole();
    }
}
