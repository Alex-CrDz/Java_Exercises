package com.globant.topic_6.Persistance.Model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(name = "BANK")
public class Bank {
    @Id
    private long idBank;
    @Column
    private String nameBank;

    public Bank(long idBank, String nameBank) {
        this.idBank = idBank;
        this.nameBank = nameBank;
    }

    public Bank() {
    }
}
