package com.develhope.spring.users.models;

import java.util.Collection;

public enum Roles {
    BUYER,
    ADMIN,
    SALESPERSON;

    public static boolean contains(Roles role, Collection<Roles> roles) {
        return roles.contains(role);
    }
}
