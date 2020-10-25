package com.example.cryptocurrencywallet;

import com.example.cryptocurrencywallet.model.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.Role;
import com.example.cryptocurrencywallet.model.User;

import java.util.Set;

/**
 * Class for testing purposes to build custom
 * UserRegistrationDTO and User objects.
 */

public class UserDTOBuilder {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;

    public UserDTOBuilder() {

    }

    public UserDTOBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDTOBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDTOBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDTOBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDTOBuilder withRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
        return this;
    }


    public UserRegistrationDTO buildUserRegistrationDTO() {
        return new UserRegistrationDTO(firstName, lastName, email, password, repeatPassword);
    }

    /**
     * Returns the User object with ROLE_USER and Wallet balanceUSD value of 10 000
     */

    public User buildUserWithRoleUser() {
        return new User(firstName, lastName, email, password, Set.of(new Role("ROLE_USER")));
    }
}
