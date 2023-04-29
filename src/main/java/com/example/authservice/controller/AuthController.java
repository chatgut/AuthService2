package com.example.authservice.controller;

import com.example.authservice.entity.Auth;
import com.example.authservice.repository.AuthRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthRepository authRepository;

    public AuthController(AuthRepository authRepository){
        this.authRepository = authRepository;
    }
    @PostMapping("/login")
    void postCredentials(@RequestBody Auth auth){
        authRepository.save(auth);
    }
    @GetMapping("/login")
    String login(@RequestBody Auth auth){
        if(authRepository.findByUsernameAndPassword(auth.getUsername(),auth.getPassword())!= null)
            return "token";
        else
            return "did not work";
    }

}
