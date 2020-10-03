package com.example.cryptocurrencywallet.controller;


import com.example.cryptocurrencywallet.model.MyUserDetails;
import com.example.cryptocurrencywallet.retriveCoin.model.CryptoCurrency;
import com.example.cryptocurrencywallet.service.CryptoCoinDetails;
import com.example.cryptocurrencywallet.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;

/**
 * @SpringBootTest is the general test annotation. It loads whole app and enables all auto-configuration.
 * It does not use slicing and not customize component scanning at all.
 * @ContextConfiguration limit the bean which will be auto-configure by Spring
 * @WebMvcTest is only going to scane the controller you have defined and the MVC infrastructure. If controller
 * has some dependency to toher beans from service layer, the test won't start until
 * you either load that config yoursefl or provide a mock for it. This is much faster as we only load a tiny
 * portion of app.
 */

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MainPageController.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//@SpringBootTest
public class MainPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @MockBean
    private CryptoCoinDetails cryptoCoinDetails;

    @MockBean
    private MyUserDetails userDetails;


}

