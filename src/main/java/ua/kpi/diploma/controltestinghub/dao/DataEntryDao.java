package ua.kpi.diploma.controltestinghub.dao;

import ua.kpi.diploma.controltestinghub.model.DataEntry;

import java.util.List;

public interface DataEntryDao {
    List<DataEntry> getDataEntryByDataSetName(Integer dataSetId);

    void updateDataEntry(List<DataEntry> dataEntryList);

    void deleteDataEntryValueById(Integer dataEntryId);

    void createDataEntry(Long dataSetId, List<DataEntry> dataSetValues);

    void createDataEntry( List<DataEntry> dataSetValues);

    List<DataEntry> getAllByDataSetId(Integer dataSetId);

    void deleteDataEntry(Integer dataSetId);
}
