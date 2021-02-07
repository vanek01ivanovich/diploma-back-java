package ua.kpi.diploma.controltestinghub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ua.kpi.diploma.controltestinghub.config.JwtProvider;
import ua.kpi.diploma.controltestinghub.dto.AuthDto;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(JwtProvider jwtProvider,AuthenticationManager authenticationManager){
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/login")
    public void authenticate(@RequestBody AuthDto authRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        String jwt = jwtProvider.generateJwtToken(authentication);


    }


}
