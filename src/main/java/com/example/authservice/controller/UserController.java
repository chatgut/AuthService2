package com.example.authservice.controller;

import com.example.authservice.JwtTokenProvider;
import com.example.authservice.entity.User;
import com.example.authservice.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class UserController {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    ResponseEntity<String> postCredentials(@RequestBody User user){
        if (userRepository.findByUsername(user.getUsername())==null) {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            return new ResponseEntity<>("Account created", HttpStatus.OK);
        }
        return new ResponseEntity<>("Username is already in use", HttpStatus.CONFLICT);
    }
    @GetMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Optional<User> optionalUser) {
        if (optionalUser.isPresent()) {
            User encodedUser = userRepository.findByUsername(optionalUser.get().getUsername());
            if (encodedUser != null && encoder.matches(optionalUser.get().getPassword(), encodedUser.getPassword())) {
                Token token = new Token(jwtTokenProvider.generateToken(optionalUser.get()));
                return ResponseEntity.ok().body(token);
            }
        }
        return new ResponseEntity<>("invalid credentials", HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/login")
    ResponseEntity<String> deleteLogin (@RequestBody Optional<User> optionalUser){
        if(optionalUser.isPresent()){
            User encodedUser = userRepository.findByUsername(optionalUser.get().getUsername());
            if (encodedUser!=null && encoder.matches(optionalUser.get().getPassword(),encodedUser.getPassword())){
                userRepository.delete(userRepository.findByUsername(optionalUser.get().getUsername()));
                return new ResponseEntity<>("deleted",HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("no user found",HttpStatus.NO_CONTENT);
    }
    @GetMapping("/validate/user")
    ResponseEntity<String> validate(@RequestBody String token){
        boolean isValid = jwtTokenProvider.validateToken(token);
        if (isValid)
            return new ResponseEntity<>(jwtTokenProvider.getUsernameFromToken(token), HttpStatus.OK);
        else
            return new ResponseEntity<>("invalid token", HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/validate/UUID")
    ResponseEntity<String> UUID(@RequestBody String token){
        boolean isValid = jwtTokenProvider.validateToken(token);
        if (isValid)
            return new ResponseEntity<>(jwtTokenProvider.getUUIDFromToken(token), HttpStatus.OK);
        else
            return new ResponseEntity<>("invalid token", HttpStatus.UNAUTHORIZED);
    }
}
@Getter
@Setter
class Token{
    private String token;
    public Token(String token) {
        this.token = token;
    }
}
