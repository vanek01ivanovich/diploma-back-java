package ua.kpi.diploma.controltestinghub.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TestCaseUpd {
    private Long id;
    private String name;
}
