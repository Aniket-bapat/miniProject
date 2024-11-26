package project.man.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        return "index"; // Only "index", not "index.html"
    }
    @GetMapping("/register")
    public String register(Model model) {
        return "register"; // Only "index", not "index.html"
    }
    
    }
