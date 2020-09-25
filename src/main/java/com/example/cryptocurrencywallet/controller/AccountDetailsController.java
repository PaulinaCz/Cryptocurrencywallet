package com.example.cryptocurrencywallet.controller;

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
    private UserService userService;


    @ModelAttribute
    public void saveLoggedUserDetails(Model model) {
        model.addAttribute("user", userService.getLoggedUser());
        model.addAttribute("loggedUserBalance", userService.getLoggedUser().getWallet().getBalanceUSD());
        model.addAttribute("loggedUserCurrencies", userService.getLoggedUser().getWallet().getMyCoins());
        model.addAttribute("loggedUserTransactionHistory", userService.getLoggedUser().getWallet().getTransactionHistories());

    }
    @GetMapping(value = "/accountDetails")
    public String getAccountDetailsPage() {
        return "accountDetails";
    }

}