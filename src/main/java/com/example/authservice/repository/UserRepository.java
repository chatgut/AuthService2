package com.example.authservice.repository;

import com.example.authservice.entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
