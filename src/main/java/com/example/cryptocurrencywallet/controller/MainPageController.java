package com.example.cryptocurrencywallet.controller;

import com.example.cryptocurrencywallet.service.CryptoCoinDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainPageController {

    @Autowired
    private CryptoCoinDetails cryptoCoinDetails;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/user")
    public String mainUserView(Model model) {
        model.addAttribute("cryptoCoinDetails", cryptoCoinDetails.getListOfCryptoCurrencies("BTC,ETH,USDT,XRP,BCH,DOT,LINK,BNB,CRO,LTC"));
        return "mainUserPage";
    }

    @RequestMapping("/admin")
    public String mainAdminView(Model model) {
        return "mainAdminPage";

    }
}
