package com.develhope.spring.users.dtos;

public class UserRentalReturnerDto {

    private long id;

    private String name;

    private String surname;

    private String email;

    private long phoneNumber;


    public UserRentalReturnerDto(long id,
                                 String name,
                                 String surname,
                                 String email,
                                 long phoneNumber
    ) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public UserRentalReturnerDto(long id) {
        this.id = id;
    }

    public UserRentalReturnerDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
