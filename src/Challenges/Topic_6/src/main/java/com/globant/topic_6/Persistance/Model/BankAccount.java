package com.globant.topic_6.Persistance.Model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "BANK_ACCOUNT")
public class BankAccount {
    public static final String SAVINGS = "AHORROS";
    public static final String CHECKING = "CORRIENTE";

    @Id
    private String idAccount;
    @Column
    private String typeAccount;
    @Column
    private String ownerIdNumber;
    @Column
    private String ownerFirstName;
    @Column
    private String ownerLastName;
    @Column
    private double fundsAccount;
    /* ----- RELATIONSHIPS ----- */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank")
    private Bank bank;

    public BankAccount() {
    }

    public BankAccount(String idAccount, String typeAccount, String ownerIdNumber, String ownerFirstName, String ownerLastName, double fundsAccount, Bank bank) {
        this.idAccount = idAccount;
        this.typeAccount = typeAccount;
        this.ownerIdNumber = ownerIdNumber;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
        this.fundsAccount = fundsAccount;
        this.bank = bank;
    }
}
