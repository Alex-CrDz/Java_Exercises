package com.globant.topic_5.Persistence.Model;

import javax.persistence.*;

/**
 * Role model that represents the ROLE table from database
 */
@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @Column(name = "id_role")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRole;
    @Column(name = "name_role")
    private String nameRole;

    public long getIdRole() {
        return idRole;
    }

    public void setIdRole(long idRole) {
        this.idRole = idRole;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }
}
