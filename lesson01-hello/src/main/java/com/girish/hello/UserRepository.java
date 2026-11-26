package com.girish.hello;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

// =====================================================================
//  LESSON 7 — TASK C: THE REPOSITORY = your helper that talks to the DB
//
//  'interface' = a list of methods WITHOUT code inside.
//  Spring Data JPA WRITES THE CODE FOR YOU when the app starts!
//
//  By extending JpaRepository<AppUser, Long> you already get, for free:
//      findAll(), findById(id), save(user), delete(user), count() ...
//      (AppUser = what is stored,  Long = the type of its id)
//
//  ✨ MAGIC: if you NAME a method correctly, Spring reads the name
//     and builds the SQL query from it:
//         findByUsername(...)  →  SELECT * FROM app_user WHERE username = ?
// =====================================================================
public interface UserRepository extends JpaRepository<AppUser, Long> {

    // TODO C: Declare ONE method (no body, just end with ;) that:
    //           - is named        findByUsername
    //           - takes           String username
    //           - returns         Optional<AppUser>
    //
    //   Optional<AppUser> = "an AppUser, OR nothing if no such user"
    //   Shape:   ReturnType methodName(Type parameterName);

public Optional<AppUser> findByUsername(String username);

}
