package com.task.zentity.api.controller;

import com.task.zentity.business.AccountService;
import com.task.zentity.business.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class AccountCustomerController {

    private final AccountService accountService;
    private final CustomerService customerService;

    @Autowired
    public AccountCustomerController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @PostMapping("/addCustomer/{accountID}/{customerID}")
    public void addCustomer(@PathVariable Long accountID, @PathVariable Long customerID){
        accountService.addCustomer(accountID, customerService.getCustomerById(customerID));
        customerService.addAccount(customerID, accountService.getAccountById(accountID));
    }
    @DeleteMapping("/deleteCustomer/{accountID}/{customerID}")
    public void deleteCustomer(@PathVariable Long accountID, @PathVariable Long customerID){
        accountService.deleteCustomer(accountID, customerService.getCustomerById(customerID));
        customerService.deleteAccount(customerID, accountService.getAccountById(accountID));
    }

}
