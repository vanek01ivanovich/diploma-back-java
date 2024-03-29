package ua.kpi.diploma.controltestinghub.service;


import ua.kpi.diploma.controltestinghub.model.DataEntry;

import java.util.List;

public interface DataEntryService {
    List<DataEntry> getDataEntryByDataSetName(Integer dataSetId);

    void updateDataEntry(List<DataEntry> dataEntryList);

    void deleteDataEntryValueById(Integer dataEntryId);

    void createDataEntry(Integer dataSetId, List<DataEntry> dataSetValues);

    List<DataEntry> getAllByDataSetId(Integer dataSetId);

    void deleteDataEntry(Integer dataSetId);
}
