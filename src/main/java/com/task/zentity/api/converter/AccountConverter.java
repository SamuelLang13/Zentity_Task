package com.task.zentity.api.converter;

import com.task.zentity.api.dto.AccountDTO;
import com.task.zentity.api.dto.CustomerDTO;
import com.task.zentity.domain.Account;
import com.task.zentity.domain.Customer;

import java.util.Collection;
import java.util.stream.Collectors;

public class AccountConverter {

    /**
     * Method for converting DTO into model
     * @param accountDTO
     * @return Account
     */
    public static Account toModel(AccountDTO accountDTO){
        return new Account(accountDTO.getIBAN(), accountDTO.getCurrency(), accountDTO.getBalance());
    }

    /**
     * Method for converting model into DTO
     * @param account
     * @return AccountDTO
     */
    public static AccountDTO fromModel(Account account){
        return new AccountDTO(account.getAccountID(), account.getIBAN(), account.getCurrency(), account.getBalance(), account.getCustomer());
    }

    /**
     * Method for converting models into DTOs
     * @param account
     * @return Collection<AccountDTO>
     */
    public static Collection<AccountDTO> fromModels(Collection<Account> account){
        return account.stream().map(AccountConverter::fromModel).collect(Collectors.toList());
    }
}
