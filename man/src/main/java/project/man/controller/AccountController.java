package project.man.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.man.Service.AccountService;
import project.man.Service.ProjectService;
import project.man.models.Account;
import project.man.models.Project;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
     ProjectService projectService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    

    @GetMapping("/register")
    public String register(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "register"; 
    }

    @PostMapping("/register")
    public String register_user(@ModelAttribute Account account){
        accountService.save(account);
        return "redirect/:";
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "login"; // Show the login page
    }
     @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model, RedirectAttributes redirectAttributes) {
        try {
            
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                email, password);
            
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            
          
            SecurityContextHolder.getContext().setAuthentication(authentication);
      
            
          
            return "redirect:/home"; 

        } catch (Exception e) {
       
            model.addAttribute("error", "Invalid email or password");
            return "login";  
        }
    }
    @GetMapping("/index")
    @PreAuthorize("isAuthenticated()")
    public String index(Model model) {
            List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "index"; 
    }
}