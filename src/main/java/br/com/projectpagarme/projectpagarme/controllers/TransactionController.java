package br.com.projectpagarme.projectpagarme.controllers;

import br.com.projectpagarme.projectpagarme.dtos.requests.TransactionRequestDTO;
import br.com.projectpagarme.projectpagarme.dtos.responses.BalanceResponseDTO;
import br.com.projectpagarme.projectpagarme.dtos.responses.TransactionResponseDTO;
import br.com.projectpagarme.projectpagarme.facades.TransactionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionFacade transactionFacade;

    @PostMapping
    public TransactionResponseDTO create(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO){

        return transactionFacade.create(transactionRequestDTO);
    }

    @GetMapping("/balance")
    public BalanceResponseDTO getBalance(TransactionRequestDTO transactionRequestDTO){

        return transactionFacade.getBalance(transactionRequestDTO.getTransactionValue());
    }

    @GetMapping
    public List<TransactionResponseDTO> getAll(){
        return transactionFacade.getAll();
    }
}
