package ua.kpi.diploma.controltestinghub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kpi.diploma.controltestinghub.dto.GroupedTestCaseExecutionDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseExecutionsCountsByStartDatesDto;
import ua.kpi.diploma.controltestinghub.dto.UserCountDto;
import ua.kpi.diploma.controltestinghub.model.ActionExecutionPassedFailed;
import ua.kpi.diploma.controltestinghub.service.ActionExecutionService;
import ua.kpi.diploma.controltestinghub.service.DashboardService;
import ua.kpi.diploma.controltestinghub.service.TestCaseExecService;
import ua.kpi.diploma.controltestinghub.service.TestCaseService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dashboard")
public class DashboardController {
    private final TestCaseService testCaseService;
    private final TestCaseExecService testCaseExecService;
    private final ActionExecutionService actionExecutionService;
    private final DashboardService dashboardService;

    public DashboardController(TestCaseService testCaseService, TestCaseExecService testCaseExecService, ActionExecutionService actionExecutionService, DashboardService dashboardService) {
        this.testCaseService = testCaseService;
        this.testCaseExecService = testCaseExecService;
        this.actionExecutionService = actionExecutionService;
        this.dashboardService = dashboardService;

    }

    @GetMapping("/top-subscribed-test-cases")
    public ResponseEntity<?> getTopFiveSubscribedTestCases() {
        return ResponseEntity.ok(testCaseService.getFiveTopSubscribedTestCases());
    }

    @GetMapping("/test-case-executions-by-dates")
    public List<TestCaseExecutionsCountsByStartDatesDto> testCaseExecutionsByDates(@RequestParam Integer numberOfDays){
        return testCaseExecService.getExecutionsByDatesForLastDays(numberOfDays);
    }

    /**
     * Returns the list of GroupedTestCaseExecutionDto
     * Grouped by most executable test cases
     * Method is needed for chart on angular
     * @return list of GroupedTestCaseExecutionDto
     */
    @GetMapping("/test-case-execution/grouped-number")
    public List<GroupedTestCaseExecutionDto> getGroupedTestCaseExecutionNumber(){
        return testCaseExecService.getGroupedTestCaseExecution();
    }

    /**
     * Returns the number of actions executions
     * Grouped by data
     * @param status of actions executions
     * @return number of actions executions
     */
    @GetMapping("/action-execution/{status}")
    public List<ActionExecutionPassedFailed> getActionExecutionPassedFailed(@PathVariable("status") String status) {
        return actionExecutionService.getActionExecutionPassedFailed(status);
    }

    /**
     * Returns UserCountDto:
     * It contains fields of Long type: userCount, adminCount, managerCount, engineerCount
     */
    /*@GetMapping("/user-count-by-role")
    public UserCountDto getUserCountByRole() {
        return dashboardService.getCountOfUsersByRole();
    }*/
}
