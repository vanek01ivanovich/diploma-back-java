package ua.kpi.diploma.controltestinghub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kpi.diploma.controltestinghub.config.JwtProvider;
import ua.kpi.diploma.controltestinghub.dto.ResetPassDto;
import ua.kpi.diploma.controltestinghub.model.User;
import ua.kpi.diploma.controltestinghub.service.UserService;
import ua.kpi.diploma.controltestinghub.service.serviceImpl.EmailServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/settings")
public class UserSettingsController {
    private final UserService userService;
    private final EmailServiceImpl emailService;

    @Autowired
    public UserSettingsController(UserService userService, EmailServiceImpl emailService){
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping
    public User settingsPage(@RequestHeader("Authorization") String jwt){
        JwtProvider jwtProvider = new JwtProvider();
        return userService.getUserByEmail(jwtProvider.getUserNameFromJwtToken(jwtProvider.resolveStringToken(jwt)));
    }
    @PutMapping
    public void settingsUpdate(@RequestBody User user, @RequestHeader("Authorization") String jwt){
        JwtProvider jwtProvider = new JwtProvider();
        user.setEmail(jwtProvider.getUserNameFromJwtToken(jwtProvider.resolveStringToken(jwt)));
        userService.updateUserSettings(user);
    }
    @PutMapping("/password")
    public void changePassword(@RequestBody User user){
        userService.updateUserPassword(user);
    }

    @PutMapping("/resetpass")
    public void resetPassword(@RequestBody ResetPassDto resetPassDto) throws Exception {
        System.out.println(resetPassDto.toString());
        userService.updateUserPasswordByToken(resetPassDto.getToken(), resetPassDto.getPassword());
    }
    @PostMapping("/reset-by-email")
    public void resetPassByEmail(@RequestBody User user){
        emailService.sendCredentialsByEmail(user);
    }
}
