package com.develhope.spring.model.user;

public class User {

    private String name;

    private String surname;

    private long phoneNumber;

    private String email;

    private Roles roles;

    public User(String name, String surname, long phoneNumber, String email, Roles roles) throws EmptyParameterException, WrongEmailFormatException {
        if (name.isEmpty()) {
            throw new EmptyParameterException("Name can't be empty");
        } else {
            this.name = name;
        }
        if (surname.isEmpty()) {
            throw new EmptyParameterException("Surname can't be empty");
        } else {
            this.surname = surname;
        }
        if (!(email.contains("@"))) {
            throw new WrongEmailFormatException("Emails must be of the right format");
        } else {
            this.email = email;
        }
        this.roles = roles;
    }
}
