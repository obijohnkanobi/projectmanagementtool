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

@Repository
public class JdbcUserRepository implements UserRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM app_users WHERE username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    String roleValue = resultSet.getString("role");
                    if (roleValue == null) {
                        // Handle NULL role here by setting a default role
                        user.setRole(UserRole.USER);
                    } else {
                        // Convert the role value to the UserRole enum
                        try {
                            user.setRole(UserRole.valueOf(roleValue.toUpperCase()));
                        } catch (IllegalArgumentException e) {
                            // Handle invalid role value here (e.g., log an error or set default role)
                            user.setRole(UserRole.USER);
                        }
                    }
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
