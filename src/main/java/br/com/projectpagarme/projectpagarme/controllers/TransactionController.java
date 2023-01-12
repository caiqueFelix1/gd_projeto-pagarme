package br.com.projectpagarme.projectpagarme.controllers;


import br.com.projectpagarme.projectpagarme.dtos.requests.TransactionRequestDTO;
import br.com.projectpagarme.projectpagarme.dtos.responses.TransactionResponseDTO;
import br.com.projectpagarme.projectpagarme.facades.TransactionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionFacade transactionFacade;

    @PostMapping
    public TransactionResponseDTO create(@RequestBody TransactionRequestDTO transactionRequestDTO){
        return transactionFacade.create(transactionRequestDTO);
    }
}
