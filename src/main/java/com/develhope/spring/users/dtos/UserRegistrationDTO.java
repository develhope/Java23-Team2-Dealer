package com.develhope.spring.users.dtos;

import com.develhope.spring.configuration.validators.CustomAnnotation.PasswordMatches;
import com.develhope.spring.users.models.Roles;

@PasswordMatches
public class UserRegistrationDTO {

    private String name;
    private String surname;
    private String username;
    private String password;
    private String matchingPassword;
    private long phoneNumber;
    private String email;
    private Roles roles;


    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(String name, String surname, String username, String password, String matchingPassword,
                               long phoneNumber, String email, Roles roles) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.roles = roles;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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