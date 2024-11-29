package project.man.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import project.man.Service.AccountService;
import project.man.models.Account;
import project.man.util.constants.Roles;
@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private AccountService accountService;

   @Override
    public void run(String... args) throws Exception {
        Account account01 = new Account();
        account01.setEmail("khushjain052@gmail.com");
        account01.setPassword("Khush"); 
        account01.setRole(Roles.ADMIN.getRole());
        account01.setAuthorities(null);
        accountService.save(account01);
        Account account02 = new Account();
        account02.setEmail("shreedeelipent@gmail.com");
        account02.setPassword("Khush"); 
        account02.setRole(Roles.ADMIN.getRole());
        account02.setAuthorities(null);
        accountService.save(account02);
    }
}