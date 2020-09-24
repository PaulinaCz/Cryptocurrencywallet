package com.example.cryptocurrencywallet.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "coin")
@AllArgsConstructor
@NoArgsConstructor
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "logo_url")
    private String logoUrl;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "coin_rank")
    private long rank;
/*    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.DETACH})
    @JoinColumn(name = "coin_id")
    private CryptoCurrency cryptoCurrency;*/
}
