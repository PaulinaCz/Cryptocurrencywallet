package com.example.cryptocurrencywallet.service;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.Coin;
import com.example.cryptocurrencywallet.model.MyUserDetails;
import com.example.cryptocurrencywallet.model.Role;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.repository.UserRepository;
import com.example.cryptocurrencywallet.retriveCoin.AppHttpRequests;
import com.example.cryptocurrencywallet.retriveCoin.model.CryptoCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User save(UserRegistrationDTO userRegistrationDTO) {
        User user = new User(userRegistrationDTO.getFirstName(),
                userRegistrationDTO.getLastName(),
                userRegistrationDTO.getEmail(),
                passwordEncoder.encode(userRegistrationDTO.getPassword()),
//                passwordEncoder.encode(userRegistrationDTO.getRepeatPassword()),
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
    public void update(User user, UserRegistrationDTO registrationDTO) {

        User updatedUser = userRepository.findById(user.getId()).get();

        updatedUser.setFirstName(registrationDTO.getFirstName());
        updatedUser.setLastName(registrationDTO.getLastName());
        updatedUser.setEmail(registrationDTO.getEmail());
        updatedUser.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        userRepository.save(updatedUser);
    }

    @Override
    public Map<String, BigDecimal> userCurrentCoinValue(User loggedUser) throws IOException, InterruptedException {
        List<String> mappedSymbols = loggedUser.getWallet().getMyCoins()
                .stream()
                .map(Coin::getSymbol)
                .collect(Collectors.toList());

        String names = String.join(", ", mappedSymbols);

        List<CryptoCurrency> myCryptoCurrencies = AppHttpRequests.requestCoin(names);

        return myCryptoCurrencies.stream()
                .collect(Collectors.toMap(CryptoCurrency::getSymbol, CryptoCurrency::getPrice, (c1, c2) -> c2));
    }
}
