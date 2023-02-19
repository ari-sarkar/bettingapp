package com.example.bet.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Data
@Entity
@Table(name = "my_bids_v2")
public class MyBidsModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private String user;

    private String type;

    private Date date;

    private String bajiNo;

    private int number;

    private int amount;

    private String result;

    private String gameId;
}
