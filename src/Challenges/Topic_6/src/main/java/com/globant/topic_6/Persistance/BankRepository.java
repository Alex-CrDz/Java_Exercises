package com.globant.topic_6.Persistance;

import com.globant.topic_6.Persistance.Model.Bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends CrudRepository<Bank, Long> {
    Optional<Bank> findByNameBank(String nameBank);
}
