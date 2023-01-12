package br.com.projectpagarme.projectpagarme.services.Impl;

import br.com.projectpagarme.projectpagarme.entities.PaymentEntity;
import br.com.projectpagarme.projectpagarme.enums.PaymentMapperEnum;
import br.com.projectpagarme.projectpagarme.repositories.PaymentRepository;
import br.com.projectpagarme.projectpagarme.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public PaymentEntity create(PaymentMapperEnum paymentMapperEnum) {
        log.info("creating a new payable {}", paymentMapperEnum);

        PaymentEntity paymentEntity = new PaymentEntity();
        if(paymentMapperEnum == PaymentMapperEnum.CREDIT_CARD){
            paymentEntity.setStatus(PaymentMapperEnum.CREDIT_CARD.getStatus());
            paymentEntity.setPaymentDate(LocalDate.now().plusDays(30));
        }

        if(paymentMapperEnum == PaymentMapperEnum.DEBIT_CARD){
            paymentEntity.setStatus(PaymentMapperEnum.DEBIT_CARD.getStatus());
            paymentEntity.setPaymentDate(LocalDate.now());
        }

        return paymentRepository.save(paymentEntity);
    }
}
