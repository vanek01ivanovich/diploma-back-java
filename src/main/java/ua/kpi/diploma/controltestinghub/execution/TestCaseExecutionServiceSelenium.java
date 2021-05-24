package ua.kpi.diploma.controltestinghub.execution;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dao.ActionExecutionDao;
import ua.kpi.diploma.controltestinghub.dto.ActionDto;
import ua.kpi.diploma.controltestinghub.dto.ScenarioStepDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseDto;
import ua.kpi.diploma.controltestinghub.dto.VariableDto;
import ua.kpi.diploma.controltestinghub.execution.action.ActionExecutable;
import ua.kpi.diploma.controltestinghub.execution.action.ContextVariable;
import ua.kpi.diploma.controltestinghub.execution.action.impl.*;
import ua.kpi.diploma.controltestinghub.model.ActionExecution;
import ua.kpi.diploma.controltestinghub.model.Status;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TestCaseExecutionServiceSelenium implements TestCaseExecutionService {

    private final Map<Integer, ContextVariable> contextVariables = new HashMap<>();
    private final ActionExecutionDao actionExecutionDao;
    private List<ActionExecution> actionExecutions;
    private Status actionStatus;

    @Autowired
    public TestCaseExecutionServiceSelenium(ActionExecutionDao actionExecutionDAO){
        this.actionExecutionDao = actionExecutionDAO;
    }

    private final Map<String, ActionExecutable> actions = new HashMap<String, ActionExecutable>() {{
        put("click", new ClickActionExecutable());
        put("input", new TypeActionExecutable());
        put("click on drop down menu element", new DropDownActionExecutable());
        put("check checkbox", new CheckBoxCheckActionExecutable());
        put("uncheck checkbox", new CheckBoxUncheckActionExecutable());
    }};

    /**
     * Method execute test case and after pass data to DAO
     * @param testCaseDto contains all needed data for test case execution
     * @param testCaseExecutionId needed for executing test case by id
     * @return list of string like status
     */
    @Override
    public List<String> executeTestCase(TestCaseDto testCaseDto, Integer testCaseExecutionId) {

        List<ScenarioStepDto> scenarioStepDtoList = testCaseDto.getScenarioStepsWithData();
        actionExecutions = new ArrayList<>();
        actionStatus = Status.PASSED;

        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        //ChromeOptions options = new ChromeOptions();
        /*options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        options.addArguments("--lang=en");*/
        WebDriver driver = new ChromeDriver();

        log.info("Test case execution started");
        driver.get(testCaseDto.getProjectLink());
        driver.manage().window().maximize();

        scenarioStepDtoList.forEach(step -> {
            step.getActionDto().forEach(actionDto -> {
                executeScenarioAction(actionDto,driver,testCaseExecutionId);
            });
        });

        driver.close();
        log.info("Test case execution finished");

        List<String> statusActionExecutionsResult = statusValuesForTestExecution(actionExecutions);
        createActionExecutions(actionExecutions);
        log.info("Action Executions were added to DB successfully : {}",actionExecutions);
        return statusActionExecutionsResult;
    }

    /**
     * Void method execute action of test scenario
     * @param actionDto needed for getting value for each action execution
     * @param driver needed for selenium realization
     * @param testCaseExecutionId needed for execute action
     */
    private void executeScenarioAction(ActionDto actionDto, WebDriver driver, Integer testCaseExecutionId){
        System.out.println("ActionDto = " + actionDto);
        if (actionStatus == Status.PASSED) {
            actions.get(actionDto.getName())
                    .executeAction(driver, variableDtosToVariableValues(actionDto.getVariables()))
                    .forEach((contextVariable, status) -> {
                        actionStatus = status;
                        log.info("Action STATUS of {} is {}",actionDto.getName(),status);
                        fillActionExecution(testCaseExecutionId,actionDto,status,contextVariable);});
        }else{
            fillActionExecution(testCaseExecutionId,actionDto,Status.NOTSTARTED,Optional.empty());
        }
    }


    /**
     * Void method that fill the data after action was executed
     * @param testCaseExecutionId needed for fill data about action execution
     * @param actionDto needed for getting data to fill result
     * @param status needed for fill status of each action execution
     * @param contextVariable needed for fill values in variable if it isn`t empty
     */
    private void fillActionExecution(Integer testCaseExecutionId, ActionDto actionDto,
                                     Status status, Optional<ContextVariable> contextVariable) {
        actionExecutions.add(ActionExecution.builder()
                .testCaseExecutionId(testCaseExecutionId)
                .actionInstanceId(actionDto.getActionInstanceId())
                .status(status.name())
                .build());
        contextVariable.ifPresent(cv ->
                contextVariables.put(actionDto.getActionInstanceId(), cv));
    }


    private Map<String, String> variableDtosToVariableValues(List<VariableDto> variables) {
        return variables.stream().collect(Collectors.toMap(VariableDto::getName, v -> v.getDataEntry().getValue()));
    }

    /**
     * Void method that extracts statuses from list of action executions
     * @param actionExecutionList needed for extracting statuses
     * @return list of string
     */
    private List<String> statusValuesForTestExecution(List<ActionExecution> actionExecutionList){
        return actionExecutionList.stream()
                .map(ActionExecution::getStatus)
                .collect(Collectors.toList());
    }

    /**
     * @param actionExecutionList needed for creating action executions
     */
    private void createActionExecutions(List<ActionExecution> actionExecutionList){
        actionExecutionDao.addActionExecution(actionExecutionList);
    }
}