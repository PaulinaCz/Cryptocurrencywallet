package com.example.cryptocurrencywallet.validation;

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
public class UpdateValidator {

    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public UpdateValidator(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
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

    Model validateEmail(UserRegistrationDTO userForm, Model model) {
        if (!isFormatCorrect(userForm.getEmail())) {
            model.addAttribute("error", "Invalid e-mail address format.");
        }
        if (!isEmailAvailable(userForm.getEmail())) {
            model.addAttribute("error", "The email is already in use.");
        }
        return model;
    }

    private boolean isFormatCorrect(String email) {
        if (doesContainForbiddenCharacters(email)) {
            return false;
        }
        String[] emailAddressParts = email.split("@");
        if (emailAddressParts.length != 2) {
            return false;
        }
        if (emailAddressParts[0].length() > 64 || emailAddressParts[0].length() < 2) {
            return false;
        }

        return isDomainCorrect(emailAddressParts[1]);
    }

    private boolean doesContainForbiddenCharacters(String email) {
        String[] forbiddenCharacters = {"..", "ą", "ć", "ę", "ł", "ń", "ó", "ś", "ź", "ż", "(", ")", "/", "\\", "[", "]", ",", "[", "]", ";", ":", "*"};
        return Arrays.stream(forbiddenCharacters).parallel().anyMatch(email::contains);
    }

    private boolean isDomainCorrect(String domain) {
        String[] domainParts = domain.split("\\.");
        if (domainParts.length != 2) {
            return false;
        }
        return domain.length() >= 3;
    }

    private boolean isEmailAvailable(String userEmailAddress) {
        User updatedUser = userService.getLoggedUser();
        List<User> users = userRepository.getAllBy();

        for (User user : users) {
            if(user.getEmail().equalsIgnoreCase(userEmailAddress) && user.getEmail().equalsIgnoreCase(updatedUser.getEmail())){
                return true;
            }else if (user.getEmail().equalsIgnoreCase(userEmailAddress)) {
                return false;
            }
        }
        return true;
    }
}
