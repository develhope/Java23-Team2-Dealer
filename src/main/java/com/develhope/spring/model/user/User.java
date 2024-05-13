package com.develhope.spring.model.user;

public class User {

    private String name;

    private String surname;

    private long phoneNumber;

    private String email;

    private Roles roles;

    private void checkEmptyName(String name) throws EmptyParameterException {
        if (name.isEmpty()) {
            throw new EmptyParameterException("Name can't be empty");
        } else {
            this.name = name;
        }
    }

    private void checkEmptySurname(String surname) throws EmptyParameterException {
        if (surname.isEmpty()) {
            throw new EmptyParameterException("Surname can't be empty");
        } else {
            this.surname = surname;
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
}
