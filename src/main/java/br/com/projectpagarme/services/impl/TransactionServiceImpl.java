package br.com.projectpagarme.services.impl;

import br.com.projectpagarme.entities.TransactionEntity;
import br.com.projectpagarme.repositories.TransactionRepository;
import br.com.projectpagarme.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionEntity create(TransactionEntity transactionEntity) {
        log.info("creating a new transaction", transactionEntity);
        return transactionRepository.save(transactionEntity);
    }

    @Override
    public Iterable<TransactionEntity> getAll() {
        log.info("getting all transactions");
        return transactionRepository.findAll();
    }
}
