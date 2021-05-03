package ua.kpi.diploma.controltestinghub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class AuthenticationController {

    @Autowired
    public AuthenticationController() {
    }

    @PostMapping
    public ResponseEntity<?> userAuthentication(){

    }
}
