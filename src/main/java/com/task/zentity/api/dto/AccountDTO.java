package com.task.zentity.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.task.zentity.domain.Customer;
import com.task.zentity.domain.Transfer;
import java.math.BigDecimal;
import java.util.List;

public class AccountDTO {

    private Long accountID;
    private String IBAN;
    private String currency;
    private BigDecimal balance;
    @JsonIgnoreProperties("accounts")
    private Customer customer;
    @JsonIgnoreProperties("accounts")
    private List<Transfer> transfers;

    public AccountDTO(Long accountID, String IBAN, String currency, BigDecimal balance, Customer customer) {
        this.accountID = accountID;
        this.IBAN = IBAN;
        this.currency = currency;
        this.balance = balance;
        this.customer = customer;
    }

    public AccountDTO(Long accountID, String IBAN, String currency, BigDecimal balance, Customer customer, List<Transfer> transfers) {
        this.accountID = accountID;
        this.IBAN = IBAN;
        this.currency = currency;
        this.balance = balance;
        this.customer = customer;
        this.transfers = transfers;
    }

    public AccountDTO(){

    }

    public Long getAccountID() {
        return accountID;
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

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }
}
