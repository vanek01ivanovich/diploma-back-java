package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.dto.ActionVariableDto;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ActionVariableMapper implements RowMapper<ActionVariableDto> {
    /**
     * @param resultSet contains result from DB
     * @param i integer
     * @return ActionVariableDto
     * @throws SQLException throw SQLException
     */
    @Override
    public ActionVariableDto mapRow(ResultSet resultSet, int i) throws SQLException {
        return ActionVariableDto.builder()
                .actionId(resultSet.getInt(1))
                .actionName(resultSet.getString(2))
                .actionDescription(resultSet.getString(3))
                .variableName(resultSet.getString(4))
                .build();
    }
}
