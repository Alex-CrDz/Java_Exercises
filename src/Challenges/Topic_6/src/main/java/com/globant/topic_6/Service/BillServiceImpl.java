package com.globant.topic_6.Service;

import com.globant.topic_6.Persistance.BillRepository;
import com.globant.topic_6.Persistance.Model.Bill;
import com.globant.topic_6.Service.Interfaces.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepo;

    @Override
    public Bill getBillById(String idBill) {
        AtomicReference<Bill> bill = new AtomicReference<Bill>();
        billRepo.findById(idBill).ifPresentOrElse(dbBill -> bill.set(dbBill), () -> {
            throw new RuntimeException("Bill not found");
        });
        return bill.get();
    }

    @Override
    public Bill updateBill(Bill bill) {
        try {
            billRepo.save(bill);
        } catch (Exception e){
            throw new RuntimeException("Bill not updated");
        }
        return bill;
    }
}
