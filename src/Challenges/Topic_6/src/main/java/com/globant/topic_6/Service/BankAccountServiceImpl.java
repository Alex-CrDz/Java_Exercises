package com.globant.topic_6.Service;

import com.globant.topic_6.Persistance.BankAccountRepository;
import com.globant.topic_6.Persistance.Model.BankAccount;
import com.globant.topic_6.Service.Interfaces.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepo;

    @Override
    public BankAccount findAccountById(String idAccount) {
        AtomicReference<BankAccount> account = new AtomicReference<BankAccount>();
        bankAccountRepo.findById(idAccount).ifPresentOrElse(dbAccount -> account.set(dbAccount), () -> {
                    throw new RuntimeException("Account not found");
                }
        );
        return account.get();
    }

    @Override
    public BankAccount updateAccount(BankAccount account) {
        try {
            bankAccountRepo.save(account);
        } catch (Exception e){
            throw new RuntimeException("Account not updated");
        }
        return account;
    }
}
