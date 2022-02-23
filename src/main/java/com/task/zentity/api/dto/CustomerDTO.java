package com.task.zentity.api.dto;

import com.task.zentity.domain.Account;
import com.task.zentity.domain.Sex;

import java.time.LocalDate;
import java.util.Set;

public class CustomerDTO {

    private Long accountID;
    private String name;
    private String surname;
    private Sex sex;
    private String nationality;
    private LocalDate dateOfBirth;
    private Long cardNumber;
    private LocalDate dateOfIssue;
    private LocalDate dateOfExpiry;
    private Set<Account> accounts;

    public CustomerDTO(Long accountID, String name, String surname, Sex sex, String nationality, LocalDate dateOfBirth,
                       Long cardNumber, LocalDate dateOfIssue, LocalDate dateOfExpiry, Set<Account> accounts) {
        this.accountID = accountID;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.dateOfIssue = dateOfIssue;
        this.dateOfExpiry = dateOfExpiry;
        this.accounts = accounts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public void setDateOfExpiry(LocalDate dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public Long getAccountID() {
        return accountID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Sex getSex() {
        return sex;
    }

    public String getNationality() {
        return nationality;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public LocalDate getDateOfExpiry() {
        return dateOfExpiry;
    }
}
