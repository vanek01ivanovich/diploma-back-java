package ua.kpi.diploma.controltestinghub.service.serviceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dao.ProjectDao;
import ua.kpi.diploma.controltestinghub.dto.ProjectDto;
import ua.kpi.diploma.controltestinghub.dto.ProjectListPaginationDto;
import ua.kpi.diploma.controltestinghub.model.Project;
import ua.kpi.diploma.controltestinghub.service.ProjectService;
import ua.kpi.diploma.controltestinghub.util.Pagination;


import java.util.List;


@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectDao projectDAO;
    private final Pagination pagination;

    public ProjectServiceImpl(ProjectDao projectDAO, Pagination pagination) {
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
    public Project getProjectById(Integer id) {
        return projectDAO.getProjectById(id);
    }

    @Override
    public ProjectDto getProjectDtoById(Integer id) {
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
    public void archiveProject(Integer projectId) {
        projectDAO.updateIsArchivedField(projectId, true);
        log.info("Project {} archived", projectId);
    }

    @Override
    public void unarchiveProject(Integer projectId) {
        projectDAO.updateIsArchivedField(projectId, false);
        log.info("Project {} unarchived", projectId);
    }
}
