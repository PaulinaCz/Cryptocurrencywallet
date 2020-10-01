package com.example.cryptocurrencywallet;

import org.springframework.ui.Model;

public class CommonTools {

    public static boolean haveError(Model model) {
        return model.containsAttribute("error");
    }
}

