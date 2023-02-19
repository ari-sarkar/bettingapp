package com.example.bet.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private String name;

    private String phoneNo;

    private String walletBalance;

    @Column(nullable = false,columnDefinition = "0")
    private Integer totalRechargeAmount;

    private String password;
}
