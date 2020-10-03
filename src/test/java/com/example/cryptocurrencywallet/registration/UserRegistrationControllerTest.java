package com.example.cryptocurrencywallet.registration;


import com.example.cryptocurrencywallet.config.CustomLoginSuccessHandler;
import com.example.cryptocurrencywallet.config.MyUserDetailsService;
import com.example.cryptocurrencywallet.controller.MainPageController;
import com.example.cryptocurrencywallet.model.MyUserDetails;
import com.example.cryptocurrencywallet.registration.controller.UserRegistrationController;
import com.example.cryptocurrencywallet.registration.validation.RegistrationManager;
import com.example.cryptocurrencywallet.service.UserService;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(UserRegistrationController.class)
//@ContextConfiguration(classes = {MyUserDetails.class ,MyUserDetailsService.class, CustomLoginSuccessHandler.class })
//@SpringBootTest
//@AutoConfigureMockMvc
public class UserRegistrationControllerTest {

    private MockMvc mockMvc;

    private UserRegistrationController userRegistrationController;

//    @MockBean
//    private UserService service;

    @BeforeEach
    public void setUp(){
        userRegistrationController = new UserRegistrationController();
    }


/*
    @MockBean
    private MyUserDetails userDetails;

    @MockBean
    private MyUserDetailsService myUserDetailsService;

    @MockBean
    private CustomLoginSuccessHandler successHandler;
*/

    @Test
//    @WithMockUser(username = "user")
    @DisplayName("test initialization of creation form")
    public void test1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/registartion"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userForm"))
                .andExpect(view().name("registration"));
    }
}
