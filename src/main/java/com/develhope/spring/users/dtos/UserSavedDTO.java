package com.develhope.spring.users.dtos;

import com.develhope.spring.users.models.Roles;

public class UserSavedDTO {
    private String name;
    private String surname;
    private Roles roles;


    public UserSavedDTO() {
    }

    public UserSavedDTO(String name, String surname, Roles roles) {
        this.name = name;
        this.surname = surname;
        this.roles = roles;
    }

    //getters
    public String getName() {
        return name;
    }

    public Roles getRoles() {
        return roles;
    }

    public String getSurname() {
        return surname;
    }


    //setters
    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
