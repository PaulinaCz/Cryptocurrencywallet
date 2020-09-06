package com.example.cryptocurrencywallet.servic;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.Role;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(UserRegistrationDTO registrationDTO) {
        User user = new User (registrationDTO.getFirst_name (),
                registrationDTO.getSurname (),
                registrationDTO.getEmail (),
                passwordEncoder.encode (registrationDTO.getPassword ()), Arrays.asList (new Role ("ROLE_USER")));

        return userRepository.save (user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail (username);
        if (user == null) {
            throw new UsernameNotFoundException ("Invalid username or password");
        }

        return new org.springframework.security.core.userdetails.User (user.getEmail (), user.getPassword (), mapRolesToAuthorities (user.getRoles ()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream ().map (role -> new SimpleGrantedAuthority (role.getName ())).collect (Collectors.toList ());
    }
}
