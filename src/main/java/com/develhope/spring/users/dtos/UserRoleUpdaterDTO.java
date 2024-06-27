package com.develhope.spring.users.dtos;

import com.develhope.spring.users.models.Roles;

public class UserRoleUpdaterDTO {

    private Roles role;

    public Roles getRole() {
        return role;
    }

    public UserRoleUpdaterDTO() {}

    public void setRole(Roles role) {
        this.role = role;
    }

    public UserRoleUpdaterDTO(Roles role) {
        this.role = role;
    }
}
