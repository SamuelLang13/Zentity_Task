package com.task.zentity.business;

import com.task.zentity.api.dto.CustomerDTO;
import com.task.zentity.api.exception.EntityStateException;
import com.task.zentity.dao.CustomerRepository;
import com.task.zentity.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public Customer create(Customer customer) throws EntityStateException {
        if (exists(customer))
            throw new EntityStateException(customer);
        return repository.save(customer);
    }

    public Collection<Customer> readAll(){
        return repository.findAll();
    }
}
