package project.man.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import project.man.Service.AccountService;
import project.man.Service.ProjectService;
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
 

            return "project"; 
        } else {
            return "redirect:/error";
        }
    }
}