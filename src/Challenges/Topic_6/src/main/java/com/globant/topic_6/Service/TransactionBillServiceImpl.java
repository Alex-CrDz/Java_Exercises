package com.globant.topic_6.Service;

import com.globant.topic_6.Persistance.Model.TransactionBill;
import com.globant.topic_6.Persistance.TransactionBillRepository;
import com.globant.topic_6.Service.Interfaces.TransactionBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class TransactionBillServiceImpl implements TransactionBillService {
    @Autowired
    TransactionBillRepository transactionRepo;

    @Override
    public TransactionBill findTransactionById(long idTransaction) {
        AtomicReference<TransactionBill> transaction = new AtomicReference<TransactionBill>();
        transactionRepo.findById(idTransaction).ifPresentOrElse(dbTransaction -> transaction.set(dbTransaction), () -> {
            throw new RuntimeException("Transaction not found");
        });
        return transaction.get();
    }

    @Override
    public TransactionBill newTransaction(TransactionBill transaction) {
        try {
            transaction = transactionRepo.save(transaction);
        } catch (Exception e) {
            throw new RuntimeException("Transaction not created");
        }
        return transaction;
    }

    @Override
    public TransactionBill updateTransaction(TransactionBill transaction) {
        try {
            transaction = transactionRepo.save(transaction);
        } catch (Exception e) {
            throw new RuntimeException("Transaction not updated");
        }
        return transaction;
    }
}
