package com.example.projectmanagementtool.repositories;

import com.example.projectmanagementtool.models.User;

// UserRepository.java
public interface UserRepository {
    User findByUsername(String username);
}
