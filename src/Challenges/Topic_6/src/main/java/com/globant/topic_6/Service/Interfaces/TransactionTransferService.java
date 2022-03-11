package com.globant.topic_6.Service.Interfaces;

import com.globant.topic_6.Persistance.Model.TransactionTransfer;

public interface TransactionTransferService {
    TransactionTransfer findTransactionById(long idTransaction);

    TransactionTransfer newTransaction(TransactionTransfer transaction);

    TransactionTransfer updateTransaction(TransactionTransfer transaction);
}
