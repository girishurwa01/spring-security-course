package com.girish.hello;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// =====================================================================
//  LESSON 7 — TASK B: A JAVA CLASS THAT BECOMES A DATABASE TABLE
//
//  JPA turns this class into a table called app_user:
//
//      app_user
//      ┌────┬──────────┬──────────────────┬───────┐
//      │ id │ username │ password (hash)  │ role  │
//      ├────┼──────────┼──────────────────┼───────┤
//      │ 1  │ girish   │ $2a$10$XFM...    │ USER  │
//      │ 2  │ boss     │ $2a$10$SRK...    │ ADMIN │
//      └────┴──────────┴──────────────────┴───────┘
//
//  Each FIELD = a COLUMN.  Each OBJECT = a ROW.
// =====================================================================

// TODO B1: On the empty line below, add the annotation that tells JPA
//          "this class is a database table (an entity)".
//          Hint: look at the imports — one of them is named after it.
@Entity
@Table(name = "app_user")   // the table's name ("user" is a reserved word in SQL)
public class AppUser {

    // TODO B2: On the empty line below, add the annotation that marks
    //          'id' as the PRIMARY KEY (the unique number of each row).
    @Id
    @GeneratedValue          // = "database, pick the id number for me"
    private Long id;

    private String username;
    private String password;   // stores the BCrypt HASH, never the plain password
    private String role;       // "USER" or "ADMIN"

    // Getters: let other classes READ the fields
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}
