package com.example.cryptocurrencywallet.validation;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import static com.example.cryptocurrencywallet.CommonTools.haveError;


@Service
public class UpdateManager {

    private UpdateValidator validator;

    @Autowired
    public UpdateManager(UpdateValidator validator) {
        this.validator = validator;
    }

    public Model updateUser(UserRegistrationDTO userForm, Model model) {

        if (haveError(model)) {
            return model;
        }
        model = validator.validateEmail(userForm, model);
        model = validator.validatePassword(userForm, model);

        return model;
    }

}
