package com.example.cryptocurrencywallet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

/*
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Wallet implements Serializable {

    @Id
    @Column(name = "wallet_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID walletId;

    @Column(name = "account_number")
    private String accountNumber;
    private BigDecimal balance;

    @OneToOne(mappedBy = "wallet", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "wallet_currencies",
            joinColumns = @JoinColumn(name = "wallet_id", referencedColumnName = "wallet_id"),
            inverseJoinColumns = @JoinColumn(name = "currency", referencedColumnName = "currency_id"))
    private Collection<CryptoCurrency> myCurrencies;

    public Wallet(String accountNumber, BigDecimal balance, Collection<CryptoCurrency> myCurrencies) {
        this.walletId = UUID.randomUUID();
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.myCurrencies = myCurrencies;
    }
}
*/
