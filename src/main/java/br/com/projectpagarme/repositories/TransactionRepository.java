package br.com.projectpagarme.repositories;

import br.com.projectpagarme.entities.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
}
