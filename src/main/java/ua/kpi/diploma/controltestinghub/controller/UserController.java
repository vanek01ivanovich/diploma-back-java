package ua.kpi.diploma.controltestinghub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.kpi.diploma.controltestinghub.model.User;
import ua.kpi.diploma.controltestinghub.service.serviceImpl.EmailServiceImpl;
import ua.kpi.diploma.controltestinghub.service.serviceImpl.UserServiceImpl;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserServiceImpl userService;
    private final EmailServiceImpl emailService;
    private final PasswordEncoder passwordEncoder;
    

    @Autowired
    public UserController(UserServiceImpl userService, EmailServiceImpl emailService, PasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.emailService = emailService;
        this.passwordEncoder = bCryptPasswordEncoder;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.toString());
        userService.saveUser(user);
        emailService.sendCredentialsByEmail(user);
    }

    @GetMapping("/pages/count")
    public Integer countUserPages(Integer pageSize) {
        return userService.countPages(pageSize);
    }

    @GetMapping("/check-email/{email}")
    public Boolean checkIfEmailExists(@PathVariable String email){
        System.out.println("Check email = " + email);
        return userService.checkIfEmailExists(email);
    }

    /*@GetMapping("/pages/count-search")
    public Integer countUserPagesSearch(UserSearchDto userSearchDto, Integer pageSize) {
        return userService.countPagesSearch(userSearchDto, pageSize);
    }*/






}
