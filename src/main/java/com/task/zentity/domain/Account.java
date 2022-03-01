package com.task.zentity.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.mapping.Collection;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
//    @ManyToMany
//    @JoinTable(name = "account_transfer")
//    private List<Transfer> transfers = new ArrayList<>();

    public Account(String IBAN, String currency, BigDecimal balance) {
        this.IBAN = IBAN;
        this.currency = currency;
        this.balance = balance;
        //this.transfers = Collections.EMPTY_LIST;
    }

    public Account(){

    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getAccountID() {
        return accountID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(accountID, account.accountID) && Objects.equals(IBAN, account.IBAN) && Objects.equals(currency, account.currency) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID, IBAN, currency, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", IBAN='" + IBAN + '\'' +
                ", currency='" + currency + '\'' +
                ", balance=" + balance +
                '}';
    }
}
