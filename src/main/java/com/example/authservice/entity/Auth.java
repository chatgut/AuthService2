package com.example.authservice.entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
@Entity
@Setter
@Getter
@ToString
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    public Auth(){
    }

    public Auth(String username, String password){
        this.username = username;
        this.password = password;
    }
}
