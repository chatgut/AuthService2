package com.example.authservice.repository;

import com.example.authservice.entity.Auth;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends ListCrudRepository<Auth, Long> {
    Auth findByUsernameAndPassword(String username, String password);
}
