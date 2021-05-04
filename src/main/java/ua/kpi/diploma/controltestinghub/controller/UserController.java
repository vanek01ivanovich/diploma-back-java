package ua.kpi.diploma.controltestinghub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kpi.diploma.controltestinghub.model.User;
import ua.kpi.diploma.controltestinghub.service.serviceImpl.UserServiceImpl;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getPageUsers(){
        return null;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        return userService.getUserById(id);
    }

    @PutMapping("/update")
    public void updateUserById(@RequestBody User user){
        userService.updateUserById(user);
    }

    @PostMapping("/add")
    public void addUser(@RequestBody User user){
        //TODO make password
        userService.saveUser(user);
        //TODO email credentials
    }

    @GetMapping("/pages/count")
    public Integer countUserPages(Integer pageSize) {
        return userService.countPages(pageSize);
    }

    @GetMapping("/check-email/{email}")
    public Boolean checkIfEmailExists(@PathVariable String email){
        return userService.checkIfEmailExists(email);
    }

    /*@GetMapping("/pages/count-search")
    public Integer countUserPagesSearch(UserSearchDto userSearchDto, Integer pageSize) {
        return userService.countPagesSearch(userSearchDto, pageSize);
    }*/





}
