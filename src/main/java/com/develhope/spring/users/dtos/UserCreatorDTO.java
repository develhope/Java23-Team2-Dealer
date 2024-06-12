package com.develhope.spring.users.dtos;

import com.develhope.spring.users.models.Roles;

public class UserCreatorDTO {

    private String name;
    private String surname;
    private long phoneNumber;
    private String email;
    private Roles roles;


    public UserCreatorDTO() {
    }

    public UserCreatorDTO(String name, String surname, long phoneNumber, String email, Roles roles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roles = roles;
        this.phoneNumber = phoneNumber;
    }

    //getters
    public String getName() {
        return name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public Roles getRoles() {
        return roles;
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

    public void setRoles(Roles roles) {
        this.roles = roles;
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