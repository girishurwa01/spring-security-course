package com.girish.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This is the START button of your app. You don't need to change anything here.
// @SpringBootApplication tells Spring: "scan this package, find my classes, wire them up".
@SpringBootApplication
public class HelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }
}
