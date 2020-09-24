package com.example.cryptocurrencywallet.controller;

import com.example.cryptocurrencywallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@PreAuthorize("hasRole('ROLE_USER')")
public class AccountDetailsController {

    @Autowired
    UserService userService;

    @ModelAttribute
    public void saveLoggedUserDetails(Model model){
        model.addAttribute("loggedUser", userService.getLoggedUser());
        model.addAttribute("loggedUserBalance", userService.getLoggedUser().getWallet().getBalanceUSD());
        model.addAttribute("loggedUserCurrencies", userService.getLoggedUser().getWallet().getMyCoins());
        model.addAttribute("loggedUserTransactionHistory", userService.getLoggedUser().getWallet().getTransactionHistories());


    }

    @RequestMapping(value = "/accountDetails", method = RequestMethod.GET)
    public String getAccountDetailsPage(){
        return "accountDetails";
    }

}
