package com.example.authservice.entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    public User(){
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
