package com.develhope.spring.users.dtos;

import com.develhope.spring.users.models.Roles;

public class UserSavedDTO {
    private long id;
    private String name;
    private String surname;
    private String username;
    private Roles roles;


    public UserSavedDTO(long id){}

    public UserSavedDTO(){}

    public UserSavedDTO(long id, String name, String surname, String username, Roles roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
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

    public String getUsername() {
        return username;
    }


//setters

    public void setUsername(String username) {
        this.username = username;
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
