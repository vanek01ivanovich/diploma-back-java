package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.model.DataEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DataEntryMapper implements RowMapper<DataEntry> {
    @Override
    public DataEntry mapRow(ResultSet resultSet, int i) throws SQLException {
        return DataEntry.builder()
                .id(resultSet.getInt("id"))
                .key(resultSet.getString("key"))
                .data_set_id(resultSet.getInt("data_set_id"))
                .value(resultSet.getString("value"))
                .key(resultSet.getString("key"))
                .build();
    }
}
