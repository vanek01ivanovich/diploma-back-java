package ua.kpi.diploma.controltestinghub.dto;

import lombok.Builder;
import lombok.Data;
import ua.netcracker.group3.automaticallytesting.model.User;

@Data
@Builder
public class ProjectDto {
    private long id;
    private String name;
    private String link;
    private boolean isArchived;
    private User user;

}
