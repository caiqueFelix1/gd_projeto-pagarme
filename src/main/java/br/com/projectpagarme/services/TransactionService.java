package br.com.projectpagarme.services;

import br.com.projectpagarme.entities.TransactionEntity;

public interface TransactionService {

    TransactionEntity create(TransactionEntity transactionEntity);

    Iterable<TransactionEntity> getAll();
}
