package ua.kpi.diploma.controltestinghub.service;

import ua.kpi.diploma.controltestinghub.model.DataSet;

import java.util.List;

public interface DataSetService {

    DataSet getDataSetById(Integer id);

    void updateDataSet(DataSet editedDataset);

    Integer createDataSet(String name);

    List<DataSet> getAllDataSet();

    List<DataSet> getAll();

    int deleteDataSet(Integer id);
}
