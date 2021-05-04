package ua.kpi.diploma.controltestinghub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestScenario {

    private long id;
    private String name;
    private boolean isArchived;

    public TestScenario(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
