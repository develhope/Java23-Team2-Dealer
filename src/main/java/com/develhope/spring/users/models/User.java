package com.develhope.spring.users.models;

import com.develhope.spring.configuration.validators.CustomAnnotation.ValidEmail;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.vehicles.models.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Surname is mandatory")
    @Column(nullable = false)
    private String surname;

    @Column(unique = true)
    private String username;

    private String password;

    private long phoneNumber;

    @ValidEmail
    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles roles;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> orders;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Rental> rentals;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rental soldRental;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order soldOrder;

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(long id, String name, String surname, String username, String password,
                long phoneNumber, String email, Roles roles, Rental soldRental, Order soldOrder) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.orders = new ArrayList<>();
        this.rentals = new ArrayList<>();
        this.email = email;
        this.roles = roles;
        this.soldRental = soldRental;
        this.soldOrder = soldOrder;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(roles);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Rental getSoldRental() {
        return soldRental;
    }

    public void setSoldRental(Rental soldRental) {
        this.soldRental = soldRental;
    }

    public Order getSoldOrder() {
        return soldOrder;
    }

    public void setSoldOrder(Order soldOrder) {
        this.soldOrder = soldOrder;
    }
}