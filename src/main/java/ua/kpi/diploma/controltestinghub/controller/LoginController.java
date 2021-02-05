package ua.kpi.diploma.controltestinghub.controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {

    @PostMapping("/login")
    public void authentication(@RequestBody String message){
        System.out.println(message);
    }

    @GetMapping("/login")
    public void login(){
        System.out.println("hello");
    }
}
