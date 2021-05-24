package ua.kpi.diploma.controltestinghub.execution.action;

import org.openqa.selenium.WebDriver;
import ua.kpi.diploma.controltestinghub.model.Status;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ActionExecutable {

    Map<Optional<ContextVariable>, Status> executeAction(WebDriver driver, Map<String, String> variableValues);
}
