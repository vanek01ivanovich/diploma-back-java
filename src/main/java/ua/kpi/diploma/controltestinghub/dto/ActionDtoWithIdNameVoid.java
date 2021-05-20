package ua.kpi.diploma.controltestinghub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionDtoWithIdNameVoid {
    private Integer id;
    private String name;
    private boolean isVoid;
}
