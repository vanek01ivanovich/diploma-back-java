package ua.kpi.diploma.controltestinghub.dto;

import lombok.Data;
import ua.kpi.diploma.controltestinghub.model.DataEntry;
import java.util.List;

@Data
public class DataSetDto {
    String dataSetName;
    List<DataEntry> dataEntryValues;
}
