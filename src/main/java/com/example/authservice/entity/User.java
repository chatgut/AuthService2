package com.example.authservice.entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@ToString
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String username;
    private String password;
    public User(){
        this.uuid = UUID.randomUUID().toString();
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
