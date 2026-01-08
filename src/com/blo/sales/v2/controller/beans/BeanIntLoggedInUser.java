package com.blo.sales.v2.controller.beans;

import com.blo.sales.v2.controller.beans.enums.RolesIntEnum;

public class BeanIntLoggedInUser {
    
    private String username;
    
    private RolesIntEnum role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RolesIntEnum getRole() {
        return role;
    }

    public void setRole(RolesIntEnum role) {
        this.role = role;
    }
}
