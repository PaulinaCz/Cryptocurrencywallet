package com.example.cryptocurrencywallet.service;

import com.example.cryptocurrencywallet.dto.WalletDTO;
import com.example.cryptocurrencywallet.mapper.WalletToWalletDTOMapper;
import com.example.cryptocurrencywallet.model.Wallet;
import com.example.cryptocurrencywallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class WalletService {

    private WalletRepository walletRepository;
    private WalletToWalletDTOMapper mapper;

    @Autowired
    public WalletService(WalletRepository walletRepository, WalletToWalletDTOMapper mapper) {
        this.walletRepository = walletRepository;
        this.mapper = mapper;
    }

    public Optional<WalletDTO> getWalletById(UUID walletId){
        Optional<Wallet> wallet = walletRepository.findById(walletId);
        return mapper.walletDTO(wallet);

    }

    public Wallet createWallet(String email) {
        Wallet wallet = new Wallet();
        wallet.setAccountNumber(generateAccountNumber(email));
        wallet.setBalance(defaultBalance());
        walletRepository.save(wallet);

        return wallet;
    }

    private BigDecimal defaultBalance() {
        return BigDecimal.valueOf(500);
    }


    //TODO check if accountNumber is unique
    private String generateAccountNumber(String email) {

        return StringUtil.applySha256(
                "walletcurrencycrypto" +
                        email +
                        LocalDateTime.now().toString()
        );
    }

}
