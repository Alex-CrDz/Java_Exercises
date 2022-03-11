package com.globant.topic_6.Persistance.Model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "BILL")
public class Bill {
    @Id
    private String idBill;
    @Column
    private String idCompany;
    @Column
    private String nameCompany;
    @Column
    private Date expirationDate;
    @Column
    private double valueBill;
    @Column
    private boolean isPaid = false;

    public Bill() {
    }

    public Bill(String idBill, String idCompany, String nameCompany, Date expirationDate, double valueBill, boolean isPaid) {
        this.idBill = idBill;
        this.idCompany = idCompany;
        this.nameCompany = nameCompany;
        this.expirationDate = expirationDate;
        this.valueBill = valueBill;
        this.isPaid = false;
    }
}
