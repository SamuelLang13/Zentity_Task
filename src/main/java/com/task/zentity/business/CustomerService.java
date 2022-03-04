package com.task.zentity.business;

import com.task.zentity.api.dto.CustomerDTO;
import com.task.zentity.api.exception.EntityStateException;
import com.task.zentity.dao.CustomerRepository;
import com.task.zentity.domain.Account;
import com.task.zentity.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    /**
     * Method for checking if the customer exists
     * @param customer
     * @return true if exists, false if not
     */
    public boolean exists(Customer customer){
        Optional<Customer> optionalCustomer = repository.
                findCustomerByNameAndSurnameAndDateOfBirth(customer.getName(), customer.getSurname(),customer.getDateOfBirth());
        return optionalCustomer.isPresent();
    }

    /**
     * Method for getting customer with certain ID
     * @param customerID
     * @return Customer
     */
    public Customer getCustomerById(Long customerID){
        Optional<Customer> customer = repository.findById(customerID);
        if(customer.isEmpty()){
            throw new EntityNotFoundException("Customer with this ID does not exist");
        }
        return customer.get();
    }

    /**
     * Method for validating customer
     * @param customer
     * @return false if one of the customer data is invalid, true if everything is valid
     */
    public  boolean customerValidation(Customer customer){
        return !customer.getName().trim().isEmpty() && !customer.getSurname().trim().isEmpty() && !customer.getNationality().trim().isEmpty();
    }

    /**
     * Method to create and save customer into repository,
     * we have to check if the customer data are correct
     * if yes then we save it into repository
     * @param customer
     * @return Customer
     * @throws EntityStateException
     */
    @Transactional
    public Customer create(Customer customer) throws EntityStateException {

        if(!customerValidation(customer)){
            throw new EntityStateException(customer);
        }
        //Checking if the Expiry date is after the date of issue, also date of birth muste be before the issue date
        if(customer.getDateOfIssue().isAfter(customer.getDateOfExpiry()) ||
                customer.getDateOfBirth().isAfter(customer.getDateOfIssue())){
            throw new EntityStateException(customer);
        }
        //Checking if the customer with the same name, surname and date of birth already exists
        if (exists(customer))
            throw new EntityStateException(customer);
        return repository.save(customer);
    }

    /**
     * Method for getting every customer
     * @return Collection of customers
     */
    public Collection<Customer> readAll(){
        return repository.findAll();
    }

    /**
     * Firstly we have to check if the requested customer with this ID exists
     * if no then we throw an exception, if yes that we use deleteById method
     * @param customerID
     */
    public void deleteByID(Long customerID) {
        if(!repository.existsById(customerID)){
            throw new EntityNotFoundException("Customer with this ID does not exist!");
        }
        for (Account account : repository.getById(customerID).getAccounts()) {
            account.setCustomer(null);
        }
        repository.deleteById(customerID);
    }

    /**
     * Method for updating customer
     * @param customerID
     * @param customer
     * @return updated customer
     */
    @Transactional
    public Customer update(Long customerID, Customer customer) {
        if(!repository.existsById(customerID)){
            throw new EntityNotFoundException("Customer with this ID does not exist!");
        }
        if(!customerValidation(customer)){
            throw new EntityStateException(customer);
        }
        Customer updatedCustomer = repository.getById(customerID);
        updatedCustomer.setName(customer.getName());
        updatedCustomer.setSurname(customer.getSurname());
        updatedCustomer.setSex(customer.getSex());
        updatedCustomer.setNationality(customer.getNationality());
        updatedCustomer.setDateOfBirth(customer.getDateOfBirth());
        updatedCustomer.setCardNumber(customer.getCardNumber());
        updatedCustomer.setDateOfIssue(customer.getDateOfIssue());
        updatedCustomer.setDateOfExpiry(customer.getDateOfExpiry());
        return updatedCustomer;
    }


    /**
     * Method for updating customer
     * @param customerID
     * @param account
     */
    @Transactional
    public void addAccount(Long customerID, Account account) {
        Optional<Customer> customer  = repository.findById(customerID);
        if(customer.isEmpty()){
            throw new EntityNotFoundException();
        }
        customer.get().setAccounts(account);
    }

    /**
     * Method for deleting account
     * @param customerID
     * @param accountById
     */
    @Transactional
    public void deleteAccount(Long customerID, Account accountById) {
        if(!repository.existsById(customerID)){
            throw new EntityNotFoundException("Customer with this ID does not exist!");
        }
        Customer customer = repository.getById(customerID);
        Set<Account> accounts = customer.getAccounts();
        accounts.remove(accountById);
    }
}
