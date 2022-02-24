package com.task.zentity.api.dto;

import com.task.zentity.domain.Customer;

import java.math.BigDecimal;

public class AccountDTO {

    private Long accountID;
    private String IBAN;
    private String currency;
    private BigDecimal balance;
    private Customer customer;

    public AccountDTO(Long accountID, String IBAN, String currency, BigDecimal balance, Customer customer) {
        this.accountID = accountID;
        this.IBAN = IBAN;
        this.currency = currency;
        this.balance = balance;
        this.customer = customer;
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
}
