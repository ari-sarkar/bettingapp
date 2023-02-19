package com.example.bet.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class LoginModel {
    @GenericGenerator(
            name = "otpSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters={
            @Parameter(name = "sequence_name",value = "otpSequence"),
                    @Parameter(name = "initial_value",value = "1"),
                    @Parameter(name = "increment_value",value = "1"),
            })
    @GeneratedValue(generator = "otpSequenceGenerator")
    @Id
    private Long id;

    private String code;

    private Boolean isActive=false;
}
