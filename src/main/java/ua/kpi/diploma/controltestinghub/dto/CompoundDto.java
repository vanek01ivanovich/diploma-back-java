package ua.kpi.diploma.controltestinghub.dto;

import lombok.*;
import ua.kpi.diploma.controltestinghub.model.ActionCompound;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CompoundDto {
    long id;
    String name;
    String description;
    List<ActionCompound> actionList;

}
