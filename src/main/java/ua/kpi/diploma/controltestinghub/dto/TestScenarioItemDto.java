package ua.kpi.diploma.controltestinghub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestScenarioItemDto {
    private long id;
    private String type;
    private long priority;
    private String contextInstanceName;
    private List<TestScenarioItemDto> items;

}
