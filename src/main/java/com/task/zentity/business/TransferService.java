package com.task.zentity.business;

import com.task.zentity.api.exception.EntityStateException;
import com.task.zentity.dao.AccountRepository;
import com.task.zentity.dao.TransferRepository;
import com.task.zentity.domain.Account;
import com.task.zentity.domain.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService extends ValidationService {

    private final TransferRepository repository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransferService(TransferRepository repository, AccountRepository accountRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
    }


    /**
     * Auxiliary method for validating data
     * @param IBANDebtor
     * @param IBANCreditor
     * @param balance
     * @return true if data are correct, false if not
     */
    public boolean validation(String IBANDebtor,String IBANCreditor, BigDecimal balance){
        BigDecimal zero = new BigDecimal(0);
        int result = balance.compareTo(zero);
        return isIBANValid(IBANDebtor) && isIBANValid(IBANCreditor) && result >= 0;
    }

    /**
     * Method for checking if the debtor has enough amount of money
     * @param amount
     * @param debtorBalance
     * @return true if yes, false if not
     */
    public boolean isSufficientAmount(BigDecimal amount, BigDecimal debtorBalance){
        return debtorBalance.compareTo(amount) >= 0;
    }

    /**
     * Method for creating Transfer
     * @param transfer
     * @return transfer
     */
    @Transactional
    public Transfer create(Transfer transfer) {
        if(!validation(transfer.getDebtorIBAN(),transfer.getCreditorIBAN(),transfer.getAmount())){
            throw  new EntityStateException(transfer);
        }
        if(!accountRepository.existsByIBAN(transfer.getCreditorIBAN()) || !accountRepository.existsByIBAN(transfer.getDebtorIBAN())){
            throw  new EntityStateException(transfer);
        }
        Account creditor = accountRepository.findByIBAN(transfer.getCreditorIBAN());
        Account debtor = accountRepository.findByIBAN(transfer.getDebtorIBAN());
        if(!isSufficientAmount(transfer.getAmount(),debtor.getBalance())){
            throw new EntityStateException("Debtor does not have enough amount of money");
        }
        creditor.addBalance(transfer.getAmount());
        debtor.subBalance(transfer.getAmount());
        transfer.setCreditor(creditor);
        transfer.setDebtor(debtor);

        return repository.save(transfer);
    }

    /**
     * Method for getting all transfers sorted by the date
     * @return Collection of sorted transfers
     */
    public Collection<Transfer> readAll() {
        List<Transfer> transfers = repository.findAll();
        Collections.sort(transfers);
        return transfers;
    }

    /**
     * Method for filtering transfers with requested amount
     * @param amount
     * @return transfers with requested amount
     */
    public Collection<Transfer> readByAmount(BigDecimal amount) {
        List<Transfer> transfers = repository.findAll();
        Collections.sort(transfers);
        return transfers.stream().filter(transfer -> transfer.getAmount().compareTo(amount)==0).collect(Collectors.toList());
    }

    /**
     * Method for filtering transfers with requested IBAN (either Creditor or Debtor)
     * @param iban
     * @return transfers with requested IBAN
     */
    public Collection<Transfer> readByIBAN(String iban) {
        List<Transfer> transfers = repository.findAll();
        Collections.sort(transfers);
        return transfers.stream().filter(transfer -> transfer.getCreditorIBAN().compareTo(iban)==0 || transfer.getDebtorIBAN().compareTo(iban)==0).collect(Collectors.toList());
    }

    /**
     * Method for filtering transfers with requested Message
     * @param message
     * @return transfers with requested message
     */
    public Collection<Transfer> readByMessage(String message) {
        List<Transfer> transfers = repository.findAll();
        Collections.sort(transfers);
        return transfers.stream().filter(transfer -> transfer.getMessage().compareTo(message)==0).collect(Collectors.toList());
    }

}
