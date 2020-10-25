package com.example.cryptocurrencywallet.service;

import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.model.Wallet;
import com.example.cryptocurrencywallet.model.Transaction;

public interface TransactionService {

    User processTransaction(Transaction transaction, Wallet loggedUserWallet) throws CoinInUserWalletNotFound, InsufficientFundsException, InsufficientCoinException;
}
