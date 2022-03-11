package com.globant.topic_6.Service;

import com.globant.topic_6.Exceptions.InsufficientFundsException;
import com.globant.topic_6.Exceptions.InvalidBillIdException;
import com.globant.topic_6.Persistance.Model.BankAccount;
import com.globant.topic_6.Persistance.Model.Bill;
import com.globant.topic_6.Persistance.Model.TransactionBill;
import com.globant.topic_6.Persistance.Model.TransactionTransfer;
import com.globant.topic_6.Service.Interfaces.BankAccountService;
import com.globant.topic_6.Service.Interfaces.BillService;
import com.globant.topic_6.Service.Interfaces.TransactionBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OnlinePaymentService {

    @Autowired
    private BankAccountService accountService;
    @Autowired
    private BillService billService;    //  simulates the external service to get and update the bills info
    @Autowired
    private TransactionBillService transactionService;

    public TransactionBill onlinePayment(String idOriginAccount, String idBill) throws Exception {
        BankAccount originAccount = accountService.findAccountById(idOriginAccount);
        Bill bill = billService.getBillById(idBill);
        TransactionBill transaction = TransactionBill.builder()
                .status(TransactionBill.ONGOING)
                .originAccount(originAccount)
                .billToPay(bill)
                .amount(bill.getValueBill())
                .build();
        transaction = transactionService.newTransaction(transaction);

        if (bill.getIdBill().length() != 7
                || !bill.getIdBill().substring(0, 2).equals("00")) {    //  validates id bill format
            transaction.setStatus(TransactionTransfer.REJECTED);
            transaction.setLastModified(new Date());
            transaction = transactionService.updateTransaction(transaction);
            throw new InvalidBillIdException();
        }

        if (bill.isPaid()) {    //  if bill was paid, reject the transaction
            transaction.setStatus(TransactionTransfer.REJECTED);
            transaction.setLastModified(new Date());
            transaction = transactionService.updateTransaction(transaction);
            return transaction;
        }

        if (bill.getValueBill() + (bill.getValueBill() * 0.2) > originAccount.getFundsAccount()) {  //  validates origin account funds
            transaction.setStatus(TransactionTransfer.CANCELED);
            transaction.setLastModified(new Date());
            transaction = transactionService.updateTransaction(transaction);
            throw new InsufficientFundsException(InsufficientFundsException.MESSAGE_BILL);
        }

        if (originAccount.getTypeAccount().equals(BankAccount.CHECKING)) {  //  validates origin account type to apply discount
            transaction.setAmount(bill.getValueBill() * 0.9);
        }

        transaction.setStatus(TransactionTransfer.APPROVED);
        transaction.setLastModified(new Date());
        transaction = transactionService.updateTransaction(transaction);
        originAccount.setFundsAccount(originAccount.getFundsAccount() - transaction.getAmount());
        bill.setPaid(true);
        accountService.updateAccount(originAccount);
        billService.updateBill(bill);
        transaction.setStatus(TransactionTransfer.FINISHED);
        transaction.setLastModified(new Date());
        transaction = transactionService.updateTransaction(transaction);
        return transaction;
    }
}
