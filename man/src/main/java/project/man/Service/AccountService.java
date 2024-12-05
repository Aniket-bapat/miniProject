package project.man.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import project.man.Repositories.AccountRepository;
import project.man.models.Account;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
        @Autowired
        private PasswordEncoder passwordEncoder;
     public Account save(Account account)
    {
        if (account.getRole() == null || account.getRole().isEmpty()) {
            account.setRole("ROLE_USER"); 
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
  
        Optional<Account> optionalAccount = accountRepository.findOneByEmailIgnoreCase(email);
    
        if (optionalAccount.isEmpty()) {
            throw new UsernameNotFoundException("Account not found with email: " + email);
        }
    
        Account account = optionalAccount.get();
    
      
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    
       
        grantedAuthorities.add(new SimpleGrantedAuthority(account.getRole()));
    
        if (account.getAuthorities() != null && !account.getAuthorities().isEmpty()) {
            account.getAuthorities().forEach(auth -> 
                grantedAuthorities.add(new SimpleGrantedAuthority(auth.getName()))
            );
        }
    
   
        System.out.println("Authorities for " + account.getEmail() + ": " + grantedAuthorities);
    
        return new User(account.getEmail(), account.getPassword(), grantedAuthorities);
    }
    
    public Optional<Account> findOneByEmail(String email)
    {
        return accountRepository.findOneByEmailIgnoreCase(email);
    }
    public Optional<Account> findOneById(long id)
    {
        return accountRepository.findById(id);
    }
}
