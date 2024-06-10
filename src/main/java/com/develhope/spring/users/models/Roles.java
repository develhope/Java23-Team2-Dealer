package com.develhope.spring.users.models;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public enum Roles implements GrantedAuthority {
    BUYER,
    ADMIN,
    SALESPERSON;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
