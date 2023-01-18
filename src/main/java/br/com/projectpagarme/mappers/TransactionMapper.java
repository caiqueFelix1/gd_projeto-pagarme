package br.com.projectpagarme.mappers;

import br.com.projectpagarme.dtos.requests.TransactionRequestDTO;
import br.com.projectpagarme.dtos.responses.TransactionResponseDTO;
import br.com.projectpagarme.entities.PaymentEntity;
import br.com.projectpagarme.entities.TransactionEntity;
import br.com.projectpagarme.enums.PaymentMapperEnum;
import br.com.projectpagarme.repositories.PaymentRepository;
import br.com.projectpagarme.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionMapper {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private final ModelMapper MAPPER;

    private final BigDecimal DISCOUNT_CREDIT = new BigDecimal("0.05");

    private final BigDecimal DISCOUNT_DEBIT = new BigDecimal("0.03");

    public TransactionEntity toEntity(TransactionRequestDTO transactionRequestDTO){
        log.info("converting request dto {} to entity", transactionRequestDTO);
        TransactionEntity transactionEntity = MAPPER.map(transactionRequestDTO, TransactionEntity.class);
        PaymentEntity paymentEntity = paymentService.create(transactionEntity.getTypePayment());
        transactionEntity.setPayment(paymentEntity);

        if (transactionEntity.getTypePayment() == PaymentMapperEnum.CREDIT_CARD){
            BigDecimal discountCredit = transactionEntity.getTransactionValue().multiply(DISCOUNT_CREDIT);
            transactionEntity.setTransactionValue(transactionEntity.getTransactionValue().subtract(discountCredit));
        }

        if (transactionEntity.getTypePayment() == PaymentMapperEnum.DEBIT_CARD){
            BigDecimal discountDebit = transactionEntity.getTransactionValue().multiply(DISCOUNT_DEBIT);
            transactionEntity.setTransactionValue(transactionEntity.getTransactionValue().subtract(discountDebit));
        }

        return transactionEntity;
    }

    public TransactionResponseDTO toDto(TransactionEntity transactionEntity){
        log.info("converting entity {} to dto", transactionEntity);
        return MAPPER.map(transactionEntity, TransactionResponseDTO.class);
    }

    public List<TransactionResponseDTO> toListDto(Iterable<TransactionEntity> getAll){
        log.info("converting list of entity{} to list of dto", getAll);
        List<TransactionEntity> result = new ArrayList<>();
        getAll.forEach(result::add);

        return result.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
