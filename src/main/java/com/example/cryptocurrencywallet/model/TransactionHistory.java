package com.example.cryptocurrencywallet.model;


import com.example.cryptocurrencywallet.transactions.BuySell;
import com.example.cryptocurrencywallet.transactions.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "transaction_history")
@Getter
@Setter
@NoArgsConstructor
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @CreationTimestamp
    @Column(name = "date_of_transaction")
    private Date dateOfTransaction;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "transactionHistory_id")
    private Transaction transactionDetails;
//    @Column(name = "amount")
//    private BigDecimal amount;
//    @Column(name = "buy_or_sale")
//    @Enumerated(value = EnumType.STRING)
//    private BuySell buySell;

    public TransactionHistory(Transaction transaction) {
        this.transactionDetails = transaction;
    }
}
