package com.girish.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// =====================================================================
//  LESSON 4 — TASK B: YOUR OWN SECURITY RULES
//
//  @Configuration  = "this class holds settings, written in Java"
//  @Bean           = "Spring, please take what this method returns
//                     and use it in the app"
//
//  The method returns a SecurityFilterChain = THE RULE BOOK the
//  security guard 🛡️ reads for every request.
//
//  Because YOU now provide a rule book, Spring Boot's auto-configuration
//  steps back and stops using its default "lock everything" rules.
// =====================================================================
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // "Here come my rules about WHO may open WHICH URL"
            .authorizeHttpRequests(auth -> auth

                // TODO B1: Rule 1 — /hello is open to EVERYONE (no login).
                //          Write:  .requestMatchers("<url>").permitAll()

                .requestMatchers("/hello","/error").permitAll()
                .anyRequest().authenticated()

                // TODO B2: Rule 2 — EVERY OTHER URL needs a login.
                //          Write:  .anyRequest().authenticated()


            )
            // Keep the login page Spring gave us
            .formLogin(Customizer.withDefaults());

        return http.build();
    }
}
