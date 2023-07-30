package com.example.bankapplication.model.entity;

import com.example.bankapplication.model.base.EntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_wallets")
public class UserWallet implements EntityBase {

    @Serial
    private static final long serialVersionUID = 6519739605421663482L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "money", nullable = false)
    private Double money;

    @OneToOne(mappedBy = "wallet")
    private User user;

}
