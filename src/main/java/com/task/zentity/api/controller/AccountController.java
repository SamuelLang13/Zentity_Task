package com.task.zentity.api.controller;

import com.task.zentity.api.converter.AccountConverter;
import com.task.zentity.api.converter.CustomerConverter;
import com.task.zentity.api.dto.AccountDTO;
import com.task.zentity.api.dto.CustomerDTO;
import com.task.zentity.business.AccountService;
import com.task.zentity.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<AccountDTO> get(){
        return AccountConverter.fromModels(service.readAll());
    }

    @GetMapping("/{accountID}")
    public AccountDTO viewSummary(@PathVariable Long accountID){
        return AccountConverter.fromModel(service.getAccountById(accountID));
    }

    @PostMapping
    public AccountDTO create(@RequestBody AccountDTO accountDTO){
        return AccountConverter.fromModel(service.create(AccountConverter.toModel(accountDTO)));
    }

    @PutMapping("/{accountID}")
    public AccountDTO update(@PathVariable Long accountID, @RequestBody AccountDTO accountDTO){
        return AccountConverter.fromModel(service.update(accountID,AccountConverter.toModel(accountDTO)));
    }

    @DeleteMapping("/{accountID}")
    public void delete(@PathVariable Long accountID){
        service.delete(accountID);
    }

}
