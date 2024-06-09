package com.develhope.spring.users.models;

import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.Rental;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Surname is mandatory")
    @Column(nullable = false)
    private String surname;

    private long phoneNumber;

    @Email(message = "Wrong email format")
    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles roles;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Rental> rentals;

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(long id, String name, String surname, long phoneNumber, String email, Roles roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roles = roles;
        this.phoneNumber = phoneNumber;
        this.orders = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
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

    public void setSurname(String surname) {
        this.surname = surname;
    }

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

    public long getId() {
        return id;
    }
}
