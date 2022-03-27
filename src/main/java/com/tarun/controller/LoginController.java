package com.tarun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showMyLoginPage() {
        return "login";
    }
    @RequestMapping("/header")
    public String showMustGoOn(){
        return "header";
    }
    // add request mapping for /access-denied
    @PostMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
