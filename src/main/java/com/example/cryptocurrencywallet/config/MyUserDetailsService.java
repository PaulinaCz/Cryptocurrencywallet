package com.example.cryptocurrencywallet.config;

import com.example.cryptocurrencywallet.model.MyUserDetails;
import com.example.cryptocurrencywallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(userName)
                .map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("not found: " + userName));
    }
}
