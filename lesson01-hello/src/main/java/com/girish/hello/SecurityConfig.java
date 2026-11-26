package com.girish.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                .requestMatchers("/hello", "/error").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")

                // TODO B1 (Lesson 5): only users with role ADMIN may open /admin.
                //          Write:  .requestMatchers("<url>").hasRole("<ROLE>")
                //          Think: must it go BEFORE or AFTER anyRequest()? Why?

                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults());

        return http.build();
    }

    // =================================================================
    //  BEAN 2 (NEW in Lesson 5): THE LIST OF USERS
    //
    //  A UserDetailsService answers one question for the guard 🛡️:
    //     "Here is a username — who is this, what is their password,
    //      and what ROLES do they have?"
    //
    //  InMemoryUserDetailsManager = keep the users in memory (RAM).
    //  (Lesson 7 will move them into a real database.)
    //
    //  {noop} in front of a password = "no operation": the password is
    //  stored as plain text. FINE FOR PRACTICE ONLY. Lesson 6 replaces
    //  it with proper hashing.
    //
    //  Because YOU now provide a UserDetailsService, Spring Boot's
    //  auto-configuration steps back again: the user in
    //  application.properties is no longer used. (See TODO C there.)
    // =================================================================
    @Bean
    public UserDetailsService users() {

        // A normal user. Role: USER
        UserDetails girish = User.withUsername("girish")
                .password("{noop}password123")
                .roles("USER")
                .build();

        UserDetails boss = User.withUsername("boss")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();

        // TODO B2: Create a second user, just like girish above, but:
        //            - variable name:  boss
        //            - username:       boss
        //            - password:       {noop}admin123
        //            - role:           ADMIN


        return new InMemoryUserDetailsManager(girish, boss);
    }
}
