package com.develhope.spring.users.models;

import org.springframework.security.core.GrantedAuthority;


public enum Roles implements GrantedAuthority {
    BUYER,
    ADMIN,
    SALESPERSON;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
