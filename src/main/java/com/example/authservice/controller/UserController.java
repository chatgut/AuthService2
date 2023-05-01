package com.example.authservice.controller;

import com.example.authservice.JwtTokenProvider;
import com.example.authservice.entity.User;
import com.example.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    void postCredentials(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    @GetMapping("/login")
    String login(@RequestBody User user){
        User encodedUser = userRepository.findByUsername(user.getUsername());
        if (encoder.matches(user.getPassword(),encodedUser.getPassword())){
            return jwtTokenProvider.generateToken(user);
        }
        else
            return "Wrong login credentials";
    }
    @GetMapping("/validate")
    String validate(@RequestBody String token){
        boolean isValid = jwtTokenProvider.validateToken(token);
        if (isValid)
            return jwtTokenProvider.getUsernameFromToken(token);
        else
            return "token is invalid";
    }



}
