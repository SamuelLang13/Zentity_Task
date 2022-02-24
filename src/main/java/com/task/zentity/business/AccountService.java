package com.task.zentity.business;

import com.task.zentity.api.exception.EntityStateException;
import com.task.zentity.dao.AccountRepository;
import com.task.zentity.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public boolean exists(Account account){
        Optional<Account> optionalAccount = repository.findByIBAN(account.getIBAN());
        return optionalAccount.isPresent();
    }

    @Transactional
    public Account create(Account account){
        if(exists(account)){
            throw new EntityStateException(account);
        }
        return repository.save(account);
    }

    public Collection<Account> readAll(){
        return repository.findAll();
    }
}
