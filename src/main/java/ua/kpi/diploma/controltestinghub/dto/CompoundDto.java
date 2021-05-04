package ua.kpi.diploma.controltestinghub.dto;

import lombok.*;
import ua.netcracker.group3.automaticallytesting.model.ActionComp;

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
    List<ActionComp> actionList;

}
