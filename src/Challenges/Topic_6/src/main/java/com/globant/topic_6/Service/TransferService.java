package com.globant.topic_6.Service;

import com.globant.topic_6.Exceptions.InsufficientFundsException;
import com.globant.topic_6.Exceptions.InvalidTargetFundsException;
import com.globant.topic_6.Persistance.Model.BankAccount;
import com.globant.topic_6.Persistance.Model.TransactionTransfer;
import com.globant.topic_6.Service.Interfaces.BankAccountService;
import com.globant.topic_6.Service.Interfaces.TransactionTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransferService {

    @Autowired
    TransactionTransferService transactionService;
    @Autowired
    BankAccountService accountService;  //  simulates the external service to get and update account info

    public TransactionTransfer transfer(String idOriginAccount, String idDestinationAccount, double amountTransfer) throws Exception {
        BankAccount originAccount = accountService.findAccountById(idOriginAccount);
        BankAccount destinationAccount = accountService.findAccountById(idDestinationAccount);
        TransactionTransfer transaction = TransactionTransfer.builder()
                .originAccount(originAccount)
                .destinationAccount(destinationAccount)
                .amount(amountTransfer)
                .status(TransactionTransfer.ONGOING)
                .build();
        transaction = transactionService.newTransaction(transaction);

        if (originAccount.getFundsAccount() < amountTransfer) { //  validate origin account funds
            transaction.setStatus(TransactionTransfer.CANCELED);
            transaction.setLastModified(new Date());
            transaction = transactionService.updateTransaction(transaction);
            throw new InsufficientFundsException();
        }
        if (destinationAccount.getTypeAccount().equals(BankAccount.CHECKING)
                && destinationAccount.getFundsAccount() > amountTransfer * 3) { //  validates target account type and restrictions
            transaction.setStatus(TransactionTransfer.REJECTED);
            transaction.setLastModified(new Date());
            transaction = transactionService.updateTransaction(transaction);
            throw new InvalidTargetFundsException();
        }
        if (amountTransfer > 1500000) { //  if transaction amount is more than 1.500.000 taxes are charged
            transaction.setAmount(amountTransfer - (amountTransfer * 0.03));
        }
        if (destinationAccount.getBank().getIdBank() != 5) {    //  if target account is from another bank, apply additional charge
            amountTransfer += 3500;
        }
        transaction.setStatus(TransactionTransfer.APPROVED);
        transaction.setLastModified(new Date());
        transaction = transactionService.updateTransaction(transaction);
        originAccount.setFundsAccount(originAccount.getFundsAccount() - amountTransfer);
        destinationAccount.setFundsAccount(destinationAccount.getFundsAccount() + transaction.getAmount());
        accountService.updateAccount(originAccount);
        accountService.updateAccount(destinationAccount);
        transaction.setStatus(TransactionTransfer.FINISHED);
        transaction.setLastModified(new Date());
        transaction = transactionService.updateTransaction(transaction);
        return transaction;
    }
}
