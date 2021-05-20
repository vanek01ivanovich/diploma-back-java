package ua.kpi.diploma.controltestinghub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.kpi.diploma.controltestinghub.dto.CreateUpdateTestCaseDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseWithUserDto;
import ua.kpi.diploma.controltestinghub.exception.ValidationException;
import ua.kpi.diploma.controltestinghub.model.TestCaseUpd;
import ua.kpi.diploma.controltestinghub.service.TestCaseExecService;
import ua.kpi.diploma.controltestinghub.service.TestCaseService;
import ua.kpi.diploma.controltestinghub.service.serviceImpl.UserPrincipal;
import ua.kpi.diploma.controltestinghub.util.Pageable;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/test-case")
public class TestCaseController {
    private final TestCaseService testCaseService;
    private final TestCaseExecService testCaseExecutionService;

    @Autowired
    public TestCaseController(TestCaseService testCaseService, TestCaseExecService testCaseExecutionService) {
        this.testCaseService = testCaseService;
        this.testCaseExecutionService = testCaseExecutionService;
    }

    @PostMapping()
    public void createTestCase(@RequestBody CreateUpdateTestCaseDto createUpdateTestCaseDto) {
        Integer userId = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId();
        testCaseService.createTestCase(createUpdateTestCaseDto, userId);
    }

    @GetMapping("/list")
    public List<TestCaseUpd> getAllTestCases() {
        return testCaseService.getAllTestCases();

    }
    @GetMapping("/{id}")
    public TestCaseDto getById(@PathVariable("id") Integer testCaseId) {
        return testCaseService.getTestCase(testCaseId);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Integer testCaseId, @RequestBody CreateUpdateTestCaseDto createUpdateTestCaseDto) {
        createUpdateTestCaseDto.setId(testCaseId);
        testCaseService.updateTestCase(createUpdateTestCaseDto);
    }

    @PatchMapping("/{id}/follow")
    public void follow(@PathVariable("id") Integer testCaseId){
        Integer userId = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId();
        testCaseService.addSubscriber(testCaseId, userId);
    }

    @PatchMapping("/{id}/unfollow")
    public void unfollow(@PathVariable("id") Integer testCaseId){
        Integer userId = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId();
        testCaseService.removeSubscriber(testCaseId, userId);
    }

    @PatchMapping("/{id}/archive")
    public void archiveTestCase(@PathVariable("id") Integer testCaseId){
        testCaseService.archiveTestCase(testCaseId);
    }

    @PatchMapping("/{id}/unarchive")
    public void unarchiveTestCase(@PathVariable("id") Integer testCaseId){
        testCaseService.unarchiveTestCase(testCaseId);
    }

    @GetMapping("/{id}/is-followed")
    public Boolean isFollowed(@PathVariable("id") Integer testCaseId) {
        Integer userId = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId();
        return testCaseService.isFollowedByUser(testCaseId, userId);
    }

    @GetMapping("/execute/{id}")
    public void execute(@PathVariable("id") Integer id) {
        TestCaseDto testCaseDto =  testCaseService.getTestCase(id);
        System.out.println("Execute testCaseDto " + testCaseDto);
        //System.out.println(testCaseExecutionService.executeTestCase(testCaseDto,60L));
    }


    @GetMapping("/{projectID}/pages/count")
    public Integer countTestCasePages(Integer pageSize, @PathVariable("projectID") Long projectId) {
        return testCaseService.countTestCasesByProject(pageSize, projectId  );
    }

    @GetMapping("/{projectID}/list/page-upd")
    public List<TestCaseWithUserDto> getPageTestCasesWithUser(@PathVariable("projectID") Integer projectID, Integer pageSize, Integer page, String sortOrder, String sortField,
                                                              String name) throws ValidationException {
        Pageable pageable = Pageable.builder().page(page).pageSize(pageSize).sortField(sortField).sortOrder(sortOrder).build();
        return testCaseService.getTestCasesWithUser(projectID, pageable, name);
    }
}
