package com.example.cryptocurrencywallet.controllerREST;


import com.example.cryptocurrencywallet.model.MyUserDetails;
import com.example.cryptocurrencywallet.service.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @Autowired service/component to test
 * @MockBean all the dependencies
 * @SpyBean to mock only specific methods
 * */

@ExtendWith(SpringExtension.class)
@WebMvcTest(TradeRestController.class)
public class TradeRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @MockBean
    private MyUserDetails userDetails;



}
