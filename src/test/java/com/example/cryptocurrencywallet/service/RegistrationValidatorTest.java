package com.example.cryptocurrencywallet.service;


import com.example.cryptocurrencywallet.UserDTOBuilder;
import com.example.cryptocurrencywallet.model.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
public class RegistrationValidatorTest {

    @Autowired
    private RegistrationValidator validator;

    @Autowired
    private UserRepository userRepository;

    private UserRegistrationDTO user;
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ExtendedModelMap();
    }

    @Test
    @DisplayName("validate fields completion should return error")
    public void test1() {
        //given
        user = new UserDTOBuilder()
                .withFirstName("")
                .withLastName("")
                .withEmail("")
                .withPassword("")
                .withRepeatPassword("")
                .buildUserRegistrationDTO();
        // when
        model = validator.validateFieldsCompletion(user, model);
        // then
        assertThat(model.asMap().containsKey("error"), is(true));
        assertThat(model.asMap().containsValue("All fields must be completed."), is(true));
    }

    @Test
    @DisplayName("validate fields completion should not return error")
    public void test2() {
        //given
        user = new UserDTOBuilder()
                .withFirstName("Frodo")
                .withLastName("Baggins")
                .withEmail("fellowship@ring.com")
                .withPassword("Hobbit")
                .withRepeatPassword("Hobbit")
                .buildUserRegistrationDTO();
        // when
        model = validator.validateFieldsCompletion(user, model);
        // then
        assertThat(model.asMap().containsKey("error"), is(false));

    }

    static List<String> invalidEmailAddresses() {
        return List.of("fellowship@@ring.com", "@ring.com", "ring.com", "fellowship@ring", "fellowship@ring..com", "fellowship@ri.ng.com", "fellow;ship@ring.com"
                , "fell*owship@ring.com", "fellow:ship@ring.com", "fellowshi/p@ring.com", "fellow(ship@ring.com", "fell)owship@ring.com", "f[ellowship@ring.com",
                "fellowship@r]ing.com");
    }

    @ParameterizedTest
    @MethodSource("invalidEmailAddresses")
    @DisplayName("validate email should return invalid e-mail format")
    public void test3(String email) {
        //given
        user = new UserDTOBuilder().withEmail(email).buildUserRegistrationDTO();
        // when
        model = validator.validateEmail(user, model);
        // then
        assertThat(model.asMap().containsKey("error"), is(true));
        assertThat(model.asMap().containsValue("Invalid e-mail address format."), is(true));
    }

    @Test
    @DisplayName("validate email should return e-mail is already in use")
    public void test4() {
        //given
        User tempUser = new UserDTOBuilder()
                .withFirstName("Frodo")
                .withLastName("Baggins")
                .withEmail("fellowship@ring.com")
                .withPassword("Hobbit")
                .buildUserWithRoleUser();

        userRepository.save(tempUser);

        user = new UserDTOBuilder()
                .withEmail("fellowship@ring.com")
                .buildUserRegistrationDTO();
        // when
        model = validator.validateEmail(user, model);
        // then
        assertThat(model.asMap().containsKey("error"), is(true));
        assertThat(model.asMap().containsValue("The email is already in use."), is(true));
    }

    @Test
    @DisplayName("validate email should not return error")
    public void test5() {
        //given
        user = new UserDTOBuilder()
                .withEmail("twoTowers@ring.com")
                .buildUserRegistrationDTO();
        // when
        model = validator.validateEmail(user, model);
        // then
        assertThat(model.asMap().containsKey("error"), is(false));
    }

    @Test
    @DisplayName("validate password should not return error")
    public void test6() {
        //given
        user = new UserDTOBuilder()
                .withPassword("Hobbit2020")
                .withRepeatPassword("Hobbit2020")
                .buildUserRegistrationDTO();
        // when
        model = validator.validatePassword(user, model);
        // then
        assertThat(model.asMap().containsKey("error"), is(false));
    }

    static List<String[]> invalidPasswords() {
        return List.of(new String[]{"asdfgh", "ASDFGH"},
                new String[]{"Asdfgh", "AsdfgH"},
                new String[]{"Asdfgh", "AsdfgH"},
                new String[]{"Asdfgh", "AsdfgH2"},
                new String[]{"asdfgh", "asdFgh"},
                new String[]{"asdFgh.2", "asdFgh.1"},
                new String[]{"123456", "12345f"});
    }

    @ParameterizedTest
    @MethodSource("invalidPasswords")
    @DisplayName("validate password should return passwords does not match.")
    public void test7(String password, String repeatPassword) {
        //given
        user = new UserDTOBuilder()
                .withPassword(password)
                .withRepeatPassword(repeatPassword)
                .buildUserRegistrationDTO();
        // when
        model = validator.validatePassword(user, model);
        // then
        assertThat(model.asMap().containsKey("error"), is(true));
        assertThat(model.asMap().containsValue("Passwords does not match."), is(true));
    }

    static List<String[]> invalidPasswordsLength() {
        return List.of(new String[]{"asd", "asd"},
                new String[]{"asdASDasdASDasdasdASDasdASDasdASD", "asdASDasdASDasdasdASDasdASDasdASD"},
                new String[]{"asdqw", "asdqw"});
    }

    @ParameterizedTest
    @MethodSource("invalidPasswordsLength")
    @DisplayName("validate email should return invalid e-mail format")
    public void test8(String password, String repeatPassword) {
        //given
        user = new UserDTOBuilder()
                .withPassword(password)
                .withRepeatPassword(repeatPassword)
                .buildUserRegistrationDTO();
        // when
        model = validator.validatePassword(user, model);
        // then
        assertThat(model.asMap().containsKey("error"), is(true));
        assertThat(model.asMap().containsValue("Password must have at least 6 and less than 32 characters long."), is(true));
    }
}
