package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.model.Compound;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CompoundMapper implements RowMapper<Compound> {

    @Override
    public Compound mapRow(ResultSet rs, int i) throws SQLException {
        return new Compound(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description")
        );
    }
}
