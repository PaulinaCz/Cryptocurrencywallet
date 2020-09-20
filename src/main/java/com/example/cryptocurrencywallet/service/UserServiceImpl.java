package com.example.cryptocurrencywallet.service;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.dto.WalletDTO;
//import com.example.cryptocurrencywallet.mapper.UserToUserRegistrationDTO;
import com.example.cryptocurrencywallet.model.MyUserDetails;
import com.example.cryptocurrencywallet.model.Role;
import com.example.cryptocurrencywallet.model.User;
//import com.example.cryptocurrencywallet.model.Wallet;
import com.example.cryptocurrencywallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
/*    private UserToUserRegistrationDTO mapper;
    private PasswordEncoder passwordEncoder;
    private WalletService walletService;*/

/*
    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserToUserRegistrationDTO mapper,
                           PasswordEncoder passwordEncoder,
                           WalletService walletService) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.walletService = walletService;
    }
*/

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("not found: " + username));
    }
}

/*    public Optional<UserRegistrationDTO> getByEmail(String email) throws UsernameNotFoundException {
        Optional<User> userDetails = userRepository.findByEmail(email);

        return mapper.registrationDTO(userDetails);

    }

    public Optional<WalletDTO> getWalletByUserEmail(String email) throws UsernameNotFoundException {
        Optional<User> userDetails = userRepository.findByEmail(email);

        if (userDetails.isEmpty()){
            return Optional.empty();
        }
        else{
            Wallet wallet = userDetails.get().getWallet();
            WalletDTO walletDTO = new WalletDTO();
            walletDTO.accountNumber = wallet.getAccountNumber();
            walletDTO.balance = wallet.getBalance();

            return Optional.of(walletDTO);
        }

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream ().map (role -> new SimpleGrantedAuthority (role.getName ())).collect (Collectors.toList ());
    }
}*/
