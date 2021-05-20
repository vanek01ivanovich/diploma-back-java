package ua.kpi.diploma.controltestinghub.dto;

import lombok.*;
import ua.kpi.diploma.controltestinghub.model.DataEntry;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class VariableDto {
    private Integer id;
    private String name;
    /**
     * is null for test scenario
     */
    private DataEntry dataEntry;
    private Integer variableValueId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariableDto that = (VariableDto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
