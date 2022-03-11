package com.globant.topic_6.Persistance;

import com.globant.topic_6.Persistance.Model.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, String> {
}
