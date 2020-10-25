package com.example.cryptocurrencywallet.service;

import com.example.cryptocurrencywallet.model.UserRegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import static com.example.cryptocurrencywallet.CommonTools.haveError;

@Service
@RequiredArgsConstructor
public class RegistrationManager {

    private final RegistrationValidator validator;

    public Model registerUser(UserRegistrationDTO userForm, Model model) {
        model = validator.validateFieldsCompletion(userForm, model);

        if (haveError(model)) {
            return model;
        }

        userForm.setPassword(userForm.getPassword().trim().replaceAll(" ", ""));
        userForm.setEmail(userForm.getEmail().trim().replaceAll(" ", ""));
        model = validator.validateEmail(userForm, model);
        model = validator.validatePassword(userForm, model);

        return model;
    }
}


