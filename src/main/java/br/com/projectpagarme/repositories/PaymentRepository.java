package br.com.projectpagarme.repositories;

import br.com.projectpagarme.entities.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<PaymentEntity, Long> {
}
