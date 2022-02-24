package com.task.zentity.api.converter;

import com.task.zentity.api.dto.AccountDTO;
import com.task.zentity.api.dto.CustomerDTO;
import com.task.zentity.domain.Account;
import com.task.zentity.domain.Customer;

import java.util.Collection;
import java.util.stream.Collectors;

public class AccountConverter {

    public static Account toModel(AccountDTO accountDTO){
        return new Account(accountDTO.getIBAN(), accountDTO.getCurrency(), accountDTO.getBalance());
    }

    public static AccountDTO fromModel(Account account){
        return new AccountDTO(account.getAccountID(), account.getIBAN(), account.getCurrency(), account.getBalance(), account.getCustomer());
    }

    public static Collection<AccountDTO> fromModels(Collection<Account> account){
        return account.stream().map(AccountConverter::fromModel).collect(Collectors.toList());
    }
}
