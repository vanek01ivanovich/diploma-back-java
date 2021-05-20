package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.kpi.diploma.controltestinghub.model.CompoundActionWithActionIdAndPriority;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompoundActionWithActionIdAndPriorityMapper implements RowMapper<CompoundActionWithActionIdAndPriority> {
    @Override
    public CompoundActionWithActionIdAndPriority mapRow(ResultSet rs, int i) throws SQLException {
        return new CompoundActionWithActionIdAndPriority(
                rs.getInt("action_id"),
                rs.getInt("priority")
        );
    }
}
