package com.example.cryptocurrencywallet.servic;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.Role;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDTO registrationDTO) {
        User user = new User (registrationDTO.getFirst_name (),
                registrationDTO.getSurname (),
                registrationDTO.getEmail (),
                registrationDTO.getPassword (), Arrays.asList (new Role ("ROLE_USER")));

        return userRepository.save (user);
    }
}
