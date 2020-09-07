package com.example.cryptocurrencywallet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Wallet {

    @Id
    private UUID walletId;

    private String accountNumber;
    private BigDecimal balance;

    @OneToOne(mappedBy = "wallet")
    private User user;

    public Wallet(String accountNumber, BigDecimal balance) {
        this.walletId = UUID.randomUUID();
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
