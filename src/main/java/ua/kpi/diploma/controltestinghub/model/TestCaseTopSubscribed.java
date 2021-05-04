package ua.kpi.diploma.controltestinghub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestCaseTopSubscribed {
    private long id;
    private String name;
    private long projectId;
    private String projectName;
    private long quantitySubscribers;
}
