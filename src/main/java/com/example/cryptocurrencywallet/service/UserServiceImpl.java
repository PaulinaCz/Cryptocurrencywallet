package com.example.cryptocurrencywallet.service;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.Coin;
import com.example.cryptocurrencywallet.model.MyUserDetails;
import com.example.cryptocurrencywallet.model.Role;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.repository.UserRepository;
import com.example.cryptocurrencywallet.retriveCoin.model.CryptoCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private CryptoCoinDetails details;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, CryptoCoinDetails details) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.details = details;
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
    public Map<String, BigDecimal> userCurrentCoinValue(User loggedUser) {

        List<String> coins = getLoggedUser().getWallet().getMyCoins()
                .stream()
                .map(Coin::getSymbol)
                .distinct()
                .collect(Collectors.toList());

//        String names = "";
//
//        for(int i = 0; i < coins.size(); i++){
//            switch (name){
//                case "Bitcoin":
//                    names += "," + "BTC";
//                    break;
//                case "Ethereum":
//                    names += "," + "ETH";
//                    break;
//                case "Tether":
//                    names += "," + "USDT";
//                    break;
//                case "Ripple":
//                    names += "," + "XRP";
//                    break;
//                case "Bitcoin Cash":
//                    names += "," + "BCH";
//                    break;
//                case "Binance Coin":
//                    names += "," + "BNB";
//                    break;
//                case "Polkadot":
//                    names += "," + "DOT";
//                    break;
//                case "ChainLink":
//                    names += "," + "LINK";
//                    break;
//                case "Litecoin":
//                    names += "," + "LTC";
//                    break;
//                case "Crypto.com Chain":
//                    names += "," + "CRO";
//                    break;
//
//            }
//        }
//
//        if(names.length() > 0){
//            names = names.substring(names.indexOf(",")).trim();
//        }

        String names = coins.stream()
                .collect(Collectors.joining(", "));

        List<CryptoCurrency> myCryptoCurrencies = details.getListOfCryptoCurrencies(names);

        return myCryptoCurrencies.stream()
                .collect(Collectors.toMap(CryptoCurrency::getSymbol, CryptoCurrency::getPrice, (c1, c2) -> c2));
    }
}
