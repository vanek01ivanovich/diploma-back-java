package ua.kpi.diploma.controltestinghub.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kpi.diploma.controltestinghub.model.DataSet;
import ua.kpi.diploma.controltestinghub.service.DataSetService;

import java.util.List;

@Service
public class DataSetServiceImpl implements DataSetService {


    @Override
    public DataSet getDataSetById(Integer id) {
        return null;
    }

    @Override
    public void updateDataSet(DataSet editedDataset) {

    }

    @Override
    public Integer createDataSet(String name) {
        return null;
    }

    @Override
    public List<DataSet> getAllDataSet() {
        return null;
    }

    @Override
    public List<DataSet> getAll() {
        return null;
    }

    @Override
    public int deleteDataSet(Integer id) {
        return 0;
    }
}
