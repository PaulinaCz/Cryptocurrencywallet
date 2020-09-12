package com.example.cryptocurrencywallet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CryptoCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id")
    private Long currencyId;
    private String name;

    public CryptoCurrency(Long currencyId, String name) {
        this.currencyId = currencyId;
        this.name = name;
    }

    @ManyToMany(mappedBy = "myCurrencies")
    private Collection<Wallet> wallets;

}