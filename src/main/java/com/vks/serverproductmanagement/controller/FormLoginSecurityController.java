package com.vks.serverproductmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormLoginSecurityController {

    @GetMapping("/signin")
    public String signin() {
        return "login.html";
    }

    @GetMapping("/logout")
    public String logout(){
        return "logout.html";
    }
}
