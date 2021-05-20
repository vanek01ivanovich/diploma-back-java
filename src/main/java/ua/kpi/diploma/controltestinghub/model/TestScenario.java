package ua.kpi.diploma.controltestinghub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestScenario {

    private Integer id;
    private String name;
    private boolean isArchived;

    public TestScenario(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
