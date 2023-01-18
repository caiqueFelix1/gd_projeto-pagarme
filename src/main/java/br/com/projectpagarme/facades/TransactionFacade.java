package br.com.projectpagarme.facades;

import br.com.projectpagarme.dtos.requests.TransactionRequestDTO;
import br.com.projectpagarme.dtos.responses.TransactionResponseDTO;
import br.com.projectpagarme.dtos.responses.BalanceResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionFacade {

    List<TransactionResponseDTO> getAll();

    TransactionResponseDTO create(TransactionRequestDTO transactionRequestDTO);

    BalanceResponseDTO getBalance(BigDecimal valueTotal);
}
