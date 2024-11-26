package project.man.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/login")
    public String login(Model model) {
        return "login"; // Show the login page
    }

    @GetMapping("/login-success")
    public String loginSuccess(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated()) {
            // If authenticated, retrieve user details (optional)
            model.addAttribute("message", "Welcome, " + "!");
            return "welcome"; // Show the post-login success page
        } else {
            model.addAttribute("error", "Login failed. Please try again.");
            return "login"; // Redirect back to the login page
        }
    }
}