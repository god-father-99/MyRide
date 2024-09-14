package com.aditya.project.uber.uberApp.repositories;

import com.aditya.project.uber.uberApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
