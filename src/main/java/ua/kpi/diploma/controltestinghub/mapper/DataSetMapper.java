package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.model.DataSet;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DataSetMapper implements RowMapper<DataSet> {

    /**
     * @param resultSet contains result from DB
     * @param i integer
     * @return DataSet
     * @throws SQLException throw SQLException
     */
    @Override
    public DataSet mapRow(ResultSet resultSet, int i) throws SQLException {
        return DataSet.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
    }
}
