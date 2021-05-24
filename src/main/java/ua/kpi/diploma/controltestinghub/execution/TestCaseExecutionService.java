package ua.kpi.diploma.controltestinghub.execution;


import ua.kpi.diploma.controltestinghub.dto.TestCaseDto;

import java.util.List;
import java.util.Map;

public interface TestCaseExecutionService {

  /*  Map<Long, ContextVariable> executeTestCase(TestCaseDto testCaseDto,Long testCaseExecutionId);*/
    List<String> executeTestCase(TestCaseDto testCaseDto, Integer testCaseExecutionId);

}
