package com.task.zentity.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.task.zentity.domain.Account;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferDTO {

    private final Long transferID;
    private LocalDate date;
    private BigDecimal amount;
    private String debtorIBAN;
    private String creditorIBAN;
    private String message;
    @JsonIgnoreProperties("transfers")
    private Account debtor;
    @JsonIgnoreProperties("transfers")
    private Account creditor;

    public TransferDTO(Long transferID, LocalDate date, BigDecimal amount, String debtorIBAN,
                       String creditorIBAN, String message, Account debtor, Account creditor) {
        this.transferID = transferID;
        this.date = date;
        this.amount = amount;
        this.debtorIBAN = debtorIBAN;
        this.creditorIBAN = creditorIBAN;
        this.message = message;
        this.debtor = debtor;
        this.creditor = creditor;
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
        this.message = message;
    }

    public Account getDebtor() {
        return debtor;
    }

    public void setDebtor(Account debtor) {
        this.debtor = debtor;
    }

    public Account getCreditor() {
        return creditor;
    }

    public void setCreditor(Account creditor) {
        this.creditor = creditor;
    }

    public Long getTransferID() {
        return transferID;
    }
}
