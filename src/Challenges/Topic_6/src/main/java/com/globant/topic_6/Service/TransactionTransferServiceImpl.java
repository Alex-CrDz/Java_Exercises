package com.globant.topic_6.Service;

import com.globant.topic_6.Persistance.Model.TransactionTransfer;
import com.globant.topic_6.Persistance.TransactionTransferRepository;
import com.globant.topic_6.Service.Interfaces.TransactionTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class TransactionTransferServiceImpl implements TransactionTransferService {
    @Autowired
    TransactionTransferRepository transactionRepo;

    @Override
    public TransactionTransfer findTransactionById(long idTransaction) {
        AtomicReference<TransactionTransfer> transaction = new AtomicReference<TransactionTransfer>();
        transactionRepo.findById(idTransaction).ifPresentOrElse(dbTransaction -> transaction.set(dbTransaction), () -> {
            throw new RuntimeException("Transaction not found");
        });
        return transaction.get();
    }

    @Override
    public TransactionTransfer newTransaction(TransactionTransfer transaction) {
        try {
            transaction = transactionRepo.save(transaction);
        } catch (Exception e) {
            throw new RuntimeException("Transaction not created");
        }
        return transaction;
    }

    @Override
    public TransactionTransfer updateTransaction(TransactionTransfer transaction) {
        try {
            transaction = transactionRepo.save(transaction);
        } catch (Exception e) {
            throw new RuntimeException("Transaction not updated");
        }
        return transaction;
    }
}
