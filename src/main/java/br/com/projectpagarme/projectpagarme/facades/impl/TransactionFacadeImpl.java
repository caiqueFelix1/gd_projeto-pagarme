package br.com.projectpagarme.projectpagarme.facades.impl;

import br.com.projectpagarme.projectpagarme.dtos.requests.TransactionRequestDTO;
import br.com.projectpagarme.projectpagarme.dtos.responses.TransactionResponseDTO;
import br.com.projectpagarme.projectpagarme.facades.TransactionFacade;
import br.com.projectpagarme.projectpagarme.mapper.TransactionMapper;
import br.com.projectpagarme.projectpagarme.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransactionFacadeImpl implements TransactionFacade {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public TransactionResponseDTO create(TransactionRequestDTO transactionRequestDTO) {


        return transactionMapper.toDto(transactionService.create(transactionMapper.toEntity(transactionRequestDTO)));
    }

}
