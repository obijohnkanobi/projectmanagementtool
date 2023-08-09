package com.example.projectmanagementtool.utils;

import com.example.projectmanagementtool.models.UserRole;

import javax.servlet.http.HttpSession;

public class UserUtils {

    public static boolean isAdmin(HttpSession session) {
        UserRole userRole = (UserRole) session.getAttribute("userRole");
        return userRole == UserRole.ADMIN;
    }

    public static boolean isDeveloper(HttpSession session) {
        UserRole userRole = (UserRole) session.getAttribute("userRole");
        return userRole == UserRole.DEVELOPER;
    }

    public static boolean isProjectLeader(HttpSession session) {
        UserRole userRole = (UserRole) session.getAttribute("userRole");
        return userRole == UserRole.PROJECT_LEADER;
    }

    public static boolean isUser(HttpSession session) {
        UserRole userRole = (UserRole) session.getAttribute("userRole");
        return userRole == UserRole.USER;
    }

    // ... any other utility methods related to the user ...
}
