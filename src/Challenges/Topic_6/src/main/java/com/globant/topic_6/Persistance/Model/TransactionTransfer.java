package com.globant.topic_6.Persistance.Model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "TRANSACTION_TRANSFER")
public class TransactionTransfer {
    public static final String ONGOING = "ONGOING";
    public static final String APPROVED = "APPROVED";
    public static final String CANCELED = "CANCELED";
    public static final String FINISHED = "FINISHED";
    public static final String REJECTED = "REJECTED";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idTransaction;
    @Column
    private String status;
    @Column
    private double amount;
    @Column
    private Date createdAt;
    @Column
    private Date lastModified;
    /* ----- RELATIONSHIPS ----- */
    @ManyToOne
    @JoinColumn(name = "originAccount")
    private BankAccount originAccount;
    @ManyToOne
    @JoinColumn(name = "destinationAccount")
    private BankAccount destinationAccount;

    public TransactionTransfer(long idTransaction, String status, double amount, Date createdAt, Date lastModified, BankAccount originAccount, BankAccount destinationAccount) {
        this.idTransaction = idTransaction;
        this.status = status;
        this.amount = amount;
        this.createdAt = new Date();
        this.lastModified = this.createdAt;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
    }

    public TransactionTransfer() {
    }
}
