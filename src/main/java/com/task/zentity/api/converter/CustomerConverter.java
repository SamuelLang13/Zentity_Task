package com.task.zentity.api.converter;

import com.task.zentity.api.dto.CustomerDTO;
import com.task.zentity.domain.Customer;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomerConverter {

    public static Customer toModel(CustomerDTO customerDTO){
        return new Customer(customerDTO.getName(), customerDTO.getSurname(), customerDTO.getSex(),
                customerDTO.getNationality(), customerDTO.getDateOfBirth(), customerDTO.getCardNumber(),
                customerDTO.getDateOfIssue(), customerDTO.getDateOfExpiry());
    }

    public static CustomerDTO fromModel(Customer customer){
        return new CustomerDTO(customer.getAccountID(), customer.getName(), customer.getSurname(), customer.getSex(),
                               customer.getNationality(), customer.getDateOfBirth(), customer.getCardNumber(),
                                customer.getDateOfIssue(), customer.getDateOfExpiry(), customer.getAccounts());
    }

    public static Collection<Customer> toModels(Collection<CustomerDTO> customerDTOS){
        return customerDTOS.stream().map(CustomerConverter::toModel).collect(Collectors.toList());
    }

    public static Collection<CustomerDTO> fromModels(Collection<Customer> customer){
        return customer.stream().map(CustomerConverter::fromModel).collect(Collectors.toList());
    }

}
