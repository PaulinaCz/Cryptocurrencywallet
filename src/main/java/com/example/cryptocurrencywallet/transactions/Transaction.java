package com.example.cryptocurrencywallet.transactions;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id") //no data connection for transaction_id YET!

    private long id;

    private String name;

    @CreationTimestamp
    private Date tradeDateTime;

    private double amount;

    private double amountGBP;

    private boolean isClosed;

    private Date closedDateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private double price;

    private double closedPrice;

    private BuySell buySell;

    private boolean isExecuted;

    private String executionFailReason;

/*    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)

    private User user;*/

    private double profit;

    @Override
    public String toString() {
        return "Transaction{" +
                "id" + id +
                ", instrument='" + name + '\'' +
                ", tradeDateTime=" + tradeDateTime +
                ", amount=" + amount +
                ", amountGBP=" + amountGBP +
                ", isClosed="+ isClosed+
                ", closedDateTime=" + closedDateTime +
                ", price=" + price +
                ", closedPrice=" + closedPrice +
                ", buySell="+ buySell+
                ", isExecuted=" + isExecuted +
                ", executionFailReason=" + executionFailReason + '\'' +
                ", profit=" + profit +
                '}';

    }


}
