package com.develhope.spring.users.dtos;

import com.develhope.spring.users.models.Roles;

public class UserUpdaterDTO {
    private String name;
    private String surname;
    private String username;
    private long phoneNumber;
    private String email;

    public UserUpdaterDTO() {
    }

    public UserUpdaterDTO(String name, String surname, String username, long phoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;

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

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }
}
