package com.example.cryptocurrencywallet.controller;

import com.example.cryptocurrencywallet.model.Coin;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.repository.UserRepository;
import com.example.cryptocurrencywallet.retriveCoin.model.CryptoCurrency;
import com.example.cryptocurrencywallet.service.CryptoCoinDetails;
import com.example.cryptocurrencywallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class MainPageController {

    @Autowired
    private CryptoCoinDetails cryptoCoinDetails;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/user")
    public String mainUserView(Model model) {
        List<CryptoCurrency> listOfCrypto = cryptoCoinDetails.getListOfCryptoCurrencies("BTC,ETH,USDT,XRP,BCH,DOT,LINK,BNB,CRO,LTC");
        User loggedUser = userService.getLoggedUser();
        System.out.println(loggedUser.toString());
        model.addAttribute("cryptoCoinDetails", listOfCrypto);
        model.addAttribute("user", loggedUser);
        model.addAttribute("loggedUserBalance",loggedUser.getWallet().getBalanceUSD());
        return "mainUserPage";
    }

    @PostMapping("/user/buy")
    public String buyCryptocurrency(@ModelAttribute("user") User user,
                                    @ModelAttribute("cryptocurrency") CryptoCurrency cryptoCurrency,
                                    @ModelAttribute("cryptoCoin") Coin coin){
    return "/login";
    }

    @RequestMapping("/admin")
    public String mainAdminView(Model model) {
        return "mainAdminPage";
    }

}


