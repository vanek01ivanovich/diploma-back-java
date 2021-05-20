package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.dto.ActionDtoWithIdNameVoid;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ActionWithIdNameVoidMapper implements RowMapper<ActionDtoWithIdNameVoid> {
    @Override
    public ActionDtoWithIdNameVoid mapRow(ResultSet rs, int i) throws SQLException {
        return new ActionDtoWithIdNameVoid(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getBoolean("is_void")
        );
    }
}
