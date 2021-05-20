package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.model.ActionInstanceJoined;
import ua.kpi.diploma.controltestinghub.model.DataEntry;
import ua.kpi.diploma.controltestinghub.model.TestCase;
import ua.kpi.diploma.controltestinghub.model.TestCaseStep;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Component
public class TestCaseStepMapper implements RowMapper<TestCaseStep> {

    private final ActionInstanceJoinedMapper actionInstanceJoinedMapper;

    public TestCaseStepMapper(ActionInstanceJoinedMapper actionInstanceJoinedMapper) {
        this.actionInstanceJoinedMapper = actionInstanceJoinedMapper;
    }

    @Override
    public TestCaseStep mapRow(ResultSet resultSet, int i) throws SQLException {
        ActionInstanceJoined ai = actionInstanceJoinedMapper.mapRow(resultSet, i);
        if (ai != null) {
            ai.setVariableValueId(resultSet.getInt("variable_value_id"));
        }
        return TestCaseStep.builder()
                .testCase(TestCase.builder()
                        .userId(resultSet.getInt("test_case_user_id"))
                        .projectId(resultSet.getInt("test_case_project_id"))
                        .dataSetId(resultSet.getInt("test_case_data_set_id"))
                        .testScenarioId(resultSet.getInt("test_case_test_scenario_id"))
                        .name(resultSet.getString("test_case_name"))
                        .id(resultSet.getInt("test_case_id"))
                        .isArchived(resultSet.getBoolean("test_case_is_archived"))
                        .build())
                .projectLink(resultSet.getString("test_case_project_link"))
                .projectName(resultSet.getString("test_case_project_name"))
                .actionInstanceJoined(ai).dataEntry(
                        DataEntry.builder()
                                .id(resultSet.getInt("data_entry_id"))
                                .data_set_id(resultSet.getInt("data_set_id"))
                                .value(resultSet.getString("data_entry_value"))
                                .key(resultSet.getString("data_entry_key"))
                                .build())
                .build();
    }
}
