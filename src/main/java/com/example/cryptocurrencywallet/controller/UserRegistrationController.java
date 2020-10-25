package com.example.cryptocurrencywallet.controller;

import com.example.cryptocurrencywallet.model.UserRegistrationDTO;
import com.example.cryptocurrencywallet.service.RegistrationManager;
import com.example.cryptocurrencywallet.service.UserService;
import com.example.cryptocurrencywallet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import static com.example.cryptocurrencywallet.CommonTools.haveError;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserRegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationController.class);

    private static final String VIEW_NAME_REGISTRATION_FORM = "registration";
    private static final String VIEW_REDIRECT_REGISTRATION_SUCCESS = "redirect:/registration?success";

    private static final String MODEL_ATTRIBUTE_USER_FORM = "userForm";

    private final UserService userService;
    private final RegistrationManager registrationManager;


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
