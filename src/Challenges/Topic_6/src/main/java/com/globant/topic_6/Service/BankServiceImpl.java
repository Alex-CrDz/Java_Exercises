package com.globant.topic_6.Service;

import com.globant.topic_6.Persistance.BankRepository;
import com.globant.topic_6.Persistance.Model.Bank;
import com.globant.topic_6.Service.Interfaces.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    BankRepository bankRepo;

    @Override
    public Bank getBankById(long idBank) {
        AtomicReference<Bank> bank = new AtomicReference<Bank>();
        bankRepo.findById(idBank).ifPresentOrElse(dbBank -> bank.set(dbBank), () -> {
            throw new RuntimeException("Bank not found");
        });
        return bank.get();
    }

    @Override
    public Bank getBankByName(String nameBank) {
        AtomicReference<Bank> bank = new AtomicReference<Bank>();
        bankRepo.findByNameBank(nameBank).ifPresentOrElse(dbBank -> bank.set(dbBank), () -> {
            throw new RuntimeException("Bank not found");
        });
        return bank.get();
    }
}
