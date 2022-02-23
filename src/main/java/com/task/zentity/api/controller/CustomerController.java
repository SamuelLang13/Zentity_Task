package com.task.zentity.api.controller;


import com.task.zentity.api.converter.CustomerConverter;
import com.task.zentity.api.dto.CustomerDTO;
import com.task.zentity.business.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Collection<CustomerDTO> get(){
        return CustomerConverter.fromModels(customerService.readAll());
    }

    @PostMapping
    public CustomerDTO create(@RequestBody CustomerDTO customerDTO) throws Exception {
        return CustomerConverter.fromModel(customerService.create(CustomerConverter.toModel(customerDTO)));
    }
}
