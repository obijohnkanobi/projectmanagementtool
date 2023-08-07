package com.example.projectmanagementtool.repositories;

import com.example.projectmanagementtool.models.User;
import com.example.projectmanagementtool.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// JdbcUserRepository.java
@Repository
public class JdbcUserRepository implements UserRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    // Her indstilles UserRole, baseret på data i databasen
                    user.setRole(UserRole.valueOf(resultSet.getString("role")));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
