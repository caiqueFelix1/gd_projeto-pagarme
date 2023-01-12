package br.com.projectpagarme.projectpagarme.repositories;

import br.com.projectpagarme.projectpagarme.entities.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
}
