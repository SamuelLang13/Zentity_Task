package com.task.zentity.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerID;
    private String name;
    private String surname;
    private Sex sex;
    private String nationality;
    private LocalDate dateOfBirth;
    private Long cardNumber;
    private LocalDate dateOfIssue;
    private LocalDate dateOfExpiry;
    //One customer might have more accounts, so relation 1:M
    @OneToMany
    @JoinColumn
    private Set<Account> accounts=new HashSet<>();

    public Customer(String name, String surname, Sex sex, String nationality,
                    LocalDate dateOfBirth, Long cardNumber, LocalDate dateOfIssue,
                    LocalDate dateOfExpiry) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.dateOfIssue = dateOfIssue;
        this.dateOfExpiry = dateOfExpiry;
        this.accounts= Collections.EMPTY_SET;
    }

    public Customer() {
    }

    public Long getAccountID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public LocalDate getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(LocalDate dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Account account) {
        this.accounts.add(account);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(surname, customer.surname) && sex == customer.sex && Objects.equals(nationality, customer.nationality) && Objects.equals(dateOfBirth, customer.dateOfBirth) && Objects.equals(cardNumber, customer.cardNumber) && Objects.equals(dateOfIssue, customer.dateOfIssue) && Objects.equals(dateOfExpiry, customer.dateOfExpiry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, sex, nationality, dateOfBirth, cardNumber, dateOfIssue, dateOfExpiry);
    }

    @Override
    public String toString() {
        return "Customer{" +
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
