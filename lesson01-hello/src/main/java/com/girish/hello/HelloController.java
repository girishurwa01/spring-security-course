package com.girish.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

// =====================================================================

//  LESSON 1 — YOUR FIRST WEB ENDPOINT
//
//  Goal: when you open http://localhost:8080/hello in a browser,
//        you see the text:  Hello, Girish!
//
//  An "annotation" is a label that starts with @. It tells Spring
//  what a class or method is FOR. You will type two of them.

// =====================================================================



// TODO 1: On the empty line below, type the annotation that tells Spring
//         "this class answers web requests and sends back plain data".
//         Hint: look at the imports above — one of them is a class annotation.





@RestController

public class HelloController {

    // TODO 2: On the empty line below, type the annotation that says
    //         "run this method when someone does a GET on /hello".
    //         Hint: @Something("/hello")
  @GetMapping("/hello")
  
    public String sayHello() {

        // TODO 3: Replace the null with the text you want to send back.
        return "Hello, Girish";

    }

    // =================================================================
    //  LESSON 4 — TASK A: a second, SECRET endpoint
    //
    //  DONE ✅ (Lesson 4): Below this comment, write a whole new method, just like
    //          sayHello() above, but:
    //            - it answers GET on  /secret
    //            - the method is called  secret
    //            - it returns the text  "Top secret!"
    // =================================================================
    
    @GetMapping("/secret")

    public String secret(){
        return "Top Secret";
    }

    // =================================================================
    //  LESSON 5 — TASK A: an ADMIN-only page
    //
    //  TODO A: Below this comment, write a method that:
    //            - answers GET on  /admin
    //            - is called       admin
    //            - returns         "Welcome, Admin!"
    // =================================================================
@GetMapping("/admin")

public String admin(){

return "Welcome Admin";



}

    // =================================================================
    //  LESSON 7 (written for you): an ADMIN page that shows every row
    //  of the app_user table, so you can SEE your database.
    //  Open  http://localhost:8080/admin/users  as boss.
    // =================================================================
    private final UserRepository repo;

    private final SignupService signupService;   // Lesson 8

    public HelloController(UserRepository repo, SignupService signupService) {
        this.repo = repo;
        this.signupService = signupService;
    }

    // =================================================================
    //  LESSON 7 — TODO H: STOP SENDING THE DATABASE BOX TO THE BROWSER
    //
    //  Before: returned List<AppUser>  → JSON had the password hash 😱
    //  Now:    return List<UserView>   → only safe fields ✅
    //
    //  The loop goes through every AppUser from the database, one by one.
    //  For each one, copy its safe values into a NEW UserView box —
    //  same idea as copying AppUser → Spring's User box in TODO D!
    // =================================================================

    @GetMapping("/admin/users")
    public List<UserView> users() {
        List<UserView> result = new ArrayList<>();     // an empty list of safe boxes

        for (AppUser u : repo.findAll()) {              // for EACH user u in the DB...

            // TODO H: create a new UserView from u's SAFE values, and add it:
            //         result.add(new UserView( ... , ... , ... ));
            //         Hint: u.getId()  u.getUsername()  u.getRole()
            result.add(new UserView(u.getId(),u.getUsername(),u.getRole()));

        }

        return result;                                  // send the safe boxes
    }

    // =================================================================
    //  LESSON 8 — TODO D: THE SIGN-UP DOOR 🚪
    //
    //  GET  = "give me something"   (open a page)   → @GetMapping
    //  POST = "here, take this"     (send data)     → @PostMapping
    //
    //  Sign-up SENDS data (username + password), so it's a POST.
    @PostMapping("/signup")
    //
    //  TODO D1: on the empty line above the method, add the annotation
    //           for a POST on  /signup
    //  TODO D2: in front of  SignupRequest request  add the annotation
    //           that says "build this from the JSON in the request body"
    //           (look at the imports at the top of this file 😉)
    // =================================================================

    public UserView signup(@RequestBody SignupRequest request) {

        return signupService.signup(request);   // waiter → chef
    }
}
