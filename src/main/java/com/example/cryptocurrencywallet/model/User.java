package com.example.cryptocurrencywallet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint (columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_wallets",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "wallet_id", referencedColumnName = "id"))
    private Collection<Wallet> wallets;

    public User(String firstName, String surname, String email, String password, Collection<Wallet> wallets) {
        super();
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.wallets = wallets;
    }


}
