package br.com.projectpagarme.projectpagarme.repositories;

import br.com.projectpagarme.projectpagarme.entities.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<PaymentEntity, Long> {
}
