package com.example.cryptocurrencywallet.externallApi;

import com.example.cryptocurrencywallet.externallApi.model.CryptoCurrency;
import com.example.cryptocurrencywallet.service.CryptoCoinDetails;
import com.example.cryptocurrencywallet.service.CryptoCoinDetailsImpl;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;

public class AppHttpRequestTest {

    // 10 cryptocurrencies
    private static final String TEN_ASSETS_TO_IMPORT_FROM_API = "BTC,ETH,USDT,XRP,BCH,DOT,LINK,BNB,CRO,LTC";

    private static CryptoCoinDetails cryptoCoinDetails;
    private static String asset;

    private static List<CryptoCurrency> listOfAssets;

    @BeforeAll
    public static void setUp() {
        cryptoCoinDetails = new CryptoCoinDetailsImpl();
        asset = TEN_ASSETS_TO_IMPORT_FROM_API;
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

