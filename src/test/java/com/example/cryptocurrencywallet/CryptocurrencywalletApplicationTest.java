package com.example.cryptocurrencywallet;

import com.example.cryptocurrencywallet.controller.MainPageController;
import com.example.cryptocurrencywallet.controller.TradeRestController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class CryptocurrencywalletApplicationTest {

    @Autowired
    private MainPageController controller;

    @Autowired
    private TradeRestController tradeRestController;

    @Test
    @DisplayName("is context creating MainPAgeController")
    void test1(){
        assertThat(controller).isNotNull();
    }

    @Test
    @DisplayName("is context creating TradeRegisterController")
    void test2(){
        assertThat(tradeRestController).isNotNull();
    }

}
