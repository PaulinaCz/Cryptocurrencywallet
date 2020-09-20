package com.example.cryptocurrencywallet.repository;

import com.example.cryptocurrencywallet.retriveCoin.model.CryptoCurrency;
import org.springframework.data.repository.CrudRepository;

public interface CryptoCurrencyRatesRepository extends CrudRepository<CryptoCurrency, Long> {
}
