package br.com.projectpagarme.projectpagarme.facades.impl;

import br.com.projectpagarme.projectpagarme.dtos.requests.TransactionRequestDTO;
import br.com.projectpagarme.projectpagarme.dtos.responses.BalanceResponseDTO;
import br.com.projectpagarme.projectpagarme.dtos.responses.TransactionResponseDTO;
import br.com.projectpagarme.projectpagarme.entities.TransactionEntity;
import br.com.projectpagarme.projectpagarme.enums.PaymentMapperEnum;
import br.com.projectpagarme.projectpagarme.facades.TransactionFacade;
import br.com.projectpagarme.projectpagarme.mappers.TransactionMapper;
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

        transactionRequestDTO.setCardNumber(hidingCardDigits(transactionRequestDTO.getCardNumber()));

        return transactionMapper.toDto(transactionService.create(transactionMapper.toEntity(transactionRequestDTO)));
    }

    public BalanceResponseDTO getBalance(BigDecimal valueTotal) {

        BigDecimal waiting_funds = BigDecimal.ZERO;

        BigDecimal available = BigDecimal.ZERO;

        for (TransactionEntity transactionEntity : transactionService.getAll()) {

            if (transactionEntity.getTypePayment() == PaymentMapperEnum.CREDIT_CARD) {
                BigDecimal waiting = transactionEntity.getTransactionValue();
                waiting_funds = waiting_funds.add(waiting);
            }

            if (transactionEntity.getTypePayment() == PaymentMapperEnum.DEBIT_CARD){
                BigDecimal accessible = transactionEntity.getTransactionValue();
                available = available.add(accessible);
            }
        }

        BalanceResponseDTO balanceResponseDTO = new BalanceResponseDTO();
        balanceResponseDTO.setAvailable(available);
        balanceResponseDTO.setWaiting_funds(waiting_funds);

        return balanceResponseDTO;
    }

    @Override
    public List<TransactionResponseDTO> getAll() {

        return transactionMapper.toListDto(transactionService.getAll());
    }

    private String hidingCardDigits(String numberCard){

        final String cardDigits = "**** **** **** ";
        String lastFourNumberCard = numberCard.substring(numberCard.length()-4);

        return cardDigits + lastFourNumberCard;
    }
}
