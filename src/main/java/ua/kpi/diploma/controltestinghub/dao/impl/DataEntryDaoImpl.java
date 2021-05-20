package ua.kpi.diploma.controltestinghub.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.kpi.diploma.controltestinghub.dao.DataEntryDao;
import ua.kpi.diploma.controltestinghub.mapper.DataEntryMapper;
import ua.kpi.diploma.controltestinghub.model.DataEntry;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataEntryDaoImpl implements DataEntryDao {

    private final JdbcTemplate jdbcTemplate;
    private final DataEntryMapper dataEntryMapper;

    @Value("${get.data.entry.by.data.set.id}")
    private String GET_DATA_ENTRY_FOR_EDIT;

    @Value("${update.data.entry}")
    private String UPDATE_DATA_ENTRY;

    @Value("${insert.data.entry.default}")
    private String INSERT_DATA_ENTRY_DEFAULT;

    @Value("${delete.data.entry.by.id}")
    private String DELETE_DATA_ENTRY_BY_ID;

    @Value("${get.data.entries.by.dataset.id}")
    private String GET_BY_DATA_SET_ID;

    @Value("${delete.data.entry}")
    private String DELETE_DATA_ENTRY;

    @Autowired
    public DataEntryDaoImpl(JdbcTemplate jdbcTemplate,DataEntryMapper dataEntryMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.dataEntryMapper = dataEntryMapper;
    }

    /**
     * @param dataEntryValues contain dataEntry for creating
     */
    @Override
    public void createDataEntry(List<DataEntry> dataEntryValues) {
        jdbcTemplate.batchUpdate(INSERT_DATA_ENTRY_DEFAULT, dataEntryValues, dataEntryValues.size(), (ps, dataEntryValue) -> {
            ps.setLong(1, dataEntryValue.getData_set_id());
            ps.setString(2, dataEntryValue.getValue());
            ps.setString(3, dataEntryValue.getKey());
        });
    }

    /**
     * @param dataSetId needed for getting data from DB using dataSetId
     * @return list of dataEntry
     */
    @Override
    public List<DataEntry> getDataEntryByDataSetName(Integer dataSetId) {
        return jdbcTemplate.queryForStream(GET_DATA_ENTRY_FOR_EDIT,dataEntryMapper,dataSetId).collect(Collectors.toList());
    }

    /**
     * @param dataEntryList contains data for updating dataEntry
     */
    @Override
    public void updateDataEntry(List<DataEntry> dataEntryList) {
        jdbcTemplate.batchUpdate(UPDATE_DATA_ENTRY, dataEntryList, dataEntryList.size(), (ps, dataEntryValue) -> {
            if (dataEntryValue.getId() != null) {
                ps.setString(1, dataEntryValue.getValue());
                ps.setString(2, dataEntryValue.getKey());
                ps.setLong(3, dataEntryValue.getId());
            }
        });
    }

    /**
     * @param dataEntryId id for deleting data from DB
     */
    @Override
    public void deleteDataEntryValueById(Integer dataEntryId) {
        jdbcTemplate.update(DELETE_DATA_ENTRY_BY_ID, dataEntryId);
    }


    @Override
    public void createDataEntry(Long dataSetId, List<DataEntry> dataSetValues) {
        String sql = "insert into data_entry (data_set_id, value, key) values (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, dataSetValues, dataSetValues.size(), (ps, dataSetValue) -> {
            ps.setLong(1, dataSetId);
            ps.setString(2, dataSetValue.getValue());
            ps.setString(3, dataSetValue.getKey());
        });
    }


    @Override
    public void deleteDataEntry(Integer dataSetId) {
        jdbcTemplate.update(DELETE_DATA_ENTRY, dataSetId);
    }

    @Override
    public List<DataEntry> getAllByDataSetId(Integer dataSetId) {
        return jdbcTemplate.queryForStream(GET_BY_DATA_SET_ID, dataEntryMapper, dataSetId).collect(Collectors.toList());
    }
}
