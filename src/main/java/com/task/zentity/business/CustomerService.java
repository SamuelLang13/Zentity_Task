package com.task.zentity.business;

import com.task.zentity.dao.CustomerRepository;
import com.task.zentity.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public boolean exists(Customer customer){
        Optional<Customer> optionalCustomer = repository.
                findCustomerByNameAndSurnameAndDateOfBirth(customer.getName(), customer.getSurname(),customer.getDateOfBirth());
        return optionalCustomer.isPresent();
    }

    public Customer create(Customer customer) throws Exception {
        if(customer.getName().isEmpty() || customer.getSurname().isEmpty() || customer.getNationality().isEmpty()){
            throw new Exception("The name, surname or nationality is empty");
        }
        if(!exists(customer)){
            throw new Exception("The customer is already created!");
        }
        if(customer.getDateOfExpiry().isBefore(customer.getDateOfIssue())){
            throw new Exception("The expiry date cannot be before date of issue");
        }
        return repository.save(customer);
    }

    public Collection<Customer> readAll(){
        return repository.findAll();
    }
}
