package com.example.cryptocurrencywallet;

import com.example.cryptocurrencywallet.controller.MainPageController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class CryptocurrencywalletApplicationTest {

    @Autowired
    private MainPageController controller;

    @Test
    @DisplayName("is context creating MainPAgeController")
    void contextLoads(){
        assertThat(controller).isNotNull();
    }

}
