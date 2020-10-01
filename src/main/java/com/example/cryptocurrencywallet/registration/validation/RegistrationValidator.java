package com.example.cryptocurrencywallet.registration.validation;

import com.example.cryptocurrencywallet.dto.UserRegistrationDTO;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.repository.UserRepository;
import com.example.cryptocurrencywallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

@Component
public class RegistrationValidator {


    private UserRepository userRepository;

    @Autowired
    public RegistrationValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Model validateFieldsCompletion(UserRegistrationDTO userForm, Model model) {
        if (userForm.getFirstName().isBlank()
                || userForm.getLastName().isBlank()
                || userForm.getEmail().isBlank()
                || userForm.getPassword().isBlank()
                || userForm.getRepeatPassword().isBlank()) {
            model.addAttribute("error", "All fields must be completed.");
        }
        return model;
    }

    Model validateEmail(UserRegistrationDTO userForm, Model model) {
        if (!isEmailAddressFormatCorrect(userForm.getEmail())) {
            model.addAttribute("error", "Invalid e-mail address format.");
        }
        if (isEmailAddressAvailable(userForm.getEmail())) {
            model.addAttribute("error", "The email is already in use.");
        }
        return model;
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

    private boolean arePasswordEqual(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }

    private boolean isEmailAddressFormatCorrect(String email) {
        if (doesEmailAddressContainForbiddenCharacters(email)) {
            return false;
        }
        String[] emailAddressParts = email.split("@");
        if (emailAddressParts.length != 2) {
            return false;
        }
        if (emailAddressParts[0].length() > 64 || emailAddressParts[0].length() < 2) {
            return false;
        }

        return isEmailDomainFormatCorrect(emailAddressParts[1]);
    }

    private boolean doesEmailAddressContainForbiddenCharacters(String email) {
        String[] forbiddenCharacters = {"..", "ą", "ć", "ę", "ł", "ń", "ó", "ś", "ź", "ż", "(", ")", "/", "\\", "[", "]", ",", "[", "]", ";", ":", "*"};
        return Arrays.stream(forbiddenCharacters).parallel().anyMatch(email::contains);
    }

    private boolean isEmailDomainFormatCorrect(String domain) {
        String[] domainParts = domain.split("\\.");
        if (domainParts.length != 2) {
            return false;
        }
        return domain.length() >= 3;
    }

    private boolean isEmailAddressAvailable(String userEmailAddress) {
        List<User> users = userRepository.getAllBy();

        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(userEmailAddress)) {
                return true;
            }
        }
        return false;
    }

}
