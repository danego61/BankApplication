package com.example.bankapplication.model.entity;

import com.example.bankapplication.model.base.EntityBase;
import com.example.bankapplication.model.enums.UserLoginStatus;
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
@Entity(name = "user_logins")
public class UserLogin implements EntityBase {

    @Serial
    private static final long serialVersionUID = -2960989031241925111L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "ip", nullable = false, length = 50)
    private String ip;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private UserLoginStatus status;

}
