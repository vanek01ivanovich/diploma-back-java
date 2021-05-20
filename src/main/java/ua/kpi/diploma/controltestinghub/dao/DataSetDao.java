package ua.kpi.diploma.controltestinghub.dao;

import ua.kpi.diploma.controltestinghub.model.DataSet;

import java.util.List;

public interface DataSetDao {
    DataSet getDataSetById(Integer dataSetId);

    List<DataSet> getAllDataSet();

    void updateDataSet(DataSet editedDataSet);

    Integer createDataSet(String name);

    List<DataSet> getAll();

    int deleteDataSet(Integer id);
}
