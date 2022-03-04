package com.task.zentity.api.converter;

import com.task.zentity.api.dto.TransferDTO;
import com.task.zentity.domain.Transfer;

import java.util.Collection;
import java.util.stream.Collectors;

public class TransferConverter {

    /**
     * Method for converting DTO into model
     * @param transfer
     * @return Transfer
     */
    public static TransferDTO fromModel(Transfer transfer){
        return new TransferDTO(transfer.getTransferID(), transfer.getDate(),transfer.getAmount(),
                transfer.getDebtorIBAN(), transfer.getCreditorIBAN(), transfer.getMessage(),
                transfer.getDebtor(), transfer.getCreditor());
    }

    /**
     * Method for converting model into DTO
     * @param transferDTO
     * @return Transfer
     */
    public static Transfer toModel(TransferDTO transferDTO){
        return new Transfer(transferDTO.getDate(),transferDTO.getAmount(),
                transferDTO.getDebtorIBAN(), transferDTO.getCreditorIBAN(), transferDTO.getMessage(),
                transferDTO.getDebtor(), transferDTO.getCreditor());
    }

    /**
     * Method for converting models into DTO
     * @param transfers
     * @return Collection<TransferDTO>
     */
    public static Collection<TransferDTO> fromModels(Collection<Transfer> transfers){
        return transfers.stream().map(TransferConverter::fromModel).collect(Collectors.toList());
    }

}
