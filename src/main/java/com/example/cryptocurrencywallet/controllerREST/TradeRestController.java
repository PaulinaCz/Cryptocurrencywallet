package com.example.cryptocurrencywallet.controllerREST;

import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.model.Wallet;
import com.example.cryptocurrencywallet.service.TransactionService;
import com.example.cryptocurrencywallet.service.UserService;
import com.example.cryptocurrencywallet.transactions.BuySell;
import com.example.cryptocurrencywallet.transactions.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/transaction")
public class TradeRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeRestController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/buy", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public User buyTrade(@RequestBody Transaction transaction) {
        LOGGER.info(transaction + " inside buyTrade() method");
        return processTransactionRequest(transaction, BuySell.BUY);
    }

    @PostMapping(value = "/sell", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public User sellTrade(@RequestBody Transaction transaction) {
//        System.out.println(transaction + " <<< SELL");
        return processTransactionRequest(transaction, BuySell.SELL);
    }

    private User processTransactionRequest(Transaction transaction, BuySell buySell) {
        LOGGER.info(transaction + "transaction - inside processTransactionRequest() method");
        LOGGER.info(buySell + "buySell inside - processTransactionRequest() method");
        Wallet loggedUserWallet = userService.getLoggedUser().getWallet();
        LOGGER.info(loggedUserWallet + "wallet of logged user - inside processTransactionRequest() method");
        transaction.setBuySell(buySell);
        transactionService.processTransaction(transaction, loggedUserWallet);
        return null;
    }
}
