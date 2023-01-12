package br.com.projectpagarme.projectpagarme.services;

import br.com.projectpagarme.projectpagarme.entities.TransactionEntity;

public interface TransactionService {

    TransactionEntity create(TransactionEntity transactionEntity);

}
