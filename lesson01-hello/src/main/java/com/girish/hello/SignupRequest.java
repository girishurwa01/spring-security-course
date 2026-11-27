package com.girish.hello;

// =====================================================================
//  LESSON 8 — TODO A: THE "INCOMING" BOX
//
//  When someone signs up, the browser sends JSON like:
//      {"username":"priya", "password":"Sunflower#42"}
//  Spring turns that JSON INTO this record automatically.
//
//  UserView (Lesson 7) = the box going OUT to the browser
//  SignupRequest       = the box coming IN from the browser
//
//  TODO A: Replace ??? with the two fields the browser sends:
//            String username, String password
//
//  🤔 Think: why do we NOT put a 'role' field in here?
// =====================================================================
public record SignupRequest(String username, String password) {}
