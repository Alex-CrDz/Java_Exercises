package com.globant.topic_6.Service.Interfaces;

import com.globant.topic_6.Persistance.Model.Bill;

public interface BillService {
    Bill getBillById(String idBill);
    Bill updateBill(Bill bill);
}
