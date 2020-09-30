package com.example.cryptocurrencywallet.repository;

import com.example.cryptocurrencywallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String userName);

    List<User> getAllBy();
}
