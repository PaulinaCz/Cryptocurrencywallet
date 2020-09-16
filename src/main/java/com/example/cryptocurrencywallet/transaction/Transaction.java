package com.example.cryptocurrencywallet.transaction;

import com.example.cryptocurrencywallet.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")

    private long id;

    private String instrument;

    @CreationTimestamp
    private Date tradeDateTime;

    private double amount;

    private double amountGPB;

    private boolean isClosed;

    private Date closedDateTime;

    private double price;

    private double closedPrice;

    private BuySell buySell;

    private boolean isExecuted;

    private String executionFailReason;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)

    private User user;

    private double profit;

    @Override
    public String toString(){
        return "Transaction{" +
                "id"+ id+
                ", instrument='"+instrument+'\''+
                ",tradeDateTime="+tradeDateTime+

    }


}
