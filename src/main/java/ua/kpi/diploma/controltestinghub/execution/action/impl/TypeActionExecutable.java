package ua.kpi.diploma.controltestinghub.execution.action.impl;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import ua.kpi.diploma.controltestinghub.execution.action.ActionExecutable;
import ua.kpi.diploma.controltestinghub.execution.action.ContextVariable;
import ua.kpi.diploma.controltestinghub.model.Status;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class TypeActionExecutable implements ActionExecutable {

    private final Map<Optional<ContextVariable>, Status> resultActionExecution = new HashMap<>();

    /**
     * @param driver needed for selenium
     * @param variableValues needed for variable value
     * @return map of status and contextVariable
     */
    @Override
    public Map<Optional<ContextVariable>, Status> executeAction(WebDriver driver, Map<String, String> variableValues) {
        System.out.println("input class");
        System.out.println(variableValues.toString());
        Status actionExecution;
        final String INPUT_ELEMENT = "input xpath";
        try {
            String TEXT = "text";
            driver.findElement(By.xpath(variableValues.get(INPUT_ELEMENT)))
                    .sendKeys(variableValues.get(TEXT));
            actionExecution = Status.PASSED;
        }catch (WebDriverException exception){
            log.error("Error with element like {} ",exception.getMessage());
            actionExecution = Status.FAILED;
        }
        resultActionExecution.put(Optional.empty(), actionExecution);
        return resultActionExecution;
    }
}
