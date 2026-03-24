package com.example.enterprise.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class Usercontroller {
    @RequestMapping("/login")
    public String login(String username,String password){
        System.out.println(username);
        System.out.println(password);
        return "success";
    }
}
