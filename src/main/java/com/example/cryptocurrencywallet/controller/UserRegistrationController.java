package com.example.cryptocurrencywallet.controller;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
/*
 * The @RestController annotation in Spring MVC is nothing but a combination of @Controller and @ResponseBody annotation.
 * It was added into Spring 4.0 to make the development of REST-full Web Services in Spring framework easier.
 * If you are familiar with the REST web services you know that the fundamental difference between a web application
 * and a REST API is that the response from a web application is generally view (HTML + CSS + JavaScript)
 * because they are intended for human viewers while REST API just returns data in form of JSON or XML because
 * most of the REST clients are programs.
 * */
@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new UserRegistrationDTO());
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDto) {
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
