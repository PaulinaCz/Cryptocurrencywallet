package com.example.cryptocurrencywallet.repository;

import com.example.cryptocurrencywallet.UserDTOBuilder;
import com.example.cryptocurrencywallet.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    public void initializeDataBaseWithUsers() {
        User userOne = new UserDTOBuilder()
                .withFirstName("Bob")
                .withLastName("Barclays")
                .withEmail("bob@gmail.com")
                .withPassword("asd123")
                .buildUserWithRoleUser();
        User userTwo = new UserDTOBuilder()
                .withFirstName("Jaco")
                .withLastName("Pastorius")
                .withEmail("Jaco@jazz.com")
                .withPassword("bassLine")
                .buildUserWithRoleUser();
        userRepository.save(userOne);
        userRepository.save(userTwo);
    }

    @Test
    @DisplayName(" should not return empty list")
    public void test1() {
        // assertj
        assertThat(userRepository.getAllBy()).isNotEmpty();
        assertThat(userRepository.getAllBy()).hasSize(2);
    }

    @Test
    @DisplayName(" should return correct list size")
    public void test2() {
        // assertj
        assertThat(userRepository.getAllBy()).isNotEmpty();
        assertThat(userRepository.getAllBy()).hasSize(2);
    }

    @Test
    @DisplayName("should find by email")
    void test3() {
        // jupiter
        Assertions.assertTrue(userRepository.findByEmail("Jaco@jazz.com").isPresent());
        Assertions.assertTrue(userRepository.findByEmail("bob@gmail.com").isPresent());
    }

    @Test
    @DisplayName("should get user details")
    void test4() {
        User tempUser = userRepository.findByEmail("bob@gmail.com").orElseGet(User::new);

        assertEquals("Bob", tempUser.getFirstName());
        assertEquals("Barclays", tempUser.getLastName());
        assertEquals("bob@gmail.com", tempUser.getEmail());
        assertTrue(tempUser.getRoles().toString().contains("ROLE_USER"));
        assertEquals("10000", tempUser.getWallet().getBalanceUSD().toString());
        assertTrue(tempUser.getWallet().getMyCoins().isEmpty());
        assertTrue(tempUser.getWallet().getTransactionHistories().isEmpty());
    }

}