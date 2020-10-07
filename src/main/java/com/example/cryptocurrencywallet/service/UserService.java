package com.example.cryptocurrencywallet.service;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service
public interface UserService {
    User save(UserRegistrationDTO registrationDTO);

    User getLoggedUser();

    void update(User updatedUser, UserRegistrationDTO registrationDTO);

    Map<String, BigDecimal> userCurrentCoinValue(User loggedUser) throws IOException, InterruptedException;
}
