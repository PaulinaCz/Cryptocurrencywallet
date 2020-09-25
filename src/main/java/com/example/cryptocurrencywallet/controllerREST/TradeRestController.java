package com.example.cryptocurrencywallet.controllerREST;

import com.example.cryptocurrencywallet.model.Coin;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.model.Wallet;
import com.example.cryptocurrencywallet.service.UserService;
import com.example.cryptocurrencywallet.transactions.BuySell;
import com.example.cryptocurrencywallet.transactions.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/transaction")
public class TradeRestController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/buy", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public Transaction buyTrade(@RequestBody Transaction coin){
        System.out.println(coin + " <<< BUY");
        return processTransactionRequest(coin, BuySell.BUY);
    }

    @PostMapping(value = "/sell", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public Transaction sellTrade(@RequestBody Transaction coin){
        System.out.println(coin + " <<< SELL");
        return processTransactionRequest(coin, BuySell.SELL);
    }

    private Transaction processTransactionRequest(Transaction coin, BuySell buySell) {
        System.out.println(coin + " ==== " + buySell);
        Wallet loggedUserWallet = userService.getLoggedUser().getWallet();
        System.out.println("DONNE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(loggedUserWallet);
        return null;
    }
}