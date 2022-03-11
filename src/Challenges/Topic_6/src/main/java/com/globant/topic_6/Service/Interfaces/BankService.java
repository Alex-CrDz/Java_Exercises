package com.globant.topic_6.Service.Interfaces;

import com.globant.topic_6.Persistance.Model.Bank;

public interface BankService {
    Bank getBankById(long idBank);

    Bank getBankByName(String nameBank);
}
