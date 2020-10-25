package com.example.cryptocurrencywallet.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
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

    public TransactionHistory(Transaction transaction) {
        this.transactionDetails = transaction;
    }
}
