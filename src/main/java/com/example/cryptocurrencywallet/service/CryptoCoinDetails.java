package com.example.cryptocurrencywallet.service;

import com.example.cryptocurrencywallet.retriveCoin.model.CryptoCurrency;

import java.util.List;

public interface CryptoCoinDetails {

    List<CryptoCurrency> getListOfCryptoCurrencies(String coinNames);
}
