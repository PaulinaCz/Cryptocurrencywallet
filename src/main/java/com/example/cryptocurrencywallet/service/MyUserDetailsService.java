package com.example.cryptocurrencywallet.service;

import com.example.cryptocurrencywallet.model.MyUserDetails;
import com.example.cryptocurrencywallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(userName)
                .map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("not found: " + userName));
    }
}
