package com.example.cryptocurrencywallet.repository;

import com.example.cryptocurrencywallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
