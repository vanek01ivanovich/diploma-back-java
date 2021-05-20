package ua.kpi.diploma.controltestinghub.mapper;


import org.springframework.jdbc.core.RowMapper;
import ua.kpi.diploma.controltestinghub.dto.CompoundDtoWithIdName;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompoundWithIdNameMapper implements RowMapper<CompoundDtoWithIdName> {
    @Override
    public CompoundDtoWithIdName mapRow(ResultSet rs, int i) throws SQLException {
        return new CompoundDtoWithIdName(
                rs.getInt("id"),
                rs.getString("name")
        );
    }
}
