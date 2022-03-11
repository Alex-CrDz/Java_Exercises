package com.globant.topic_6.Service.Interfaces;

import com.globant.topic_6.Persistance.Model.BankAccount;

public interface BankAccountService {
    BankAccount findAccountById(String idAccount);

    BankAccount updateAccount(BankAccount account);
}
