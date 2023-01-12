package br.com.projectpagarme.projectpagarme.services;

import br.com.projectpagarme.projectpagarme.entities.PaymentEntity;
import br.com.projectpagarme.projectpagarme.enums.PaymentMapperEnum;

public interface PaymentService {

    PaymentEntity create(PaymentMapperEnum paymentMapperEnum);
}
