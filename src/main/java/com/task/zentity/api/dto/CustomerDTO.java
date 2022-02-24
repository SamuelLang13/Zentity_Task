package com.task.zentity.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.task.zentity.domain.Account;
import com.task.zentity.domain.Sex;

import java.time.LocalDate;
import java.util.Set;

public class CustomerDTO {

    private Long customerID;
    private String name;
    private String surname;
    private Sex sex;
    private String nationality;
    //@JsonView()
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirth;
    private Long cardNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateOfIssue;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateOfExpiry;
    private Set<Account> accounts;

    public CustomerDTO(Long accountID, String name, String surname, Sex sex, String nationality, LocalDate dateOfBirth,
                       Long cardNumber, LocalDate dateOfIssue, LocalDate dateOfExpiry, Set<Account> accounts) {
        this.customerID = accountID;
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
        return customerID;
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

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerID=" + customerID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex=" + sex +
                ", nationality='" + nationality + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", cardNumber=" + cardNumber +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfExpiry=" + dateOfExpiry +
                ", accounts=" + accounts +
                '}';
    }
}
