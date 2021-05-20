package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.dto.CompoundDto;
import ua.kpi.diploma.controltestinghub.model.ActionCompound;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
@Component
public class CompoundActionListMapper implements RowMapper<CompoundDto> {

    private ArrayList<ActionCompound> getActionList(ResultSet rs) throws SQLException {
        ArrayList<ActionCompound> actionList = new ArrayList<>();
        do{
            actionList.add(new ActionCompound(rs.getLong("action_id"), rs.getInt("priority")));
        } while(rs.next());
        return actionList;
    }

    @Override
    public CompoundDto mapRow(ResultSet resultSet, int i) throws SQLException {
        return CompoundDto.builder()
                .actionList(getActionList(resultSet))
                .build();
    }
}
