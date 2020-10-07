package com.example.cryptocurrencywallet.controller;

import com.example.cryptocurrencywallet.model.Coin;
import com.example.cryptocurrencywallet.retriveCoin.model.CryptoCurrency;
import com.example.cryptocurrencywallet.service.CryptoCoinDetails;
import com.example.cryptocurrencywallet.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@PreAuthorize("hasRole('ROLE_USER')")
public class AccountDetailsController {


    private UserService userService;
    private CryptoCoinDetails coinDetails;

    public AccountDetailsController(UserService userService, CryptoCoinDetails coinDetails) {
        this.userService = userService;
        this.coinDetails = coinDetails;
    }

    @ModelAttribute
    public void saveLoggedUserDetails(Model model) throws IOException, InterruptedException {
        model.addAttribute("user", userService.getLoggedUser());
        model.addAttribute("loggedUserBalance", userService.getLoggedUser().getWallet().getBalanceUSD());
        model.addAttribute("loggedUserCurrencies", userService.getLoggedUser().getWallet().getMyCoins());
        model.addAttribute("loggedUserTransactionHistory", userService.getLoggedUser().getWallet().getTransactionHistories());
        model.addAttribute("loggedUserCoinsCurrentValue", userService.userCurrentCoinValue(userService.getLoggedUser()));

    }

    @GetMapping(value = "/accountDetails")
    public String getAccountDetailsPage() {
        return "accountDetails";
    }

}