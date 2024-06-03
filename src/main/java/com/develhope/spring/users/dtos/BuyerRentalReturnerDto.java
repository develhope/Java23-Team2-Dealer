package com.develhope.spring.users.dtos;

import com.develhope.spring.users.models.Roles;

public class BuyerRentalReturnerDto {

    private String name;

    private String surname;

    private String email;

    private long phoneNumber;

    private Roles roles;

    public BuyerRentalReturnerDto(String name, String surname, String email, long phoneNumber, Roles roles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public BuyerRentalReturnerDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
