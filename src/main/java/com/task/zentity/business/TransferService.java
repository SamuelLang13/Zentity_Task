package com.task.zentity.business;

import com.task.zentity.api.exception.EntityStateException;
import com.task.zentity.dao.AccountRepository;
import com.task.zentity.dao.TransferRepository;
import com.task.zentity.domain.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class TransferService {

    private final TransferRepository repository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransferService(TransferRepository repository, AccountRepository accountRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
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


    public boolean validation(String IBANDebtor,String IBANCreditor, BigDecimal balance){
        BigDecimal zero = new BigDecimal(0);
        int result = balance.compareTo(zero);
        return isIBANValid(IBANDebtor) && isIBANValid(IBANCreditor) && result >= 0;
    }

    public boolean isSufficientAmount(BigDecimal amount, BigDecimal debtorBalance){
        return debtorBalance.compareTo(amount) >= 0;
    }

    @Transactional
    public Transfer create(Transfer transfer) {
        if(!validation(transfer.getDebtorIBAN(),transfer.getCreditorIBAN(),transfer.getAmount())){
            throw  new EntityStateException(transfer);
        }
        if(!accountRepository.existsByIBAN(transfer.getCreditorIBAN()) || !accountRepository.existsByIBAN(transfer.getDebtorIBAN())){
            throw  new EntityStateException(transfer);
        }
        accountRepository.findByIBAN(transfer.getCreditorIBAN()).addBalance(transfer.getAmount());
        accountRepository.findByIBAN(transfer.getDebtorIBAN()).subBalance(transfer.getAmount());
        transfer.setCreditor(accountRepository.findByIBAN(transfer.getCreditorIBAN()));
        transfer.setDebtor(accountRepository.findByIBAN(transfer.getDebtorIBAN()));

        return repository.save(transfer);
    }

    public Collection<Transfer> readAll() {
        List<Transfer> transfers = repository.findAll();
        Collections.sort(transfers);
        return transfers;
    }
}
