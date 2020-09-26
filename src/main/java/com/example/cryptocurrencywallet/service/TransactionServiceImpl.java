package com.example.cryptocurrencywallet.service;

import com.example.cryptocurrencywallet.model.Coin;
import com.example.cryptocurrencywallet.model.TransactionHistory;
import com.example.cryptocurrencywallet.model.Wallet;
import com.example.cryptocurrencywallet.retriveCoin.model.CryptoCurrency;
import com.example.cryptocurrencywallet.transactions.Transaction;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TransactionServiceImpl implements TransactionService {


    /*
    * TODO How to make it correct and functional.
    *  To long and to many thing are happening in one method.
    * */

    @Override
    public Transaction processTransaction(Transaction transaction, Wallet loggedUserWallet) {
        /*
         * TODO: is SET will be better than LIST?
         * */
        List<TransactionHistory> userTransactions = loggedUserWallet.getTransactionHistories();
        System.out.println(userTransactions.size());
        List<Coin> userCoinsInWallet = loggedUserWallet.getMyCoins();
        System.out.println(userCoinsInWallet.size());
        System.out.println(transaction + " <<< Transaction inside processTranscation method");

        Coin newCoin = new Coin(transaction.getName(),transaction.getAmount(),transaction.getPrice());

        System.out.println("inside processTransaction()!");
        System.out.println(newCoin);

        if (isEnoughFunds(transaction, loggedUserWallet)) {
            System.out.println("BEGINNING - isEnoughFunds");
            transaction.setExecuted(true);
            transaction.setAmountGBP(transaction.getTotalPriceForTransaction());
            /*
             * TODO: Make it prettier.
             * */
            loggedUserWallet.setBalanceUSD(loggedUserWallet.getBalanceUSD().subtract(transaction.getAmountGBP()));
            userTransactions.add(new TransactionHistory(transaction));
            System.out.println("BEGINNING - isEnoughFunds ---> if else");
            if (isNewCoin(userCoinsInWallet, newCoin)){
                System.out.println("INSIDE IF");
                userCoinsInWallet.add(newCoin);
            }else {
                System.out.println("INSIDE ELSE");
                BigDecimal newBalance = findAndUpdateCoinBalance(userCoinsInWallet,newCoin);
                System.out.println(newBalance + " updated balance!");

                System.out.println(loggedUserWallet);
            }
            System.out.println("END - isEnoughFunds");
        }
        return transaction;
    }

    private BigDecimal findAndUpdateCoinBalance(List<Coin> userCoinsInWallet, Coin newCoin) {
        Coin userCoin = userCoinsInWallet.stream()
                .filter(coin -> coin.getCoinName().equals(newCoin.getCoinName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Wrong coin: " + newCoin.getCoinName()));

        userCoin.setAmount(userCoin.getAmount().add(newCoin.getAmount()));
        return userCoin.getAmount();
    }

    private boolean isNewCoin(List<Coin> userCoinsInWallet, Coin newCoin) {
        return userCoinsInWallet.stream()
                .map(Coin::getCoinName)
                .noneMatch(coin -> coin.equals(newCoin.getCoinName()));


    }

    private boolean isEnoughFunds(Transaction transaction, Wallet loggedUserWallet) {
        System.out.println("Iside isEnoughFunds!! ");
        System.out.println(loggedUserWallet.getBalanceUSD().subtract(transaction.getTotalPriceForTransaction()));
        System.out.println(loggedUserWallet.getBalanceUSD().subtract(transaction.getTotalPriceForTransaction()).compareTo(BigDecimal.ZERO) >= 0);
        return loggedUserWallet.getBalanceUSD().subtract(transaction.getTotalPriceForTransaction()).compareTo(BigDecimal.ZERO) >= 0;
    }
}
