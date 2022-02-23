package com.task.zentity.dao;


import com.task.zentity.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    public Optional<Customer> findCustomerByNameAndSurnameAndDateOfBirth(String name, String surname, LocalDate dateOfBirth);

}
