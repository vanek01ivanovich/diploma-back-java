package ua.kpi.diploma.controltestinghub.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kpi.diploma.controltestinghub.dto.CreateUpdateTestCaseDto;
import ua.kpi.diploma.controltestinghub.dto.ScenarioStepDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseDto;
import ua.kpi.diploma.controltestinghub.dto.TestCaseWithUserDto;
import ua.kpi.diploma.controltestinghub.exception.ValidationException;
import ua.kpi.diploma.controltestinghub.model.TestCaseTopSubscribed;
import ua.kpi.diploma.controltestinghub.model.TestCaseUpd;
import ua.kpi.diploma.controltestinghub.service.TestCaseService;
import ua.kpi.diploma.controltestinghub.util.Pageable;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class TestCaseServiceImpl implements TestCaseService {


    @Override
    public void createTestCase(CreateUpdateTestCaseDto createUpdateTestCaseDto, Integer userId) {

    }

    @Override
    public List<ScenarioStepDto> getTestScenarioStep(Integer testCaseId) {
        return null;
    }

    @Override
    public TestCaseDto getTestCase(Integer testCaseId) {
        return null;
    }

    @Override
    public List<TestCaseWithUserDto> getTestCasesWithUser(Integer projectID, Pageable pageable, String name) throws ValidationException {
        return null;
    }

    @Override
    public List<TestCaseUpd> getAllTestCases() {
        return null;
    }

    @Override
    public Integer countTestCasesByProject(Integer pageSize, Long projectId) {
        return null;
    }

    @Override
    public List<TestCaseTopSubscribed> getFiveTopSubscribedTestCases() {
        return null;
    }

    @Override
    public void updateTestCase(CreateUpdateTestCaseDto createUpdateTestCaseDto) {

    }

    @Override
    public void addSubscriber(Integer testCaseId, Integer userId) {

    }

    @Override
    public Boolean isFollowedByUser(Integer testCaseId, Integer userId) {
        return null;
    }

    @Override
    public void removeSubscriber(Integer testCaseId, Integer userId) {

    }

    @Override
    public void archiveTestCase(Integer testCaseId) {

    }

    @Override
    public void unarchiveTestCase(Integer testCaseId) {

    }
}
