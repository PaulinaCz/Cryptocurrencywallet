package com.example.cryptocurrencywallet.controller;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@PreAuthorize("hasRole('ROLE_USER')")
public class AccountDetailsController {

    @Autowired
    UserService userService;

    @ModelAttribute
    public void saveLoggedUserDetails(Model model) {
        model.addAttribute("loggedUser", userService.getLoggedUser());
        model.addAttribute("loggedUserBalance", userService.getLoggedUser().getWallet().getBalanceUSD());
        model.addAttribute("loggedUserCurrencies", userService.getLoggedUser().getWallet().getMyCoins());
        model.addAttribute("loggedUserTransactionHistory", userService.getLoggedUser().getWallet().getTransactionHistories());

    }

    @GetMapping
    public String showUpdateForm(Model model){
        model.addAttribute("user", new UserRegistrationDTO());
        return "update";
    }

    @GetMapping(value = "/accountDetails")
    public String getAccountDetailsPage() {
        return "accountDetails";
    }

    @PutMapping(value = "/updateAccount")
    public String updateUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDto) {
        userService.save(registrationDto);
        return "accountDetails";
    }

}