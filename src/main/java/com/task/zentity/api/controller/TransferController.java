package com.task.zentity.api.controller;

import com.task.zentity.api.converter.TransferConverter;
import com.task.zentity.api.dto.TransferDTO;
import com.task.zentity.business.AccountService;
import com.task.zentity.business.TransferService;
import com.task.zentity.domain.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService service;
    private final AccountService accountService;

    @Autowired
    public TransferController(TransferService transferService, AccountService accountService) {
        this.service = transferService;
        this.accountService = accountService;
    }

    @PostMapping
    public TransferDTO create(@RequestBody TransferDTO transferDTO){
        return TransferConverter.fromModel(service.create(TransferConverter.toModel(transferDTO)));
    }

    @GetMapping("/history")
    public Collection<TransferDTO> getTransactions(){
        return TransferConverter.fromModels(service.readAll());
    }

}
