package com.example.cryptocurrencywallet.mapper;

import com.example.cryptocurrencywallet.dto.WalletDTO;
import com.example.cryptocurrencywallet.model.Wallet;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletToWalletDTOMapper {

    public Optional<WalletDTO> walletDTO(Optional<Wallet> optionalWallet){

        if(optionalWallet.isEmpty()){
            return Optional.empty();
        }
        else{
            Wallet wallet = optionalWallet.get();
            WalletDTO walletDTO = new WalletDTO();
            walletDTO.balance = wallet.getBalance();
            walletDTO.accountNumber = wallet.getAccountNumber();

            return Optional.of(walletDTO);
        }

    }

}
