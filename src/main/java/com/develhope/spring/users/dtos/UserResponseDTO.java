package com.develhope.spring.users.dtos;

import com.develhope.spring.users.models.Roles;

public class UserResponseDTO {
    private long id;
    private String name;
    private String surname;
    private String userName;
    private Roles roles;


    public UserResponseDTO() {
    }

    public UserResponseDTO(long id, String name, String surname, String userName, Roles roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.roles = roles;
    }

//getters

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Roles getRoles() {
        return roles;
    }

    public String getSurname() {
        return surname;
    }

    public String getUserName() {
        return userName;
    }


//setters

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(long id) {
        this.id = id;
    }

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
