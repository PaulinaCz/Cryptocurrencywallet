package com.example.cryptocurrencywallet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;

}
