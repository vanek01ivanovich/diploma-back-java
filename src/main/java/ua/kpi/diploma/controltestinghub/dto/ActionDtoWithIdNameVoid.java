package ua.kpi.diploma.controltestinghub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionDtoWithIdNameVoid {
    private long id;
    private String name;
    private boolean isVoid;
}
