package com.globant.topic_5.dto;

/**
 * Data transfer object used for create and edit user
 */
public class UserDto {
    private String idNumber;
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String zipCodeCity;
    private String state;
    private String country;
    private String role;

    public String getIdNumber() {
        return idNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCodeCity() {
        return zipCodeCity;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getRole() {
        return role;
    }
}
