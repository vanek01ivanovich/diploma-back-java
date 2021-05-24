package ua.kpi.diploma.controltestinghub.service;


import ua.kpi.diploma.controltestinghub.dto.UserCountDto;

import java.util.List;

public interface DashboardService {

    UserCountDto getCountOfUsersByRole();
}
