package com.example.cryptocurrencywallet.service;

public class CoinInUserWalletNotFound extends Exception {
    public CoinInUserWalletNotFound(String msg) {
        super(msg);
    }
}
