package com.task.zentity.api.controller;


import com.task.zentity.api.converter.CustomerConverter;
import com.task.zentity.api.dto.CustomerDTO;
import com.task.zentity.business.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    CustomerDTO create(@RequestBody CustomerDTO customerDTO) throws Exception {
        return CustomerConverter.fromModel(customerService.create(CustomerConverter.toModel(customerDTO)));
    }

}
