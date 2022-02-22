package com.task.zentity.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountID;
    private String name;
    private String surname;
    private Sex sex;
    private String nationality;
    private LocalDate dateOfBirth;
    private Long cardNumber;
    private LocalDate dateOfIssue;
    private LocalDate dateOfExpiry;

    public Account(String name, String surname, Sex sex, String nationality, LocalDate dateOfBirth,
                   Long cardNumber, LocalDate dateOfIssue, LocalDate dateOfExpiry) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.dateOfIssue = dateOfIssue;
        this.dateOfExpiry = dateOfExpiry;
    }

    public Account() {

    }
}
