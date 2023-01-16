package br.com.projectpagarme.projectpagarme.controllers;

import br.com.projectpagarme.projectpagarme.dtos.requests.TransactionRequestDTO;
import br.com.projectpagarme.projectpagarme.dtos.responses.BalanceResponseDTO;
import br.com.projectpagarme.projectpagarme.dtos.responses.TransactionResponseDTO;
import br.com.projectpagarme.projectpagarme.facades.TransactionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionFacade transactionFacade;

    @PostMapping
    public TransactionResponseDTO create(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO){

        return transactionFacade.create(transactionRequestDTO);
    }

    @GetMapping("/{status}")
    public BalanceResponseDTO getAll(TransactionRequestDTO transactionRequestDTO){

        return transactionFacade.getBalance();
    }
}
