package ua.kpi.diploma.controltestinghub.dto;

import lombok.Builder;
import lombok.Data;
import ua.kpi.diploma.controltestinghub.model.User;

@Data
@Builder
public class ProjectDto {
    private Integer id;
    private String name;
    private String link;
    private boolean isArchived;
    private User user;

}
