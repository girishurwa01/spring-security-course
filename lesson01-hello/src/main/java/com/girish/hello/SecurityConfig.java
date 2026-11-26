package com.girish.hello;

import java.beans.BeanProperty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                .requestMatchers("/hello", "/error").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")

                // DONE ✅ (Lesson 5): only users with role ADMIN may open /admin.
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
    // =================================================================
    //  LESSON 6 — TASK C: REPLACE THE PLAIN PASSWORDS WITH HASHES
    //
    //  1. In the terminal run:   ./hash.sh <girish's password>
    //     Copy the line it prints (starts with $2a$10$...).
    //  2. Replace "{noop}..." for girish with "<that hash>"
    //     (remove {noop} too — the whole thing becomes just the hash).
    //  3. Same for boss:         ./hash.sh <boss password>
    // =================================================================

    @Bean
    public UserDetailsService users() {

        // A normal user. Role: USER
        UserDetails girish = User.withUsername("girish")
                .password("$2a$10$XFM.lJkyeZGiHLUUDtczwO.7XZEKZFwBG7vBwe8.hzOPxu6I35AE2")
                .roles("USER")
                .build();

        UserDetails boss = User.withUsername("boss")
                .password("$2a$10$SRKOtbevGWAZnET88cZKOeeBidcHgtj04pwIK0JPE/8TFbi0nZX2q")
                .roles("ADMIN")
                .build();

        // DONE ✅ (Lesson 5): Create a second user, just like girish above, but:
        //            - variable name:  boss
        //            - username:       boss
        //            - password:       {noop}<a password>
        //            - role:           ADMIN


        return new InMemoryUserDetailsManager(girish, boss);
    }

    // =================================================================
    //  LESSON 6 — TASK B: THE PASSWORD CHECKER
    //
    //  A PasswordEncoder tells Spring HOW passwords are stored.
    //  When someone logs in, Spring does:
    //      encoder.matches(typedPassword, storedHash)  → true / false
    //
    //  TODO B: Below this comment, write a @Bean method:
    //            - return type:  PasswordEncoder
    //            - name:         passwordEncoder
    //            - no parameters
    //            - body:         return new BCryptPasswordEncoder();
    //
    //  Hint: look at how  public UserDetailsService users() { ... }
    //        is written above — same shape, much shorter.
    // =================================================================

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
