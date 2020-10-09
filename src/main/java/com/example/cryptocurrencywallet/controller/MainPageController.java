package com.example.cryptocurrencywallet.controller;

import com.example.cryptocurrencywallet.model.Coin;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.registration.controller.UserRegistrationController;
import com.example.cryptocurrencywallet.repository.UserRepository;
import com.example.cryptocurrencywallet.retriveCoin.model.CryptoCurrency;
import com.example.cryptocurrencywallet.service.CryptoCoinDetails;
import com.example.cryptocurrencywallet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainPageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainPageController.class);
    //assets
    private static final String TEN_ASSETS_TO_IMPORT_FROM_API = "BTC,ETH,USDT,XRP,BCH,DOT,LINK,BNB,CRO,LTC";

    // views
    private static final String VIEW_NAME_MAIN_ADMIN_PAGE = "mainAdminPage";
    private static final String VIEW_NAME_MAIN_USER_PAGE = "mainUserPage";
    private static final String VIEW_NAME_LOGIN = "login";

    private final CryptoCoinDetails cryptoCoinDetails;
    private final UserService userService;

    @Autowired
    public MainPageController(CryptoCoinDetails cryptoCoinDetails, UserService userService) {
        this.cryptoCoinDetails = cryptoCoinDetails;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return VIEW_NAME_LOGIN;
    }

    @RequestMapping("/user")
    public String mainUserView(Model model) {
        List<CryptoCurrency> listOfCrypto = cryptoCoinDetails.getListOfCryptoCurrencies(TEN_ASSETS_TO_IMPORT_FROM_API);
        User loggedUser = userService.getLoggedUser();
        LOGGER.info("\n" + loggedUser.getFirstName() + " " + loggedUser.getLastName() + "\n" + loggedUser.getEmail());

        model.addAttribute("cryptoCoinDetails", listOfCrypto);
        model.addAttribute("user", loggedUser);
        model.addAttribute("loggedUserBalance", loggedUser.getWallet().getBalanceUSD());
        model.addAttribute("loggedUserCoins", loggedUser.getWallet().getMyCoins());
        return VIEW_NAME_MAIN_USER_PAGE;
    }

//    @PostMapping("/user/buy")
//    public String buyCryptocurrency(@ModelAttribute("user") User user,
//                                    @ModelAttribute("cryptocurrency") CryptoCurrency cryptoCurrency,
//                                    @ModelAttribute("cryptoCoin") Coin coin) {
//        return VIEW_NAME_LOGIN;
//    }

    @RequestMapping("/admin")
    public String mainAdminView(Model model) {
        return VIEW_NAME_MAIN_ADMIN_PAGE;
    }

}


