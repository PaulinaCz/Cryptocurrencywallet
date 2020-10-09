package com.example.cryptocurrencywallet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "wallet")
public class Wallet {

    /*@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID walletId;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int walletId;

    @Column(name = "balance")
    private BigDecimal balanceUSD;

    @OneToOne(mappedBy = "wallet", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private User user;


    /*
    *  TODO: Make it Map<String,Coin> instead of List
    * */

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wallet_id")
    private List<Coin> myCoins;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wallet_id")
    private List<TransactionHistory> transactionHistories;

    public Wallet(BigDecimal balanceUSD, User user) {
        this.balanceUSD = balanceUSD;
        this.myCoins = new ArrayList<>();
        this.transactionHistories = new ArrayList<>();
        this.user = user;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "walletId=" + walletId +
                ", balanceUSD=" + balanceUSD +
                '}';
    }
}
