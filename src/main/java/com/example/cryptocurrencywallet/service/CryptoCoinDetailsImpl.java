package com.example.cryptocurrencywallet.service;


import com.example.cryptocurrencywallet.externallApi.AppHttpRequests;
import com.example.cryptocurrencywallet.externallApi.model.CryptoCurrency;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Getter
@Setter
@Transactional
public class CryptoCoinDetailsImpl implements CryptoCoinDetails {

    @Override
    public List<CryptoCurrency> getListOfCryptoCurrencies(String coinNames) {
        List<CryptoCurrency> coinResponse = null;
        try {
            coinResponse = AppHttpRequests.requestCoin(coinNames);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return coinResponse;
    }

}
