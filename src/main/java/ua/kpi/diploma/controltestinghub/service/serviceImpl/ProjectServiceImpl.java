package ua.kpi.diploma.controltestinghub.service.serviceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectDAO projectDAO;
    private final Pagination pagination;

    public ProjectServiceImpl(ProjectDAO projectDAO, Pagination pagination) {
        this.projectDAO = projectDAO;
        this.pagination = pagination;
    }

    /**
     * @param pagination object that includes fields for pagination.
     * @return list of Project objects from database.
     */
    @Override
    public List<Project> getAllProjects(ProjectListPaginationDto pagination) {
        List<Project> projects = projectDAO.findAll(pagination);
        if(projects.isEmpty()) {
            log.warn("IN getAllProjects - no projects found with pagination: {}", pagination);
            return projects;
        }
        log.info("IN getAllProjects - {} projects found with pagination: {}", projects.size(), pagination);
        return projects;
    }

    @Override
    public Project getProjectById(Long id) {
        return projectDAO.getProjectById(id);
    }

    @Override
    public ProjectDto getProjectDtoById(Long id) {
        return projectDAO.getProjectDtoById(id);
    }

    @Override
    public Integer countPages(ProjectListPaginationDto pagination, Integer pageSize) {
        return this.pagination.countPages(projectDAO.countProjects(pagination), pageSize);
    }

    @Override
    public void createProject(Project project) {
        projectDAO.insert(project);
        log.info("Project created: {}", project);
    }

    @Override
    public void updateProject(Project project) {
        projectDAO.update(project);
        log.info("Project updated: {}", project);
    }

    @Override
    public void archiveProject(Long projectId) {
        projectDAO.updateIsArchivedField(projectId, true);
        log.info("Project {} archived", projectId);
    }

    @Override
    public void unarchiveProject(Long projectId) {
        projectDAO.updateIsArchivedField(projectId, false);
        log.info("Project {} unarchived", projectId);
    }
}
