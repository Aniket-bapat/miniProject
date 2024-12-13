package project.man.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import project.man.Service.AccountService;
import project.man.Service.ProjectService;
import project.man.models.Account;
import project.man.models.Project;
import project.man.util.constants.Roles;
@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ProjectService projectService;

   @Override
    public void run(String... args) throws Exception {
        Account account01 = new Account();
        account01.setFname("KHUSH");
        account01.setEmail("khushjain052@gmail.com");
        account01.setPassword("Khush"); 
        account01.setRole(Roles.ADMIN.getRole());
        account01.setAuthorities(null);
        accountService.save(account01);
        Account account02 = new Account();
        account02.setFname("Aniket");
        account02.setEmail("shreedeelipent@gmail.com");
        account02.setPassword("Khush"); 
        account02.setRole(Roles.ADMIN.getRole());
        account02.setAuthorities(null);
        accountService.save(account02);  

        Account account03 = new Account();
        account03.setFname("Atharva");
        account03.setEmail("atharvasungar@gmail.com");
        account03.setPassword("Khush"); 
        account03.setRole(Roles.ADMIN.getRole());
        account03.setAuthorities(null);
        accountService.save(account03);

        Account account04 = new Account();
        account04.setFname("Abhishek");
        account04.setEmail("abhisheksolabannavar@gmail.com");
        account04.setPassword("Khush"); 
        account04.setRole(Roles.ADMIN.getRole());
        account04.setAuthorities(null);
        accountService.save(account04);


        Project project01 = new Project();
        project01.setTitle("IOT PROJECT ");
        project01.setRequirements("Hardwareeeeeeeeeeee");
        project01.setDescription("DESCRIPTION");
        project01.setAccount(account02);
        projectService.save(project01);

        Project project02 = new Project();
        project02.setTitle("IT project");
        project02.setRequirements("Hardwareeeeeeeeeeee");
        project02.setDescription("DESCRIPTION aaadadasdad");
        project02.setAccount(account01);
        projectService.save(project02);

        Project project03 = new Project();
        Project project04 = new Project();
 

        project03.setTitle("BluePhoenix");
        project03.setRequirements("Hardwareeeeeeeeeeee");
        project03.setDescription("A revitalizing project aimed at rebuilding and reinventing processes for optimal efficiency.");
        project03.setAccount(account03);
        projectService.save(project03);

        project04.setTitle("SwiftMomentum");
        project04.setRequirements("Hardwareeeeeeeeeeee");
        project04.setDescription("Focuses on accelerating task completion and delivering results with speed and precision.");
        project04.setAccount(account04);
        projectService.save(project04);
    }
   
}