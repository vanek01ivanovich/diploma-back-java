package ua.kpi.diploma.controltestinghub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestScenarioDtoWithIdNameArchived {
    private Integer id;
    private String name;
    private boolean archived;
}
