package com.globant.topic_6.Persistance;

import com.globant.topic_6.Persistance.Model.TransactionTransfer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTransferRepository extends CrudRepository<TransactionTransfer, Long> {
}
