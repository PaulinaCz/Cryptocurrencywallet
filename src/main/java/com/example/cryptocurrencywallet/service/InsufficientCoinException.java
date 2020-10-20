package com.example.cryptocurrencywallet.service;

public class InsufficientCoinException extends Exception {
    public InsufficientCoinException(String msg) {
        super(msg);
    }
}
