package com.example.cryptocurrencywallet.service;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    User save(UserRegistrationDTO registrationDTO);

    User getLoggedUser();

    void update(User updatedUser, UserRegistrationDTO registrationDTO);
}
