package com.example.cryptocurrencywallet.registration.controller;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.registration.validation.RegistrationManager;
import com.example.cryptocurrencywallet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import static com.example.cryptocurrencywallet.CommonTools.haveError;

/**
 * The @RestController annotation in Spring MVC is nothing but a combination of @Controller and @ResponseBody annotation.
 * It was added into Spring 4.0 to make the development of REST-full Web Services in Spring framework easier.
 * If you are familiar with the REST web services you know that the fundamental difference between a web application
 * and a REST API is that the response from a web application is generally view (HTML + CSS + JavaScript)
 * because they are intended for human viewers while REST API just returns data in form of JSON or XML because
 * most of the REST clients are programs.
 */
@Controller
@RequestMapping
public class UserRegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationController.class);

    private static final String VIEW_NAME_REGISTRATION_FORM = "registration";
    private static final String VIEW_REDIRECT_REGISTRATION_SUCCESS = "redirect:/registration?success";

    private static final String MODEL_ATTRIBUTE_USER_FORM = "userForm";

    private final UserService userService;
    private final RegistrationManager registrationManager;

    @Autowired
    public UserRegistrationController(UserService userService,
                                      RegistrationManager registrationManager) {
        this.registrationManager = registrationManager;
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        LOGGER.info("Rendering register user form");

        model.addAttribute(MODEL_ATTRIBUTE_USER_FORM, new UserRegistrationDTO());
        return VIEW_NAME_REGISTRATION_FORM;
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute(MODEL_ATTRIBUTE_USER_FORM) UserRegistrationDTO userForm,
                                      Model model) {

        LOGGER.info("Registering new user: {} ", userForm);
        model = registrationManager.registerUser(userForm, model);

        if (haveError(model)) {
            LOGGER.error("Registering user form was submitted with errors. ");
            return VIEW_NAME_REGISTRATION_FORM;
        }

        userService.save(userForm);
        LOGGER.info("Registered new user: \n{} {}\n{}", userForm.getFirstName(), userForm.getLastName(), userForm.getEmail());
        return VIEW_REDIRECT_REGISTRATION_SUCCESS;
    }
}
