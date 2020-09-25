/*package com.example.cryptocurrencywallet.mapper;


import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserToUserRegistrationDTO {

    public Optional<UserRegistrationDTO> registrationDTO(Optional<User> optionalUser){

        if(optionalUser.isEmpty()){
            return Optional.empty();
        }else{
            User user = optionalUser.get();
            UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
            userRegistrationDTO.setFirstName(user.getFirstName());
            userRegistrationDTO.setLastName(user.getLastName());
            userRegistrationDTO.setEmail(user.getEmail());
            userRegistrationDTO.setPassword(user.getPassword());

            return Optional.of(userRegistrationDTO);
        }

    }

}*/
