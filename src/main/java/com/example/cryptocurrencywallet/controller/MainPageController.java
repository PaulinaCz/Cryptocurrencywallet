package com.example.cryptocurrencywallet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainPageController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
//    @GetMapping("/user")
//    public String home() {
//        return "index";
//    }
//
    @GetMapping("/user")
    public String mainUserView(Model model) {
        return "mainUserPage";
    }

    @RequestMapping("/admin")
    public String mainAdminView(Model model) {
        return "mainAdminPage";

    }
}
