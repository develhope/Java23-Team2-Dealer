package com.develhope.spring.users.dtos;

import com.develhope.spring.users.models.Roles;

public class UserUpdaterDTO {
    private String name;
    private String surname;
    private long phoneNumber;
    private String email;

    public UserUpdaterDTO() {
    }

    public UserUpdaterDTO( String name, String surname, long phoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    //getters

    public String getName() {
        return name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }


    //setters

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
