package com.example.cryptocurrencywallet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegistrationDTO {

    private String first_name;
    private String surname;
    private String email;
    private String password;

    public UserRegistrationDTO() {

    }
}
