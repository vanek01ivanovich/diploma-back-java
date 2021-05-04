package ua.kpi.diploma.controltestinghub.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCaseStep {
    private TestCase testCase;
    private ActionInstanceJoined actionInstanceJoined;
    private DataEntry dataEntry;
    private String projectName;
    private String projectLink;
}
