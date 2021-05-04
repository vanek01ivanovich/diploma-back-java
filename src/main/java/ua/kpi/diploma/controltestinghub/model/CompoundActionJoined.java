package ua.kpi.diploma.controltestinghub.model;

import lombok.*;

@Data
@Builder
@ToString
public class CompoundActionJoined {
    private Action action;
    private Integer priority;
}
