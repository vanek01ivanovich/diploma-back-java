package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;
import ua.kpi.diploma.controltestinghub.model.ActionExecutionPassedFailed;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ActionExecutionPassedFailedMapper implements RowMapper<ActionExecutionPassedFailed> {
    @Override
    public ActionExecutionPassedFailed mapRow(ResultSet rs, int i) throws SQLException {
        return new ActionExecutionPassedFailed (
                rs.getInt(1),
                rs.getString(2)
        );
    }
}
