package com.example.cryptocurrencywallet.service;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.MyUserDetails;
import com.example.cryptocurrencywallet.model.Role;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.repository.UserRepository;
import com.example.cryptocurrencywallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User save(UserRegistrationDTO userRegistrationDTO) {
        User user = new User(userRegistrationDTO.getFirstName(),
                userRegistrationDTO.getLastName(),
                userRegistrationDTO.getEmail(),
                passwordEncoder.encode(userRegistrationDTO.getPassword()),
                Set.of(new Role("ROLE_USER"))
        );
        return userRepository.save(user);
    }

    @Override
    public User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) auth.getPrincipal();
        Optional<User> userOptional = userRepository.findById(myUserDetails.getId());
        return userOptional.orElseGet(User::new);
//    }        return userRepository.findByEmail(userEmail)
//                .orElseThrow(() -> new IllegalArgumentException("User doesn't exist:  " + userEmail));
    }

    @Override
    public User updateUser(UserRegistrationDTO registrationDTO) {
        User updatedUser = getLoggedUser();
        updatedUser.setFirstName(registrationDTO.getFirstName());
        updatedUser.setLastName(registrationDTO.getLastName());
        updatedUser.setEmail(registrationDTO.getEmail());
        updatedUser.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

      return userRepository.save(updatedUser);
    }

}
