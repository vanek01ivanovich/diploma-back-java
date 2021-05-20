package ua.kpi.diploma.controltestinghub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestCaseTopSubscribed {
    private Integer id;
    private String name;
    private Integer projectId;
    private String projectName;
    private Integer quantitySubscribers;
}
