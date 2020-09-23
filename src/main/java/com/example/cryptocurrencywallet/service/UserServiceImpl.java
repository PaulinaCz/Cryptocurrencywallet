package com.example.cryptocurrencywallet.service;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.dto.WalletDTO;
//import com.example.cryptocurrencywallet.mapper.UserToUserRegistrationDTO;
import com.example.cryptocurrencywallet.model.MyUserDetails;
import com.example.cryptocurrencywallet.model.Role;
import com.example.cryptocurrencywallet.model.User;
//import com.example.cryptocurrencywallet.model.Wallet;
import com.example.cryptocurrencywallet.model.Wallet;
import com.example.cryptocurrencywallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
                , new Wallet(new BigDecimal(10000))
        );
        return userRepository.save(user);
    }

    @Override
    public User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User doesn't exist:  " + userEmail));
    }
}
