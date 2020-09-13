package com.example.cryptocurrencywallet.servic;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {
    Optional<User> findByEmail(String email);
    User save(UserRegistrationDTO registrationDTO);
}
