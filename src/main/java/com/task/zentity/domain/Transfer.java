package com.task.zentity.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transferID;
    private LocalDate date;
    private BigDecimal amount;
    private String debtorIBAN;
    private String creditorIBAN;
    private String message;
//    @ManyToMany
//    @JoinTable(name = "account",
//                joinColumns = @JoinColumn(name = "accountid"),
//                inverseJoinColumns = @JoinColumn(name = "transferid"))
//    @JsonIgnoreProperties("transfers")
//    public List<Account> accounts = new ArrayList<>();

    public Transfer(LocalDate date, BigDecimal amount, String debtorIBAN, String creditorIBAN,
                    String message) {
        this.date = date;
        this.amount = amount;
        this.debtorIBAN = debtorIBAN;
        this.creditorIBAN = creditorIBAN;
        this.message = message;
        //this.accounts = Collections.EMPTY_LIST;
    }

    public Transfer(){

    }

    public Long getTransferID() {
        return transferID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDebtorIBAN() {
        return debtorIBAN;
    }

    public void setDebtorIBAN(String debtorIBAN) {
        this.debtorIBAN = debtorIBAN;
    }

    public String getCreditorIBAN() {
        return creditorIBAN;
    }

    public void setCreditorIBAN(String creditorIBAN) {
        this.creditorIBAN = creditorIBAN;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        message = message;
    }

//    public List<Account> getAccounts() {
//        return accounts;
//    }
//
//    public void setAccounts(List<Account> accounts) {
//        this.accounts = accounts;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Transfer)) return false;
//        Transfer transfer = (Transfer) o;
//        return Objects.equals(date, transfer.date) && Objects.equals(amount, transfer.amount) && Objects.equals(debtorIBAN, transfer.debtorIBAN) && Objects.equals(creditorIBAN, transfer.creditorIBAN) && Objects.equals(message, transfer.message) && Objects.equals(accounts, transfer.accounts);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(date, amount, debtorIBAN, creditorIBAN, message, accounts);
//    }
}
