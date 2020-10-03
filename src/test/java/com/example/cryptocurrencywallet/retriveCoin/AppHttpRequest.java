package com.example.cryptocurrencywallet.retriveCoin;

import com.example.cryptocurrencywallet.retriveCoin.model.CryptoCurrency;
import com.example.cryptocurrencywallet.service.CryptoCoinDetails;
import com.example.cryptocurrencywallet.service.CryptoCoinDetailsImpl;
import org.junit.jupiter.api.*;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;

public class AppHttpRequest {

    private static CryptoCoinDetails cryptoCoinDetails = null;
    private static String asset = "";

    private static List<CryptoCurrency> listOfAssets = null;

    @BeforeAll
    public static void setUp() {
        cryptoCoinDetails = new CryptoCoinDetailsImpl();
        asset = "BTC,ETH,USDT,XRP,BCH,DOT,LINK,BNB,CRO,LTC";
        listOfAssets = cryptoCoinDetails.getListOfCryptoCurrencies(asset);
    }

    @Test
    @DisplayName("Is list not empty")
    public void test1() {
        assertThat(listOfAssets, is(not(empty())));
    }

    @Test
    @DisplayName("is importing 10 coins")
    public void test12() {
        int numberOfCoinsInList = listOfAssets.size();
        int expectedLengthOfList = 10;

        Assertions.assertEquals(numberOfCoinsInList, expectedLengthOfList);

    }


}

