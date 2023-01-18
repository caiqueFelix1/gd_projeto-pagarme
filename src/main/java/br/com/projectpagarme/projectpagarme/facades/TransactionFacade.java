package br.com.projectpagarme.projectpagarme.facades;

import br.com.projectpagarme.projectpagarme.dtos.requests.TransactionRequestDTO;
import br.com.projectpagarme.projectpagarme.dtos.responses.BalanceResponseDTO;
import br.com.projectpagarme.projectpagarme.dtos.responses.TransactionResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionFacade {

    List<TransactionResponseDTO> getAll();

    TransactionResponseDTO create(TransactionRequestDTO transactionRequestDTO);

    BalanceResponseDTO getBalance(BigDecimal valueTotal);
}
