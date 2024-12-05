package project.man.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import project.man.Service.AccountService;
import project.man.Service.ProjectService;
import project.man.models.Account;
import project.man.models.Project;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private AccountService accountService;

        @GetMapping("/projects/{id}")
    public String getProject(@PathVariable Long id, Model model, Principal principal) {
          Optional<Project> optionalProject = projectService.getById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            model.addAttribute("project", project);

            String authUser = (principal != null) ? principal.getName() : "email";
            model.addAttribute("isOwner", authUser.equals(project.getAccount().getEmail()));

            return "project"; 
        } else {
            return "redirect:/error";
        }
    }
   @GetMapping("/projects/add")
    public String addPostForm(Model model, Principal principal) {
        String authUser = (principal != null) ? principal.getName() : "email";
        Optional<Account> optionalAccount = accountService.findOneByEmail(authUser);

        if (optionalAccount.isPresent()) {
             Project project  = new Project();
            project.setAccount(optionalAccount.get());
            model.addAttribute("project", project);
            return "addproject"; // Ensure this view exists
        } else {
            return "redirect:/"; // Redirect if account is not found
        }
    }
   @PostMapping("/project/add")
@PreAuthorize("isAuthenticated()")
public String addProjectHandler(@Valid @ModelAttribute Project project, 
                                 BindingResult bindingResult, 
                                 @RequestParam("accountId") Long accountId) {
    if (bindingResult.hasErrors()) {
        return "addproject";
    }

    // Fetch the Account using the accountId
    Optional<Account> optionalAccount = accountService.findOneById(accountId);

    if (optionalAccount.isEmpty()) {
        return "redirect:/?error=noaccount";
    }

    // Set the account on the project
    project.setAccount(optionalAccount.get());

    // Save the project
    projectService.save(project);

    return "redirect:/projects/" + project.getId();
}

        @GetMapping("/projects/{id}/edit")
    @PreAuthorize("isAuthenticated()")
 public String getProjectForEdit(@PathVariable long id, Model model)
 {
    Optional<Project> optionalProject = projectService.getById(id);
    if (optionalProject.isPresent()) {
        Project project = optionalProject.get();
        model.addAttribute("project", project);

        return "editproject";
    }else{
        return"404";
    }
    
      
 }
 @PostMapping("/projects/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String updatedProject(@Valid @ModelAttribute Project project,BindingResult bindingResult,@PathVariable long id)
    {
        if(bindingResult.hasErrors())
        {
            return "addproject";
        }
        Optional<Project> optionalProject = projectService.getById(id);
        if (optionalProject.isPresent()) {
            Project existingproject = optionalProject.get();
            existingproject.setTitle(project.getTitle());
            existingproject.setRequirements(project.getRequirements());
            existingproject.setDescription(project.getDescription());
          projectService.save(existingproject);
        }
        return "redirect:/projects/"+project.getId();
    }
}