package com.girish.hello;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

// =====================================================================
//  LESSON 8: THE SIGN-UP LOGIC (a SERVICE = the "chef" 👨‍🍳)
//
//  The controller (waiter) hands us a SignupRequest.
//  We check it, hash the password, save the user, and return a safe UserView.
// =====================================================================
@Service
public class SignupService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;   // ← YOUR BCrypt bean from Lesson 6!

    // Spring passes in both (dependency injection)
    public SignupService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public UserView signup(SignupRequest request) {

        // --- Checks (written for you) -------------------------------
        // Records use  request.username()  — NOT  getUsername()
        if (request.username() == null || request.username().isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is required");

        // Remember Lesson 6: a common short password was guess #7. Short passwords get cracked.
        if (request.password() == null || request.password().length() < 8)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must be at least 8 characters");

        if (repo.findByUsername(request.username()).isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken");

        // --- TODO B: HASH the password ------------------------------
        //  Use the encoder, exactly like you did in HashTool (Lesson 6).
        //  The plain password is:  request.password()
        //
        //  String hash = ...
        String hash = encoder.encode(request.password());   // ← replace null


        // --- TODO C: SAVE the new user ------------------------------
        //  1. Make a new AppUser box:   new AppUser( username, hash, role )
        //       - username → request.username()
        //       - hash     → your hash from TODO B
        //       - role     → always "USER"   (🤔 why ALWAYS?)
        //  2. Save it:   repo.save( ...that new AppUser... )
        //     save() gives back the SAVED user (now with an id from the DB)
        //
        //  AppUser saved = repo.save( ... );
        
        AppUser saved = repo.save(new AppUser(request.username(),hash,"USER"));   



        // Return the SAFE box (never the entity — Lesson 7!)
        return new UserView(saved.getId(), saved.getUsername(), saved.getRole());
    }
}
