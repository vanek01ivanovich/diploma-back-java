package ua.kpi.diploma.controltestinghub.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dao.DataEntryDao;
import ua.kpi.diploma.controltestinghub.model.DataEntry;
import ua.kpi.diploma.controltestinghub.service.DataEntryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DataEntryServiceImpl implements DataEntryService {

    private final DataEntryDao dataEntryDAO;

    @Autowired
    public DataEntryServiceImpl(DataEntryDao dataEntryDAO) {
        this.dataEntryDAO = dataEntryDAO;
    }

    /**
     * Void method that creates DataEntry in DB
     * @param dataSetId needed for creating dataEntry
     * @param dataSetValues contains dataSetValues for creating dataEntry
     */
    @Override
    public void createDataEntry(Integer dataSetId, List<DataEntry> dataSetValues) {
        dataEntryDAO.createDataEntry(dataSetId, dataSetValues);
    }

    @Override
    public void deleteDataEntry(Integer dataSetId) {
        dataEntryDAO.deleteDataEntry(dataSetId);
    }

    /**
     * Returns the list of DataEntry that was got by dataSetId
     * @param dataSetId needed for getting dataEntry from DB by dataSetId
     * @return list of DataEntry
     */
    @Override
    public List<DataEntry> getDataEntryByDataSetName(Integer dataSetId) {
        return dataEntryDAO.getDataEntryByDataSetName(dataSetId);
    }

    /**
     * Void method that updates DataEntry using dataEntryList
     * @param dataEntryList contains value for updating DataEntry
     */
    @Override
    public void updateDataEntry(List<DataEntry> dataEntryList) {
        List<DataEntry> dataEntryForUpdate = dataEntryList
                .stream().filter(d -> d.getId() != null).collect(Collectors.toList());
        List<DataEntry> dataEntryForInsert = dataEntryList
                .stream().filter(d -> d.getId() == null).collect(Collectors.toList());
        log.info("DataEntry for update: {}",dataEntryForUpdate);
        log.info("DataEntry for insert: {}",dataEntryForInsert);
        dataEntryDAO.createDataEntry(dataEntryForInsert);
        dataEntryDAO.updateDataEntry(dataEntryForUpdate);
    }

    /**
     * Void method that deletes DataEntry using dataEntryId
     * @param dataEntryId needed for deleting dataEntry from DB
     */
    @Override
    public void deleteDataEntryValueById(Integer dataEntryId) {
        dataEntryDAO.deleteDataEntryValueById(dataEntryId);
    }

    @Override
    public List<DataEntry> getAllByDataSetId(Integer dataSetId){
        return dataEntryDAO.getAllByDataSetId(dataSetId);
    }
}