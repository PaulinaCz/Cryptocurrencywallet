package com.example.cryptocurrencywallet.registration.controller;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.registration.validation.RegistrationManager;
import com.example.cryptocurrencywallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.cryptocurrencywallet.CommonTools.haveError;

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
    private RegistrationManager registrationManager;

    @Autowired
    public UserRegistrationController(UserService userService, RegistrationManager registrationManager) {
        this.registrationManager = registrationManager;
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new UserRegistrationDTO());
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("userForm") UserRegistrationDTO userForm, Model model) {
        model = registrationManager.registerUser(userForm, model);
        if (haveError(model)){
            return "registration";
        }
        userService.save(userForm);
        return "redirect:/registration?success";
    }
}
