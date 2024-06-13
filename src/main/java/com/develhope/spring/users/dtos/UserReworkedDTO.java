package com.develhope.spring.users.dtos;

import com.develhope.spring.users.models.Roles;

public class UserReworkedDTO {
    private long id;
    private String name;
    private String surname;
    private String username;
    private long phoneNumber;
    private String email;


    public  UserReworkedDTO(long id){
        this.id = id;
    }

    public UserReworkedDTO() {
    }

    public UserReworkedDTO(long id, String name, String surname, String username, long phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    //getters
    public long getId() {
        return id;
    }

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
    public void setId(long id) {
        this.id = id;
    }

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
