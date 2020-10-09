package com.example.cryptocurrencywallet.controller;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.service.UserService;
import com.example.cryptocurrencywallet.validation.UpdateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.cryptocurrencywallet.CommonTools.haveError;


@Controller
@RequestMapping("/update")
public class UpdateAccountDetailsController {

    private UserService userService;
    private UpdateManager updateManager;

    @Autowired
    public UpdateAccountDetailsController(UserService userService, UpdateManager updateManager) {
        this.userService = userService;
        this.updateManager = updateManager;
    }


    @GetMapping
    public String showUpdateForm(Model model){
        model.addAttribute("user", new UserRegistrationDTO());
        return "update";
    }

    @PostMapping
    public String updateUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDTO, Model model) {
        User updatedUser = userService.getLoggedUser();
        model = updateManager.updateUser(registrationDTO, model);

        if(haveError(model)){
            return "update";
        }

        userService.update(updatedUser.getId(), registrationDTO);
        return "redirect:/user";

    }

}
