package com.develhope.spring.users.models;

import com.develhope.spring.users.models.exceptions.EmptyParameterException;
import com.develhope.spring.users.models.exceptions.WrongEmailFormatException;
import jakarta.persistence.*;

import java.util.Collections;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;

    private long phoneNumber;
    @Column(unique = true, nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles roles;

    private void checkEmptyName(String name) throws EmptyParameterException {
        String validName = name.trim();
        if (validName.isEmpty()) {
            throw new EmptyParameterException("Name can't be empty");
        } else {
            this.name = validName;
        }
    }

    private void checkEmptySurname(String surname) throws EmptyParameterException {
        String validSurname = surname.trim();
        if (validSurname.isEmpty()) {
            throw new EmptyParameterException("Surname can't be empty");
        } else {
            this.surname = validSurname;
        }
    }

    private void checkEmailFormat(String email) throws WrongEmailFormatException {
        if (!(email.contains("@"))) {
            throw new WrongEmailFormatException("Emails must be of the right format");
        } else {
            this.email = email;
        }
    }

    public User(String name, String surname, long phoneNumber, String email, Roles roles) throws EmptyParameterException, WrongEmailFormatException {
        checkEmptyName(name);
        checkEmptySurname(surname);
        checkEmailFormat(email);
        this.roles = roles;
        this.phoneNumber = phoneNumber;
    }

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

    public void setEmail(String email) throws WrongEmailFormatException {
        checkEmailFormat(email);
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public void setName(String name) throws EmptyParameterException {
        checkEmptyName(name);
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSurname(String surname) throws EmptyParameterException {
        checkEmptySurname(surname);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
