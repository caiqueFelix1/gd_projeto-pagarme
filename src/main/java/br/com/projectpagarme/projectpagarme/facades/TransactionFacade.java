package br.com.projectpagarme.projectpagarme.facades;

import br.com.projectpagarme.projectpagarme.dtos.requests.TransactionRequestDTO;
import br.com.projectpagarme.projectpagarme.dtos.responses.BalanceResponseDTO;
import br.com.projectpagarme.projectpagarme.dtos.responses.TransactionResponseDTO;

import java.util.List;

public interface TransactionFacade {

    TransactionResponseDTO create(TransactionRequestDTO transactionRequestDTO);

    List<TransactionResponseDTO> getAll();

    BalanceResponseDTO getBalance();
}
