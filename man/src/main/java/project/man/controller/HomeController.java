package project.man.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import project.man.Service.ProjectService;
import project.man.models.Project;



@Controller
public class HomeController {

     @Autowired
     ProjectService projectService;

    @GetMapping("/")
    public String home(Model model) {
           List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "index"; 
    }

}
