package com.task.zentity.business;

import com.task.zentity.api.exception.EntityStateException;
import com.task.zentity.dao.AccountRepository;
import com.task.zentity.dao.CustomerRepository;
import com.task.zentity.domain.Account;
import com.task.zentity.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService extends ValidationService {

    private final AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository, CustomerRepository customerRepository,
                          CustomerService customerService) {
        this.repository = repository;
    }

    /**
     * Auxiliary method for getting account with requested IBAN
     * @param IBAN
     * @return account if the account with the IBAN exists
     */
    public Account getAccountByIBAN(String IBAN){
        Optional<Account> account = Optional.ofNullable(repository.findByIBAN(IBAN));
        if(account.isEmpty()){
            throw new EntityNotFoundException();
        }
        return account.get();
    }

    /**
     * Auxiliary method for getting account with requested ID
     * @param accountID
     * @return account if the account with the ID exists
     */
    public Account getAccountById(Long accountID) {
        Optional<Account> account = repository.findById(accountID);
        if(account.isEmpty()){
            throw new EntityNotFoundException();
        }
        return account.get();
    }

    /**
     * Method to find if account with this IBAN, already exist
     * @param account
     * @return true if the account exists, false if not
     */
    public boolean exists(Account account){
        Optional<Account> optionalAccount = Optional.ofNullable(repository.findByIBAN(account.getIBAN()));
        return optionalAccount.isPresent();
    }

    /**
     * Auxiliary method for validation
     * @param IBAN
     * @param balance
     * @return true if the data are correct, false if not
     */
    public boolean validation(String IBAN, BigDecimal balance){
        BigDecimal zero = new BigDecimal(0);
        int result = balance.compareTo(zero);
        return !IBAN.isEmpty() && isIBANValid(IBAN) && result >= 0;
    }

    /**
     * Method for creating account and validating account's data
     * @param account
     * @return ACCOUNT
     */
    @Transactional
    public Account create(Account account){
        if(!validation(account.getIBAN(),account.getBalance())){
            throw new EntityStateException(account);
        }
        if(exists(account)){
            throw new EntityStateException(account);
        }
        return repository.save(account);
    }

    /**
     * Method for getting all accounts
     * @return Collection of account
     */
    public Collection<Account> readAll(){
        return repository.findAll();
    }

    /**
     * Method for adding customer, we have to check if the account with this ID exists
     * @param accountID
     * @param customer
     */
    @Transactional
    public void addCustomer(Long accountID, Customer customer) {
        Optional<Account> account = repository.findById(accountID);
        if(account.isEmpty()){
            throw new EntityNotFoundException();
        }
        account.get().setCustomer(customer);
    }

    /**
     * Method for deleting, we have to check if the account with this ID exists
     * @param accountID
     */
    public void delete(Long accountID) {
        if(!repository.existsById(accountID)){
            throw new EntityNotFoundException("Account with this ID does not exist!");
        }
        repository.deleteById(accountID);
    }

    /**
     * Method for update, we have to check if the account with this ID exists
     * @param accountID
     * @param account
     * @return updated Account
     */
    @Transactional
    public Account update(Long accountID,Account account) {
        if(!repository.existsById(accountID)){
            throw new EntityNotFoundException("Account with this ID does not exist!");
        }
        if(!validation(account.getIBAN(),account.getBalance())){
            throw new EntityStateException(account);
        }
        Account updatedAccount = repository.getById(accountID);
        updatedAccount.setIBAN(account.getIBAN());
        updatedAccount.setBalance(account.getBalance());
        updatedAccount.setCurrency(account.getCurrency());
        return updatedAccount;
    }

    /**
     * Method for deleting customer
     * @param accountID
     * @param customerById
     */
    @Transactional
    public void deleteCustomer(Long accountID, Customer customerById) {
        if(!repository.existsById(accountID)){
            throw new EntityNotFoundException("Account with this ID does not exist!");
        }
        Account account = repository.getById(accountID);
        Customer customer = account.getCustomer();
        if(!Objects.equals(customer.getCustomerID(), customerById.getCustomerID())){
            throw new EntityNotFoundException("Account does not have customer with this ID");
        }
        account.setCustomer(null);
    }
}
