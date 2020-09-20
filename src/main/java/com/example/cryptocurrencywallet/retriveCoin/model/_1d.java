package com.example.cryptocurrencywallet.retriveCoin.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class _1d {
    @SerializedName("volume")
    private String volume;
    @SerializedName("price_change")
    private String priceChange;
    @SerializedName("price_change_pct")
    private String priceChangePct;
    @SerializedName("volume_change")
    private String volumeChange;
    @SerializedName("volume_change_pct")
    private String volumeChangePct;
    @SerializedName("market_cap_change")
    private String marketCapChange;
    @SerializedName("market_cap_change_pct")
    private String marketCapChangePct;
}
