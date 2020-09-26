package com.example.cryptocurrencywallet.controllerREST;

import com.example.cryptocurrencywallet.model.Coin;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.model.Wallet;
import com.example.cryptocurrencywallet.service.TransactionService;
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

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/buy", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public Transaction buyTrade(@RequestBody Transaction transaction) {
        System.out.println(transaction + " <<< BUY");
        return processTransactionRequest(transaction, BuySell.BUY);
    }

    @PostMapping(value = "/sell", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public Transaction sellTrade(@RequestBody Transaction transaction) {
        System.out.println(transaction + " <<< SELL");
        return processTransactionRequest(transaction, BuySell.SELL);
    }

    private Transaction processTransactionRequest(Transaction transaction, BuySell buySell) {
        System.out.println(transaction + " ==== " + buySell);
        Wallet loggedUserWallet = userService.getLoggedUser().getWallet();
        System.out.println("DONNE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(loggedUserWallet);

        transaction.setBuySell(buySell);
        transactionService.processTransaction(transaction, loggedUserWallet);
        return null;
    }
}
