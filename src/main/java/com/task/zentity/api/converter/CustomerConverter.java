package com.task.zentity.api.converter;

import com.task.zentity.api.dto.CustomerDTO;
import com.task.zentity.domain.Customer;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomerConverter {


    /**
     * Method for converting DTO into model
     * @param customerDTO
     * @return Customer
     */
    public static Customer toModel(CustomerDTO customerDTO){
        return new Customer(customerDTO.getName(), customerDTO.getSurname(), customerDTO.getSex(),
                customerDTO.getNationality(), customerDTO.getDateOfBirth(), customerDTO.getCardNumber(),
                customerDTO.getDateOfIssue(), customerDTO.getDateOfExpiry());
    }

    /**
     * Method for converting model into DTO
     * @param customer
     * @return CustomerDTO
     */
    public static CustomerDTO fromModel(Customer customer){
        return new CustomerDTO(customer.getCustomerID(), customer.getName(), customer.getSurname(), customer.getSex(),
                               customer.getNationality(), customer.getDateOfBirth(), customer.getCardNumber(),
                                customer.getDateOfIssue(), customer.getDateOfExpiry(), customer.getAccounts());
    }

    /**
     * Method for converting models into DTOs
     * @param customer
     * @return Collection<CustomerDTO>
     */
    public static Collection<CustomerDTO> fromModels(Collection<Customer> customer){
        return customer.stream().map(CustomerConverter::fromModel).collect(Collectors.toList());
    }

}
