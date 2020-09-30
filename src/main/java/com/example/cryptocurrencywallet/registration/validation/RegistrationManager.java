package com.example.cryptocurrencywallet.registration.validation;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.registration.validation.RegistrationValidator;
import com.example.cryptocurrencywallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import static com.example.cryptocurrencywallet.CommonTools.haveError;

@Service
public class RegistrationManager {

    private RegistrationValidator validator;
    private UserService userService;


    @Autowired
    public RegistrationManager(RegistrationValidator validator, UserService userService) {
        this.validator = validator;
        this.userService = userService;
    }

    public Model registerUser(UserRegistrationDTO userForm, Model model) {
        model = validator.validateFieldsCompletion(userForm, model);
        if (haveError(model)) {
            return model;
        }

        userForm.setFirstName(userForm.getFirstName().trim().replaceAll(" +", " "));
        userForm.setLastName(userForm.getFirstName().trim().replaceAll(" +", " "));
        userForm.setPassword(userForm.getPassword().replaceAll(" ", ""));
        userForm.setEmail(userForm.getEmail().replaceAll(" ", ""));
        model = validator.validateEmail(userForm, model);
        model = validator.validatePassword(userForm, model);

        return model;
    }
}


