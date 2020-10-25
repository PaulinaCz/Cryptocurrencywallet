package com.example.cryptocurrencywallet.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@ToString
@Table(name = "coin")
@AllArgsConstructor
@NoArgsConstructor
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String coinName;
    @Column(name = "logo_url")
    private String logoUrl;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "coin_rank")
    private long rank;

    public Coin(String coinName, BigDecimal amount, BigDecimal price) {
        this.coinName = coinName;
        this.amount = amount;
        this.price = price;
    }
}
