package br.com.projectpagarme.services;

import br.com.projectpagarme.entities.PaymentEntity;
import br.com.projectpagarme.enums.PaymentMapperEnum;

public interface PaymentService {

    PaymentEntity create(PaymentMapperEnum paymentMapperEnum);
}
