package project.man.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import project.man.Service.AccountService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebsecurityConfig {
        @Autowired
        private AccountService accountService;

        private static final String[] WHITELIST = {
                "/", "/login", "/register", "/css/**", "/fonts/**", "/images/**", "/js/**", "/home","/forgot-password","/reset-password","/change-password",
        };
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                        .csrf(csrf -> csrf.disable())  
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers(WHITELIST).permitAll()  
                                .requestMatchers("/welcome/**").authenticated()  
                                .requestMatchers("/landing/**").authenticated()
                        )
                        .formLogin(form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/landing", true)
                                .failureUrl("/login?error")
                                .permitAll()
                        )
                        .logout(logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                                .permitAll());
                  
        
                return http.build();
            }
@Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(accountService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}