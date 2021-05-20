package ua.kpi.diploma.controltestinghub.model;

import lombok.*;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TestCase {
    private Integer id;
    private String name;
    private Integer userId;
    private Integer projectId;
    private Integer testScenarioId;
    private Integer dataSetId;
    private Boolean isArchived;
}
