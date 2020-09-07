package com.example.cryptocurrencywallet.servic;

import com.example.cryptocurrencywallet.dto.WalletDTO;
import com.example.cryptocurrencywallet.mapper.WalletToWalletDTOMapper;
import com.example.cryptocurrencywallet.model.Wallet;
import com.example.cryptocurrencywallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public void createWallet(WalletDTO walletDTO){
        Wallet wallet = new Wallet(walletDTO.accountNumber, BigDecimal.valueOf(5000));
        walletRepository.save(wallet);
    }

}
