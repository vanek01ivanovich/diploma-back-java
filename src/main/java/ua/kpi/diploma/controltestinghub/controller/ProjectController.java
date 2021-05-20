package ua.kpi.diploma.controltestinghub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.kpi.diploma.controltestinghub.dto.ProjectDto;
import ua.kpi.diploma.controltestinghub.dto.ProjectListPaginationDto;
import ua.kpi.diploma.controltestinghub.model.Project;
import ua.kpi.diploma.controltestinghub.service.ProjectService;
import ua.kpi.diploma.controltestinghub.service.serviceImpl.UserPrincipal;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/projects")
@Slf4j
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> projects(@RequestParam String link,
                                      @RequestParam String name,
                                      @RequestParam Boolean isArchived,
                                      @RequestParam Integer page,
                                      @RequestParam Integer pageSize,
                                      @RequestParam String sortOrder,
                                      @RequestParam String sortField) {
        ProjectListPaginationDto pagination = new ProjectListPaginationDto(
                name, link, isArchived, page, pageSize, sortOrder, sortField
        ).setPage(page);
        return ResponseEntity.ok(projectService.getAllProjects(pagination));
    }

    @GetMapping("/pages/count")
    public Integer countUserPages(@RequestParam String link,
                                  @RequestParam String name,
                                  @RequestParam Boolean isArchived,
                                  @RequestParam Integer pageSize) {
        ProjectListPaginationDto pagination = new ProjectListPaginationDto(
                name, link, isArchived
        );
        return projectService.countPages(pagination, pageSize);
    }

    @GetMapping("/{id}")
    public ProjectDto getProject(@PathVariable("id") Long id){
        return projectService.getProjectDtoById(id);

    }

    @PostMapping()
    public void createProject(@RequestBody Project project){
        Integer userId = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId();
        project.setUserId(userId);
        projectService.createProject(project);
    }

    @PutMapping("/{id}")
    public void updateProject(@PathVariable("id") Integer projectId, @RequestBody Project project){
        project.setId(projectId);
        projectService.updateProject(project);
    }

    @PatchMapping("/{id}/archive")
    public void archiveProject(@PathVariable("id") Long projectId){
        projectService.archiveProject(projectId);
    }

    @PatchMapping("/{id}/unarchive")
    public void unarchiveProject(@PathVariable("id") Long projectId){
        projectService.unarchiveProject(projectId);
    }

}
