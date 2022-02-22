package com.task.zentity.api.controller;


import com.task.zentity.api.dto.CustomerDTO;
import com.task.zentity.business.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {


    final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    CustomerDTO create(@RequestBody CustomerDTO customerDTO){
        return null;
    }

}
