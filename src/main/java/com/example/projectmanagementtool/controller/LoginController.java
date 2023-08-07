package com.example.projectmanagementtool.controller;

import com.example.projectmanagementtool.service.UserService;
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
        if (userService.login(username, password)) {
            // Gem brugeroplysninger i sessionen
            session.setAttribute("username", username);
            // Hvis login er vellykket, omdirigere til index-siden
            return "redirect:/index";
        } else {
            // Failed login, redirect tilbage til login-siden med en fejlmeddelelse
            redirectAttributes.addFlashAttribute("error", "Invalid username or password.");
            return "redirect:/login";
        }
    }
    // Redirects the root URL to the login page
    @GetMapping("/")
    public String redirectToLoginPage() {
        return "redirect:/login";
    }
}
