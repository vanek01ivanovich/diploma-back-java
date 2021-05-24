package ua.kpi.diploma.controltestinghub.service;


import ua.kpi.diploma.controltestinghub.dto.ProjectDto;
import ua.kpi.diploma.controltestinghub.dto.ProjectListPaginationDto;
import ua.kpi.diploma.controltestinghub.model.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects(ProjectListPaginationDto pagination);

    Project getProjectById(Integer id);

    ProjectDto getProjectDtoById(Integer id);

    Integer countPages(ProjectListPaginationDto pagination, Integer pageSize);

    void createProject(Project project);

    void updateProject(Project project);

    void archiveProject(Integer projectId);

    void unarchiveProject(Integer projectId);
}
