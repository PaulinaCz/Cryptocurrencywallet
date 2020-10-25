package com.example.cryptocurrencywallet.registration;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerTest {

    //model
    private static final String MODEL_ATTRIBUTE_USER_FORM = "userForm";

    //Form Field / model attribute fields
    private static final String USER_FORM_PROPERTY_FIRST_NAME = "firstName";
    private static final String USER_FORM_PROPERTY_LAST_NAME = "lastName";
    private static final String USER_FORM_PROPERTY_EMAIL = "email";
    private static final String USER_FORM_PROPERTY_PASSWORD = "password";
    private static final String USER_FORM_PROPERTY_REPEAT_PASSWORD = "repeatPassword";

    //view
    private static final String VIEW_NAME_REGISTRATION_FORM = "registration";
    private static final String VIEW_NAME_REGISTRATION_FORM_SUCCESS = "redirect:/registration?success";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should initialize new registration form")
    public void test1() throws Exception {
        mockMvc.perform(get("http://localhost8080/registration"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(MODEL_ATTRIBUTE_USER_FORM, allOf(
                        hasProperty(USER_FORM_PROPERTY_FIRST_NAME, nullValue()),
                        hasProperty(USER_FORM_PROPERTY_LAST_NAME, nullValue()),
                        hasProperty(USER_FORM_PROPERTY_EMAIL, nullValue()),
                        hasProperty(USER_FORM_PROPERTY_PASSWORD, nullValue()),
                        hasProperty(USER_FORM_PROPERTY_REPEAT_PASSWORD, nullValue())
                )))
                .andExpect(view().name(VIEW_NAME_REGISTRATION_FORM));
    }

    @Test
    @DisplayName("should process new registration form success")
    public void test2() throws Exception {
        mockMvc.perform(post("http://localhost8080/registration")
                .param(USER_FORM_PROPERTY_FIRST_NAME, "Issac")
                .param(USER_FORM_PROPERTY_LAST_NAME, "Newton")
                .param(USER_FORM_PROPERTY_EMAIL, "issac@newton.com")
                .param(USER_FORM_PROPERTY_PASSWORD, "Newton1643")
                .param(USER_FORM_PROPERTY_REPEAT_PASSWORD, "Newton1643"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(VIEW_NAME_REGISTRATION_FORM_SUCCESS));
    }

    @Test
    @DisplayName("should not process new registration form without first name")
    public void test3() throws Exception {
        mockMvc.perform(post("http://localhost8080/registration")
                .param(USER_FORM_PROPERTY_FIRST_NAME, "")
                .param(USER_FORM_PROPERTY_LAST_NAME, "Newton")
                .param(USER_FORM_PROPERTY_EMAIL, "issac@newton.com")
                .param(USER_FORM_PROPERTY_PASSWORD, "Newton1643")
                .param(USER_FORM_PROPERTY_REPEAT_PASSWORD, "Newton1643"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_REGISTRATION_FORM));
    }
    @Test
    @DisplayName("should not process new registration form without last name")
    public void test4() throws Exception {
        mockMvc.perform(post("http://localhost8080/registration")
                .param(USER_FORM_PROPERTY_FIRST_NAME, "Issac")
                .param(USER_FORM_PROPERTY_LAST_NAME, "")
                .param(USER_FORM_PROPERTY_EMAIL, "issac@newton.com")
                .param(USER_FORM_PROPERTY_PASSWORD, "Newton1643")
                .param(USER_FORM_PROPERTY_REPEAT_PASSWORD, "Newton1643"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_REGISTRATION_FORM));
    }
    @Test
    @DisplayName("should not process new registration form without email")
    public void test5() throws Exception {
        mockMvc.perform(post("http://localhost8080/registration")
                .param(USER_FORM_PROPERTY_FIRST_NAME, "Issac")
                .param(USER_FORM_PROPERTY_LAST_NAME, "Newton")
                .param(USER_FORM_PROPERTY_EMAIL, "")
                .param(USER_FORM_PROPERTY_PASSWORD, "Newton1643")
                .param(USER_FORM_PROPERTY_REPEAT_PASSWORD, "Newton1643"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_REGISTRATION_FORM));
    }
    @Test
    @DisplayName("should not process new registration form without password")
    public void test6() throws Exception {
        mockMvc.perform(post("http://localhost8080/registration")
                .param(USER_FORM_PROPERTY_FIRST_NAME, "Issac")
                .param(USER_FORM_PROPERTY_LAST_NAME, "Newton")
                .param(USER_FORM_PROPERTY_EMAIL, "issac@newton.com")
                .param(USER_FORM_PROPERTY_PASSWORD, "")
                .param(USER_FORM_PROPERTY_REPEAT_PASSWORD, "Newton1643"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_REGISTRATION_FORM));
    }
    @Test
    @DisplayName("should not process new registration form without repeat password")
    public void test7() throws Exception {
        mockMvc.perform(post("http://localhost8080/registration")
                .param(USER_FORM_PROPERTY_FIRST_NAME, "Issac")
                .param(USER_FORM_PROPERTY_LAST_NAME, "Newton")
                .param(USER_FORM_PROPERTY_EMAIL, "issac@newton.com")
                .param(USER_FORM_PROPERTY_PASSWORD, "Newton1643")
                .param(USER_FORM_PROPERTY_REPEAT_PASSWORD, ""))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_REGISTRATION_FORM));
    }


}
