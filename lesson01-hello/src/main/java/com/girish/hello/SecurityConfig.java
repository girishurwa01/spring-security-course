package com.girish.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // =================================================================
    //  BEAN 1: THE RULE BOOK  (you wrote this in Lesson 4)
    // =================================================================
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // TODO E (Lesson 8): people signing up are NOT logged in yet!
                //         Add "/signup" to this permitAll list.
                .requestMatchers("/hello", "/error", "/signup").permitAll()
                .requestMatchers("/admin", "/admin/**").hasRole("ADMIN")   // /** = "and everything under /admin/"

                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            // Lesson 8 (written for you): let /signup accept JSON POSTs from curl.
            // CSRF protection would block them with 403. WHY it exists, and how to
            // do this properly, is exactly Lesson 9. 🔜
            .csrf(csrf -> csrf.ignoringRequestMatchers("/signup"));

        return http.build();
    }

    // =================================================================
    //  BEAN 2: THE PASSWORD CHECKER  (you wrote this in Lesson 6)
    //  Spring uses it at login:  encoder.matches(typed, storedHash)
    //  and SignupService uses it to hash new passwords (Lesson 8).
    //
    //  Users are NOT in this file any more: they live in the database
    //  and are loaded by DbUserDetailsService (Lesson 7).
    // =================================================================

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
