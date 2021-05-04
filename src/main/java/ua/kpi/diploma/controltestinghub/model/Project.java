package ua.kpi.diploma.controltestinghub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    private long id;
    private String name;
    private String link;
    private boolean isArchived;
    private long userId;

}
