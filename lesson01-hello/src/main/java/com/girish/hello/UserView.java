package com.girish.hello;

// =====================================================================
//  LESSON 7 — FIX THE LEAK: THE "SAFE BOX" (a DTO = Data Transfer Object)
//
//  AppUser  = the DATABASE box  (has the password hash ⚠️)
//  UserView = the BROWSER box   (only SAFE fields)
//
//  A 'record' is a short way to write a simple box in Java.
//  This ONE line:
//      public record Point(int x, int y) {}
//  gives you a class with fields x and y, a constructor new Point(1, 2),
//  and getters — all automatically. Spring turns it into JSON:
//      {"x":1,"y":2}
//
//  TODO G: Replace the  ???  with the fields that are SAFE to show:
//            - Long id
//            - String username
//            - String role
//          (separated by commas, like the Point example)
//          ⚠️ Which field must NOT be here? 😉
// =====================================================================
public record UserView(Long id, String username, String role) {

}
