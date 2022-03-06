package com.globant.topic_5.Util;

import com.globant.topic_5.Persistence.Model.Role;
import com.globant.topic_5.Persistence.Model.User;
import com.globant.topic_5.Persistence.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class UserBuilder {

    @Autowired
    private RoleRepository roleRepo;

    private String idNumber;
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String zipCodeCity;
    private String state;
    private String country;
    private String role;

    public UserBuilder() {
        this.idNumber = null;
        this.username = null;
        this.firstName = null;
        this.lastName = null;
        this.address = null;
        this.zipCodeCity = null;
        this.state = null;
        this.country = null;
        this.role = null;
    }

    public UserBuilder setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder setZipCodeCity(String zipCodeCity) {
        this.zipCodeCity = zipCodeCity;
        return this;
    }

    public UserBuilder setState(String state) {
        this.state = state;
        return this;
    }

    public UserBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public UserBuilder setRole(String role) {
        this.role = role;
        return this;
    }

    public User build() {
        List<Role> roles = new ArrayList<Role>();
        if (this.role != null) {
            AtomicReference<Role> role = new AtomicReference<Role>();
            roleRepo.findByNameRole(this.role).ifPresent(dbRole -> {
                role.set(dbRole);
                roles.add(role.get());
            });
        }
        return new User(Long.parseLong(idNumber), username, firstName, lastName, address, zipCodeCity, state, country, roles);
    }
}
