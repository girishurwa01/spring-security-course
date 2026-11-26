package com.girish.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
