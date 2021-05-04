package ua.kpi.diploma.controltestinghub.dto;

import lombok.*;
import ua.netcracker.group3.automaticallytesting.model.Compound;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScenarioStepDto {

    private Integer priority;
    /**
     * can be null
     */
    private Compound compound;
    /**
     * consists of only one action if compound is null
     * SORTED by priority!
     */
    private List<ActionDto> actionDto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScenarioStepDto that = (ScenarioStepDto) o;
        return Objects.equals(priority, that.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priority);
    }
}
