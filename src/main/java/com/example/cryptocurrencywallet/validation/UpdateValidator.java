package com.example.cryptocurrencywallet.validation;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

@Component
public class UpdateValidator {

    private UserRepository userRepository;

    @Autowired
    public UpdateValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    Model validatePassword(UserRegistrationDTO userForm, Model model) {
        if ((userForm.getPassword().length() < 6 || userForm.getPassword().length() > 32)) {
            model.addAttribute("error", "Password must have at least 6 and less than 32 characters long.");
        }
        if (!arePasswordEqual(userForm.getPassword(), userForm.getRepeatPassword())) {
            model.addAttribute("error", "Passwords does not match.");
        }
        return model;
    }


    private boolean arePasswordEqual(String password, String repeatPassword){
        return password.equals(repeatPassword);
    }

}
