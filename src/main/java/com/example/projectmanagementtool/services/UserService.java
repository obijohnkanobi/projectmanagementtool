package com.example.projectmanagementtool.services;

import com.example.projectmanagementtool.models.User;
import com.example.projectmanagementtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null;  // User not found
        }

        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
