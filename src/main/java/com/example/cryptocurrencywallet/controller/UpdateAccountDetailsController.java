package com.example.cryptocurrencywallet.controller;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/update")
public class UpdateAccountDetailsController {

    private UserService userService;


    public UpdateAccountDetailsController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String showUpdateForm(Model model){
        model.addAttribute("user", new UserRegistrationDTO());
        return "update";
    }

    @PostMapping
    public String updateUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDTO) {
        User updatedUser = userService.getLoggedUser();

        userService.update(updatedUser, registrationDTO);
        return "mainUserPage";

    }

}
