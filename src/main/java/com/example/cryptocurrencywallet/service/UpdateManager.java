package com.example.cryptocurrencywallet.service;

import com.example.cryptocurrencywallet.model.UserRegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import static com.example.cryptocurrencywallet.CommonTools.haveError;


@Service
@RequiredArgsConstructor
public class UpdateManager {

    private final UpdateValidator validator;

    public Model updateUser(UserRegistrationDTO userForm, Model model) {

        if (haveError(model)) {
            return model;
        }
        model = validator.validateEmail(userForm, model);
        model = validator.validatePassword(userForm, model);

        return model;
    }
}
