package com.task.zentity.domain;


import org.hibernate.mapping.Collection;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;

@Entity
@Table
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountID;
    private String IBAN;
    private String currency;
    private BigDecimal balance;
    //Accounts can belong to one customer, so relation M:1
    @ManyToOne
    @JoinColumn
    private Customer customer;

    public Account(String IBAN, String currency, BigDecimal balance) {
        this.IBAN = IBAN;
        this.currency = currency;
        this.balance = balance;
    }

    public Account() {

    }
}
