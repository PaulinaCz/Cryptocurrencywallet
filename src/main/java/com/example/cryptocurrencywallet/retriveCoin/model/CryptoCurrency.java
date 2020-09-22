package com.example.cryptocurrencywallet.retriveCoin.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoCurrency {

private String id;
private String currency;
private String symbol;
private String name;
@SerializedName("logo_url")
private String logoUrl;
@JsonFormat(shape = JsonFormat.Shape.STRING)
private BigDecimal price;
private String rank;
@SerializedName("max_supply")
private String maxSupply;
@SerializedName("circulating_supply")
private String circulatingSupply;
@SerializedName("market_cap")
private String marketCap;
@SerializedName("1d")
private _1d _1d;
}
