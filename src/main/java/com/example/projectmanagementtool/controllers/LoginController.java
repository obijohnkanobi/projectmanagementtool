package com.example.projectmanagementtool.controllers;

import com.example.projectmanagementtool.models.User;
import com.example.projectmanagementtool.models.UserRole;
import com.example.projectmanagementtool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              RedirectAttributes redirectAttributes,
                              HttpSession session) {
        User user = userService.loginUser(username, password);
        if (user != null) {
            session.setAttribute("username", username);
            session.setAttribute("userRole", user.getRole());
            return "redirect:/index";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password.");
            return "redirect:/login";
        }

    }
    // Redirects the root URL to the login page
    @GetMapping("/")
    public String redirectToLoginPage() {
        return "redirect:/login";
    }
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
