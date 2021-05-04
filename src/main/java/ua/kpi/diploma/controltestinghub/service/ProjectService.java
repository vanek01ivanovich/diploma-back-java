package ua.kpi.diploma.controltestinghub.service;


import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects(ProjectListPaginationDto pagination);

    Project getProjectById(Long id);

    ProjectDto getProjectDtoById(Long id);

    Integer countPages(ProjectListPaginationDto pagination, Integer pageSize);

    void createProject(Project project);

    void updateProject(Project project);

    void archiveProject(Long projectId);

    void unarchiveProject(Long projectId);
}
