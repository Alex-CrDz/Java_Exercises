package com.globant.topic_6.Service.Interfaces;

import com.globant.topic_6.Persistance.Model.TransactionBill;

public interface TransactionBillService {
    TransactionBill findTransactionById(long idTransaction);

    TransactionBill newTransaction(TransactionBill transaction);

    TransactionBill updateTransaction(TransactionBill transaction);
}
