package com.example.cryptocurrencywallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CryptocurrencywalletApplication {

    public static void main(String[] args) {
        SpringApplication.run (CryptocurrencywalletApplication.class, args);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder ();
        String password = "user1";
        String encodedPassword = passwordEncoder.encode (password);

        System.out.println ();
        System.out.println ("Password is         : " + password);
        System.out.println ("Encoded Password is : " + encodedPassword);
        System.out.println ("Invalid Password is : " + encodedPassword + "junk");
        System.out.println ();


        boolean isPasswordMatch = passwordEncoder.matches (password, encodedPassword + "junk");
        System.out.println ("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);
    }
}
