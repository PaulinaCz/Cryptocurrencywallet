package com.example.cryptocurrencywallet.model;

import com.example.cryptocurrencywallet.retriveCoin.model.CryptoCurrency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "wallet")
public class Wallet{

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID walletId;

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToOne(mappedBy = "wallet", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private User user;

    /*
    *
    * */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wallet_id")
    private List<Coin> myCoins;

 /*   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wallet_id")
    private List<TransactionHistory> transactionHistories;
*/
    public Wallet(BigDecimal balance) {
//        this.walletId = UUID.randomUUID();
        this.balance = balance;
//        this.myCoins = myCoins;
    }
}
