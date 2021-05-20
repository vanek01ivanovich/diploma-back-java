package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.dto.ActionExecutionDto;
import ua.kpi.diploma.controltestinghub.model.Action;
import ua.kpi.diploma.controltestinghub.model.DataEntry;
import ua.kpi.diploma.controltestinghub.model.Variable;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ActionExecutionMapper implements RowMapper<ActionExecutionDto> {

    /**
     * @param resultSet contains result from DB
     * @param i integer
     * @return ActionExecutionDto
     * @throws SQLException throw SQLException
     */
    @Override
    public ActionExecutionDto mapRow(ResultSet resultSet, int i) throws SQLException {
        return ActionExecutionDto.builder()
                .id(resultSet.getInt("action_execution_id"))
                .action(Action.builder()
                        .actionId(resultSet.getInt("action_id"))
                        .actionName(resultSet.getString("action_name"))
                        .actionDescription(resultSet.getString("action_description"))
                        .isVoid(resultSet.getBoolean("action_is_void"))
                        .build())
                .variable(Variable.builder()
                        .name(resultSet.getString("variable_name"))
                        .build())
                .dataEntry(DataEntry.builder()
                        .key(resultSet.getString("variable_key"))
                        .value(resultSet.getString("variable_value"))
                        .build())
                .testcaseId(resultSet.getInt("test_case_id"))
                .testcaseExecutionId(resultSet.getInt("test_case_execution_id"))
                .status(resultSet.getString("status"))
                .build();
    }
}
