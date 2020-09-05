package com.example.cryptocurrencywallet.servic;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.User;

public interface UserService {
    User save(UserRegistrationDTO registrationDTO);
}
