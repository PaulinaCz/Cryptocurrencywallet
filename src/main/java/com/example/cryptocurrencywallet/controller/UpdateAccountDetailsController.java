package com.example.cryptocurrencywallet.controller;

import com.example.cryptocurrencywallet.model.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.service.UpdateManager;
import com.example.cryptocurrencywallet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.cryptocurrencywallet.CommonTools.haveError;


@Controller
@RequestMapping("/update")
@RequiredArgsConstructor
public class UpdateAccountDetailsController {

    private final UserService userService;
    private final UpdateManager updateManager;

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
