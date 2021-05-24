package ua.kpi.diploma.controltestinghub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kpi.diploma.controltestinghub.config.JwtProvider;
import ua.kpi.diploma.controltestinghub.dto.ActionExecutionDto;
import ua.kpi.diploma.controltestinghub.model.User;
import ua.kpi.diploma.controltestinghub.service.ActionExecutionService;
import ua.kpi.diploma.controltestinghub.service.UserService;
import ua.kpi.diploma.controltestinghub.util.Pageable;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/list/actions-execution")
@Slf4j
public class ActionExecutionController {

    private final ActionExecutionService actionExecutionService;
    //private final SseService sseService;
    private final UserService userService;

    @Autowired
    public ActionExecutionController(ActionExecutionService actionExecutionService,
                                     /*SseService sseService, */UserService userService){
        this.actionExecutionService = actionExecutionService;
        //this.sseService = sseService;
        this.userService = userService;
    }

    /**
     * Returns the List of ActionExecutionDto with pagination
     * using Pageable class
     * @param testCaseExecutionId the id, which helps to get action executions by testCaseExecutionId
     * @param page  current page of the pagination
     * @param orderSearch  order in which query in DB will be executed
     * @param orderSort using in query for sorting info from query in DB
     * @param pageSize  current size of elements on one page
     * @param search an item that used in DB query for searching info
     * @param jwt used for resolving token
     * @return list of ActionExecutionDto with pagination
     */
    @GetMapping("/{testCaseExecutionId}")
    public List<ActionExecutionDto> getAllActionExecutions(@PathVariable Integer testCaseExecutionId,
                                                           Integer page, String orderSearch,
                                                           String orderSort, Integer pageSize, String search,
                                                           @RequestHeader("Authorization") String jwt){
        JwtProvider jwtProvider = new JwtProvider();
        String email = jwtProvider.getUserNameFromJwtToken(jwtProvider.resolveStringToken(jwt));
        User user = userService.getUserByEmail(email);
        log.info("User from jwt token : {}", user);
        //sseService.deleteNotification(testCaseExecutionId, user.getId());
        Pageable pageable = Pageable.builder().page(page).pageSize(pageSize).sortField(orderSearch)
                                                       .sortOrder(orderSort).search(search).build();
        log.info("Pageable : {}", pageable);
        return actionExecutionService.getAllActionExecutions(testCaseExecutionId,pageable);
    }

    /**
     * Returns the Integer of Quantity action executions
     * @param testCaseExecutionId the id, which helps to get action executions by testCaseExecutionId
     * @param search an item that used in DB query for searching info
     * @return the Integer of Quantity action executions
     */
    @GetMapping("/quantity/{testCaseExecutionId}")
    public Integer getQuantityActionsExecutions(@PathVariable Integer testCaseExecutionId,String search){
        return actionExecutionService.getQuantityActionsExecutions(testCaseExecutionId,search);
    }
}
