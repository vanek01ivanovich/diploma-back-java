package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ActionInstanceJoinedMapper implements RowMapper<ActionInstanceJoined> {

    @Override
    public ActionInstanceJoined mapRow(ResultSet resultSet, int i) throws SQLException {
        ActionInstanceJoined ai = ActionInstanceJoined.builder()

                .id(resultSet.getInt("action_instance_id"))
                .testScenarioId(resultSet.getInt("test_scenario_id"))
                .priority(resultSet.getInt("priority"))

                .action(Action.builder().actionId(resultSet.getInt("action_id"))
                        .actionName(resultSet.getString("action_name"))
                        .actionDescription(resultSet.getString("action_description")).build())

                .variable(Variable.builder()
                        .id(resultSet.getInt("variable_id"))
                        .name(resultSet.getString("variable_name")).build())
                //.compoundInstance(null)

                .build();
        // needs refactoring and checking if exception can be thrown
        if (resultSet.getLong("compound_instance_id") != 0) {
            ai.setCompoundInstance(CompoundInstance.builder()
                    .id(resultSet.getInt("compound_instance_id"))
                    .compound(Compound.builder().id(resultSet.getInt("compound_id"))
                            .name(resultSet.getString("compound_name"))
                            .description(resultSet.getString("compound_description")).build())
                    .priority(resultSet.getInt("compound_priority")).build());

        }
        return ai;
    }
}
