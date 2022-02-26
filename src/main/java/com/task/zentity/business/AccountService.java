package com.task.zentity.business;

import com.task.zentity.api.exception.EntityStateException;
import com.task.zentity.dao.AccountRepository;
import com.task.zentity.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public boolean isIBANValid(String IBAN){

        int IBAN_MIN_SIZE = 15;
        int IBAN_MAX_SIZE = 34;
        long IBAN_MAX = 999999999;
        long IBAN_MODULUS = 97;
        String trimmed = IBAN.trim();

        //IBAN characters must be between 15 a 34 characters
        if(trimmed.length()<IBAN_MIN_SIZE || trimmed.length()>IBAN_MAX_SIZE){
            return false;
        }

        //Rearrange
        String reformat = trimmed.substring(4)+trimmed.substring(0,4);
        long total = 0;
        for (int i = 0;i<reformat.length();i++){
            int charValue = Character.getNumericValue(reformat.charAt(i));
            //0 - 0 , z - 35
            if(charValue<0 || charValue>35 ){
                return false;
            }
            //Compute remainder
            total = (charValue > 9 ? total * 100 : total * 10) + charValue;
            if (total > IBAN_MAX) {
                total = (total % IBAN_MODULUS);
            }
        }
        //Modulo 97 of remainder must always be 1
        return (total % IBAN_MODULUS) == 1;
    }

    public boolean exists(Account account){
        Optional<Account> optionalAccount = repository.findByIBAN(account.getIBAN());
        return optionalAccount.isPresent();
    }

    @Transactional
    public Account create(Account account){
        BigDecimal zero = new BigDecimal(0);
        int result = account.getBalance().compareTo(zero);
        if(account.getIBAN().isEmpty() || !isIBANValid(account.getIBAN())){
            throw new EntityStateException(account);
        }
        if(result<0){
            throw new EntityStateException(account);
        }
        if(exists(account)){
            throw new EntityStateException(account);
        }
        return repository.save(account);
    }

    public Collection<Account> readAll(){
        return repository.findAll();
    }
}
