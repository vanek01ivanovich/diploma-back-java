package ua.kpi.diploma.controltestinghub.service.serviceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dto.ProjectDto;
import ua.kpi.diploma.controltestinghub.dto.ProjectListPaginationDto;
import ua.kpi.diploma.controltestinghub.model.Project;
import ua.kpi.diploma.controltestinghub.service.ProjectService;


import java.util.List;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {


    @Override
    public List<Project> getAllProjects(ProjectListPaginationDto pagination) {
        return null;
    }

    @Override
    public Project getProjectById(Long id) {
        return null;
    }

    @Override
    public ProjectDto getProjectDtoById(Long id) {
        return null;
    }

    @Override
    public Integer countPages(ProjectListPaginationDto pagination, Integer pageSize) {
        return null;
    }

    @Override
    public void createProject(Project project) {

    }

    @Override
    public void updateProject(Project project) {

    }

    @Override
    public void archiveProject(Long projectId) {

    }

    @Override
    public void unarchiveProject(Long projectId) {

    }
}
