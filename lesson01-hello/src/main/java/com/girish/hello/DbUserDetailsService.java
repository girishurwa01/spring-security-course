package com.girish.hello;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// =====================================================================
//  LESSON 7 — TASK D: THE BRIDGE between Spring Security and your DB
//
//  At login, the guard 🛡️ calls:   loadUserByUsername("boss")
//  This class answers by looking boss up in the DATABASE, then turns the
//  AppUser row into a UserDetails object that Spring Security understands.
//
//  @Service = "Spring, create one of these and use it".
//  Because it implements UserDetailsService, Spring Security will
//  automatically use it to find users at login.
// =====================================================================
@Service
public class DbUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    // Spring passes in the repository for us ("dependency injection")
    public DbUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        // 1. Ask the DB for the row. If it isn't there → login fails.
        AppUser u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user: " + username));

        // 2. TODO D: Turn 'u' into a UserDetails and return it.
        //    You wrote this shape in Lesson 5! But instead of fixed text,
        //    read the values FROM u using its getters:
        //        u.getUsername()   u.getPassword()   u.getRole()
        //
        //    return User.withUsername( ... )
        //            .password( ... )
        //            .roles( ... )
        //            .build();



           return User.withUsername(u.getUsername())
                .password( u.getPassword())
                .roles( u.getRole())
                .build();
    }
}
