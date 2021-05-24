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
public class CheckBoxUncheckActionExecutable  implements ActionExecutable {


    @Override
    public Map<Optional<ContextVariable>, Status> executeAction(WebDriver driver, Map<String, String> variableValues) {
        String CHECKBOX = "checkbox xpath";
        try {
            if (driver.findElement(By.xpath(CHECKBOX)).isSelected()) {
                driver.findElement(By.xpath(CHECKBOX)).click();
            }
            return new HashMap<Optional<ContextVariable>, Status>() {{
                put(Optional.empty(), Status.PASSED);
            }};
        } catch (WebDriverException exception) {
            log.error("Error with element like {} ",exception.getMessage());
            return new HashMap<Optional<ContextVariable>, Status>() {{
                put(Optional.empty(), Status.FAILED);
            }};
        }
    }
}
