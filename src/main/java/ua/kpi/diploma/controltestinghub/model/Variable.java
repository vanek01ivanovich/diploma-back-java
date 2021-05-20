package ua.kpi.diploma.controltestinghub.model;

import lombok.*;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Variable {
    private Integer id;
    private String name;
    private Integer action_id;

    // TODO fix
   // private Long variableId;
   // private String variableName;
}
