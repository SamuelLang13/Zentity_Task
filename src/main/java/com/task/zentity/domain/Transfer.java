package com.task.zentity.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private String Message;
    @ManyToMany
    @JoinTable(name = "account")
    public List<Account> accounts = new ArrayList<>();

    public Transfer(LocalDate date, BigDecimal amount, String debtorIBAN, String creditorIBAN,
                    String message) {
        this.date = date;
        this.amount = amount;
        this.debtorIBAN = debtorIBAN;
        this.creditorIBAN = creditorIBAN;
        Message = message;
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
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
