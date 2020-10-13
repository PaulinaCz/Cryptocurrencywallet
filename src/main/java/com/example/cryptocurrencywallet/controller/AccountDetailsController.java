package com.example.cryptocurrencywallet.controller;

import com.example.cryptocurrencywallet.retriveCoin.model.CryptoCurrency;
import com.example.cryptocurrencywallet.service.CryptoCoinDetails;
import com.example.cryptocurrencywallet.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public void saveLoggedUserDetails(Model model) {
        List<CryptoCurrency> listOfCrypto = coinDetails.getListOfCryptoCurrencies("BTC,ETH,USDT,XRP,BCH,DOT,LINK,BNB,CRO,LTC");

        model.addAttribute("user", userService.getLoggedUser());
        model.addAttribute("loggedUserBalance", userService.getLoggedUser().getWallet().getBalanceUSD());
        model.addAttribute("loggedUserCurrencies", userService.getLoggedUser().getWallet().getMyCoins());
        model.addAttribute("loggedUserTransactionHistory", userService.getLoggedUser().getWallet().getTransactionHistories());
        model.addAttribute("cryptoCoinDetails", listOfCrypto);

    }

    @GetMapping(value = "/accountDetails")
    public String getAccountDetailsPage() {
        return "accountDetails";
    }

}