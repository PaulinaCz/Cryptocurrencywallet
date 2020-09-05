package com.example.cryptocurrencywallet.servic;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.Wallet;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

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
                registrationDTO.getPassword (), Arrays.asList (new Wallet ("USERS_WALLETS")));

        return userRepository.save (user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail (username);
        if (user == null) {
            throw new UsernameNotFoundException ("Invalid username or password");
        }

        return new org.springframework.security.core.userdetails.User (user.getEmail (), user.getPassword (), null);
    }

    private Collection<? extends GrantedAuthority> mapWalletsToAuthorities(Collection<Wallet> wallets) {
        return wallets.stream ().map (wallet -> new SimpleGrantedAuthority (wallet.getName ())).collect (Collectors.toList ());
    }
}
