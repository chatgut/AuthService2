package com.example.authservice.controller;

import com.example.authservice.entity.User;
import com.example.authservice.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @PostMapping("/login")
    void postCredentials(@RequestBody User user){
        userRepository.save(user);
    }
    @GetMapping("/login")
    String login(@RequestBody User user){
        if(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())!= null)
            return "token";
        else
            return "did not work";

    }

}
