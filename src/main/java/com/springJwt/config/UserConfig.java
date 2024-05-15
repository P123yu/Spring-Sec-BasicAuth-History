package com.springJwt.config;


import com.springJwt.repository.UserRepository;
import com.springJwt.serviceImpl.UserServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Consumer;

@Configuration
public class UserConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(req->req.requestMatchers("/api/register").permitAll()
                        .requestMatchers("/api/login").permitAll()
                        .anyRequest().authenticated())
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Autowired
    public UserRepository userRepository;

//    @Bean
//    public UserDetailsService userDetailsService(){
//        return username -> (UserDetails) userRepository.findByNameOrEmail(username,username).orElse(null);
//    }





    @Bean
    public UserDetailsService userDetailsService() {
        Consumer<String> usernamePrinter = username -> System.out.println("Username: " + username);
        return username -> {
            usernamePrinter.accept(username);  // Print username for debugging
            return userRepository.findByNameOrEmail(username, username).orElse(null);
        };
    }


    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }



}
