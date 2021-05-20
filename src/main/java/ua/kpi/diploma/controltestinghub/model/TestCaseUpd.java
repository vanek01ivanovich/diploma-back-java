package ua.kpi.diploma.controltestinghub.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TestCaseUpd {
    private Integer id;
    private String name;
}
