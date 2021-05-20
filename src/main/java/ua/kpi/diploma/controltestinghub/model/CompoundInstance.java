package ua.kpi.diploma.controltestinghub.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CompoundInstance {

    private Integer id;
    private Integer priority;
    private Compound compound;

}
