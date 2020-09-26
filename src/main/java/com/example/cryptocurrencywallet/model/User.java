package com.example.cryptocurrencywallet.model;

import com.example.cryptocurrencywallet.validator.UniqueEmail;
import com.example.cryptocurrencywallet.validator.ValidationGroupUniqueEmail;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name")
    private String username;


    @Size(min = 1, message = "First name can not be empty")
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 1, message = "Last name can not be empty")
    @Column(name = "surname")
    private String lastName;

    @Pattern(regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "type correct email")
    @UniqueEmail(groups = ValidationGroupUniqueEmail.class)
    private String email;

    @Size(min = 6,  message = "password must be at least 6 characters")
    private String password;
    private boolean active;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Wallet wallet;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    private Set<Role> roles;

    /*      MAPPING FOR UserRegistrationDto     */
    public User(String firstName, String lastName, String email, String password, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.wallet = new Wallet(new BigDecimal(10000), this);
//        System.out.println(wallet.getBalanceUSD()+" <<<---- BALANCE");
//        System.out.println(wallet.getWalletId()+" <<<---- WALLET ID");
    }

    /*      MAPPING FOR MyUserDetails     */
    public User(User user) {
        this.active = user.isActive();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.username = user.getUsername();
        this.id = user.getId();
        this.password = user.getPassword();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", wallet=" + wallet +
                ", roles=" + roles +
                '}';
    }
}


