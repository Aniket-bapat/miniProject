package project.man.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import project.man.Service.EmailService;
import project.man.Service.ProjectService;
import project.man.models.Account;
import project.man.models.Project;
import project.man.util.constants.email.emailDetails;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
     ProjectService projectService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmailService emailService;

    @Value("${site.domain}")
    private String site_domain;


    @Value("${password.token.reset.timeout.minutes}")
    private int password_token_timeout;


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

     @GetMapping("/forgotpassword")
    public String forgot_password(Model model) {
        return "forgotpassword";
    }
    @PostMapping("/resetpassword")
    public String resetpassword(@RequestParam("email") String _email, RedirectAttributes attributes, Model model) {
        Optional<Account> optional_account = accountService.findOneByEmail(_email);
        if (optional_account.isPresent()) {
            Account account = optional_account.get();
            String reset_token = UUID.randomUUID().toString();
            account.setToken(reset_token);
            account.setPassword_reset_token_expiry(LocalDateTime.now().plusMinutes(password_token_timeout));
            accountService.save(account);
           String reset_message= "This is the reset password link: "+site_domain+"changepassword?token="+reset_token;
             emailDetails emaildetails = new emailDetails(account.getEmail(),reset_message,"Reset password by Project Ace");
             if(emailService.sendSimpleEmail(emaildetails) ==false)
             {
                attributes.addFlashAttribute("error","Error while sending email contact admin");
             }
            attributes.addFlashAttribute("message", "Password reset email sent.");
            return "redirect:/login";
        } else {
            attributes.addFlashAttribute("error", "No user found with this email.");
            return "redirect:/forgotpassword";
        }
    }
    
    @GetMapping("/changepassword")
    public String change_password(@RequestParam("token")String token,RedirectAttributes attributes, Model model) {
        if(token.equals(""))
        {
            attributes.addFlashAttribute("error","Invalid Token");
            return "redirect:/forgotpassword";
        }
       Optional<Account> optional_account = accountService.findByToken(token);
       if(optional_account.isPresent())
       {
          Account account = accountService.findOneById(optional_account.get().getId()).get();
          LocalDateTime now = LocalDateTime.now();
         if (now.isAfter(optional_account.get().getPassword_reset_token_expiry()))
         {
            attributes.addFlashAttribute("error", "Token Expired");
            return "redirect:/forgotpassword";
         }
          model.addAttribute("account",account);
          return "changepassword";
       }
       else {
        attributes.addFlashAttribute("error", "Invalid token");
        return "redirect:/forgotpassword";
    }
    }
    @PostMapping("/changepassword")
    public String post_change_password(@ModelAttribute Account account,RedirectAttributes attributes)
    {
        Account account_by_id = accountService.findOneById(account.getId()).get();
        account_by_id.setPassword(account.getPassword());
        account_by_id.setToken("");
        accountService.save(account_by_id);
        attributes.addFlashAttribute("message","Password Updated");
        return "redirect:/login";
    }
    
}