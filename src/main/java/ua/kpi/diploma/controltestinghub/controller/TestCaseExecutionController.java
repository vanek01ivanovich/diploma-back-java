package ua.kpi.diploma.controltestinghub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ua.kpi.diploma.controltestinghub.dto.TestCaseDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseExecutionDto;
import ua.kpi.diploma.controltestinghub.execution.TestCaseExecutionService;
import ua.kpi.diploma.controltestinghub.model.TestCaseExecution;
import ua.kpi.diploma.controltestinghub.service.NotificationService;
import ua.kpi.diploma.controltestinghub.service.TestCaseExecService;
import ua.kpi.diploma.controltestinghub.service.TestCaseService;
import ua.kpi.diploma.controltestinghub.service.UserService;
import ua.kpi.diploma.controltestinghub.util.Pageable;

import java.util.List;

import static ua.kpi.diploma.controltestinghub.model.TestCaseExecutionStatus.FINISHED;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/test-case-execution")
public class TestCaseExecutionController {
    private final TestCaseExecService testCaseExecService;
    private final TestCaseService testCaseService;
    private final TestCaseExecutionService testCaseExecutionService;
    private final UserService userService;
    private final NotificationService notificationService;
    //private final SseService sseService;

    public TestCaseExecutionController(TestCaseExecService testCaseExecService, TestCaseService testCaseService,
                                       TestCaseExecutionService testCaseExecutionService, UserService userService,
                                       NotificationService notificationService /*,SseService sseService*/) {
        this.testCaseExecService = testCaseExecService;
        this.testCaseService = testCaseService;
        this.testCaseExecutionService = testCaseExecutionService;
        this.userService = userService;
        this.notificationService = notificationService;
        //this.sseService = sseService;
    }

    @GetMapping("/get-all")
    public List<TestCaseExecution> getAllTestCaseExecutions() {
        return testCaseExecService.getAllTestCaseExecutions();
    }

    /**
     * @param limit page size
     * @param offset sql offset
     * @param orderBy ASC or DESC
     * @param orderByClause column for order by
     * @param testCaseName test case name
     * @param projectName project name
     * @param status status of actions executions
     * @return list of test case executions
     */
    @GetMapping("/all")
    public List<TestCaseExecutionDto> getAllTestCaseExecutionWithFailedActionNumber(Integer limit, Integer offset, String orderBy, String orderByClause,
                                                                                    String testCaseName, String projectName, String status) {
        Pageable pageable = Pageable.builder().page((int) offset).pageSize((int) limit).sortOrder(orderByClause).sortField(orderBy).build();
        //actions execution status:  all - get all actions executions, passed - only passed, failed - only with failed
        log.info("test case execution list: {}, search test case name: {}, search project name: {}, actions execution status: {}", pageable, testCaseName, projectName, status);
        return  testCaseExecService.getAllTestCaseExecutionWithFailedActionNumber(limit, offset, orderBy, orderByClause, testCaseName, projectName, status);
    }

    /**
     * @param testCaseName test case name
     * @param projectName project name
     * @param status status of actions executions
     * @return number of test case executions
     */
    @GetMapping("/count/{testCaseName}/{projectName}/{status}")
    public Integer countTestCaseExecutions(@PathVariable("testCaseName") String testCaseName, @PathVariable("projectName") String projectName,
                                           @PathVariable("status") String status) {
        return testCaseExecService.countTestCaseExecutions(testCaseName, projectName, status);
    }

    /**
     * create test case executions
     * @param testCaseId test case id
     * @param userEmail user email
     */
    @PostMapping("/execute/{testCaseId}")
    public void createTestCaseExecution(@PathVariable("testCaseId") Integer testCaseId,
                                        @RequestBody String userEmail) {
        Integer userId = userService.getUserIdByEmail(userEmail);
        Integer testCaseExecutionId = testCaseExecService.createTestCaseExecution(testCaseId, userId);
        //notificationService.addNotifications(testCaseId, testCaseExecutionId);
        executeTestCase(testCaseId, testCaseExecutionId);
    }

    /**
     * @param testCaseId test case id
     * @param testCaseExecutionId test case executions id
     */
    private void executeTestCase(Integer testCaseId, Integer testCaseExecutionId) {
        TestCaseDto testCaseDto =  testCaseService.getTestCase(testCaseId);
        List<String> status = testCaseExecutionService.executeTestCase(testCaseDto, testCaseExecutionId);
        log.error("Number of error action executions: {}",status.stream().filter(el -> el.equals("FAILED")).count());
        testCaseExecService.updateTestCaseExecution(String.valueOf(FINISHED), testCaseExecutionId);
        //sseService.sendRecentNotifications(testCaseDto.getTestCase().getId(), testCaseExecutionId);
    }

}
