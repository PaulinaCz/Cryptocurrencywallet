package com.example.cryptocurrencywallet.controller;

import com.example.cryptocurrencywallet.dto.WalletDTO;
import com.example.cryptocurrencywallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletDTO> getWalletById(@PathVariable("walletId") UUID walletId){
        Optional<WalletDTO> walletById = walletService.getWalletById(walletId);
        return walletById.map(walletDTO -> new ResponseEntity<>(walletDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/add")
    public ResponseEntity<WalletDTO> createWallet(@RequestBody String email){
        walletService.createWallet(email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}