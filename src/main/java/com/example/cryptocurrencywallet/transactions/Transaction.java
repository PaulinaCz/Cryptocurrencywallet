package com.example.cryptocurrencywallet.transactions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal amount;

    private BigDecimal amountUSD;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;

    @Enumerated(value = EnumType.STRING)
    private BuySell buySell;

    private boolean isExecuted;

/*    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)

    private User user;*/

    public BigDecimal getTotalPriceForTransaction(){
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Wrong amount: " + amount);
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Wrong price: " + price);
        }
        return price.multiply(amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id" + id +
                ", name='" + name + '\'' +
                ", tradeDateTime=" + tradeDateTime +
                ", amount=" + amount +
                ", amountGBP=" + amountUSD +
                ", price=" + price +
                ", buySell="+ buySell+
                ", isExecuted=" + isExecuted +
                '}';
    }
}
