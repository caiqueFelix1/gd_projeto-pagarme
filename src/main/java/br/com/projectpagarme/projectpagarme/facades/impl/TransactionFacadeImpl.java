package br.com.projectpagarme.projectpagarme.facades.impl;

import br.com.projectpagarme.projectpagarme.dtos.requests.TransactionRequestDTO;
import br.com.projectpagarme.projectpagarme.dtos.responses.BalanceResponseDTO;
import br.com.projectpagarme.projectpagarme.dtos.responses.TransactionResponseDTO;
import br.com.projectpagarme.projectpagarme.entities.TransactionEntity;
import br.com.projectpagarme.projectpagarme.enums.PaymentMapperEnum;
import br.com.projectpagarme.projectpagarme.facades.TransactionFacade;
import br.com.projectpagarme.projectpagarme.mapper.TransactionMapper;
import br.com.projectpagarme.projectpagarme.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
public class TransactionFacadeImpl implements TransactionFacade {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public TransactionResponseDTO create(TransactionRequestDTO transactionRequestDTO) {

        String getCardNumber = transactionRequestDTO.getCardNumber();
        String cardNumberNotSalved = getCardNumber.substring(getCardNumber.length()-4);

        transactionRequestDTO.setCardNumber(cardNumberNotSalved);

        return transactionMapper.toDto(transactionService.create(transactionMapper.toEntity(transactionRequestDTO)));
    }

    public BalanceResponseDTO getBalance() {

        BalanceResponseDTO balanceResponseDTO = new BalanceResponseDTO();

        for (TransactionEntity transactionEntity : transactionService.getAll()) {

            if (transactionEntity.getTypePayment() == PaymentMapperEnum.CREDIT_CARD) {
                BigDecimal waiting_funds = transactionEntity.getTransactionValue();
                waiting_funds = waiting_funds.add(waiting_funds);

                balanceResponseDTO.setWaiting_funds(waiting_funds);
            }

            if (transactionEntity.getTypePayment() == PaymentMapperEnum.DEBIT_CARD){
                BigDecimal avaible = transactionEntity.getTransactionValue();
                avaible = avaible.add(avaible);

                balanceResponseDTO.setAvailable(avaible);
            }
        }

        return balanceResponseDTO;
    }

    @Override
    public List<TransactionResponseDTO> getAll() {

        return transactionMapper.toListDto(transactionService.getAll());
    }
}
