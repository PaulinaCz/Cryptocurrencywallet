package com.example.cryptocurrencywallet.repository;

import com.example.cryptocurrencywallet.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    //TODO SPRAWDZIC
    public List<Transaction> findAllByOrderByIdDesc();

    @Query(value = "SELECT * FROM TRANSACTION WHERE IS_CLOSED = FALSE AND USER_USER_ID  = ?1 ORDER BY TRANSACTION_ID DESC", nativeQuery = true)
    public List<Transaction> findAllNonClosed(Long user_id);

    @Query(value = "SELECT * FROM TRANSACTION WHERE IS_CLOSED = TRUE AND USER_USER_ID  = ?1 ORDER BY TRANSACTION_ID DESC", nativeQuery = true)
    public List<Transaction> findAllClosed(Long user_id);

    @Query(value = "SELECT * FROM TRANSACTION WHERE IS_CLOSED = FALSE AND USER_USER_ID  = ?1 ORDER BY TRANSACTION_ID DESC LIMIT 5", nativeQuery = true)
    public List<Transaction> findFirst5NonClosedDesc(Long user_id);

    @Query(value = "SELECT * FROM TRANSACTION WHERE IS_CLOSED = TRUE AND IS_EXECUTED = TRUE AND USER_USER_ID  = ?1 ORDER BY TRANSACTION_ID DESC LIMIT 5", nativeQuery = true)
    public List<Transaction> findFirst5ClosedDesc(Long user_id);
}
