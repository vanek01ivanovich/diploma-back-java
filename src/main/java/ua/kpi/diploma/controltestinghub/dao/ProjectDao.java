package ua.kpi.diploma.controltestinghub.dao;

import ua.kpi.diploma.controltestinghub.dto.ProjectDto;
import ua.kpi.diploma.controltestinghub.dto.ProjectListPaginationDto;
import ua.kpi.diploma.controltestinghub.model.Project;

import java.util.List;

public interface ProjectDao {
    List<Project> findAll(ProjectListPaginationDto pagination);

    Project getProjectById(Integer id);

    ProjectDto getProjectDtoById(Integer id);

    void update(Project project);

    Integer countProjects(ProjectListPaginationDto pagination);

    void insert(Project project);

    void updateIsArchivedField(Integer projectId, boolean isArchived);

}
