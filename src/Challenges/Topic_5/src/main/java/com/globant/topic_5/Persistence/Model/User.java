package com.globant.topic_5.Persistence.Model;

import javax.persistence.*;
import java.util.List;

/**
 * User model that represents the USER table from database
 */
@Entity
@Table(name = "USER", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {
    @Id
    @Column(name = "identification_number")
    private long idNumber;
    @Column(name = "username")
    private String username;
    @Column(name = "name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "zip_code_city")
    private String zipCodeCity;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;

    /*--------- RELATIONSHIP --------*/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "identification_number"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Role> roles;

    public User() {
    }

    /**
     * Constructor used by UserBuilder
     */
    public User(long idNumber, String username, String firstName, String lastName, String address, String zipCodeCity, String state, String country, List<Role> roles) {
        this.idNumber = idNumber;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipCodeCity = zipCodeCity;
        this.state = state;
        this.country = country;
        this.roles = roles;
    }

    public static User MapUser(User from, User to) {
        to.setUsername(from.getUsername());
        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setAddress(from.getAddress());
        to.setZipCodeCity(from.getZipCodeCity());
        to.setState(from.getState());
        to.setCountry(from.getCountry());
        if (from.getRoles().size() > 0) {
            for (Role role : from.getRoles()) {
                if (!to.getRoles().contains(role)) {
                    to.setRoles(from.getRoles());
                    break;
                }
            }
        }
        return to;
    }

    public long getIdNumber() {
        return idNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCodeCity() {
        return zipCodeCity;
    }

    public void setZipCodeCity(String zipCodeCity) {
        this.zipCodeCity = zipCodeCity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
