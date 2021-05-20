package ua.kpi.diploma.controltestinghub.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.model.DataEntry;
import ua.kpi.diploma.controltestinghub.service.DataEntryService;

import java.util.List;


@Service
@Slf4j
public class DataEntryServiceImpl implements DataEntryService {
    @Override
    public List<DataEntry> getDataEntryByDataSetName(Integer dataSetId) {
        return null;
    }

    @Override
    public void updateDataEntry(List<DataEntry> dataEntryList) {

    }

    @Override
    public void deleteDataEntryValueById(Integer dataEntryId) {

    }

    @Override
    public void createDataEntry(Long dataSetId, List<DataEntry> dataSetValues) {

    }

    @Override
    public List<DataEntry> getAllByDataSetId(Long dataSetId) {
        return null;
    }

    @Override
    public void deleteDataEntry(Integer dataSetId) {

    }
}