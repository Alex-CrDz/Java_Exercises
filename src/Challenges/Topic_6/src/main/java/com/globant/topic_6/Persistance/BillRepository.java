package com.globant.topic_6.Persistance;

import com.globant.topic_6.Persistance.Model.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends CrudRepository<Bill, String> {
}
