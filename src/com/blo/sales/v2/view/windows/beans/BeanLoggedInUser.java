package com.blo.sales.v2.view.windows.beans;

import com.blo.sales.v2.view.windows.beans.enums.RolesEnum;

public class BeanLoggedInUser {
    
    private RolesEnum role;
    
    private String username;

    public RolesEnum getRole() {
        return role;
    }

    public void setRole(RolesEnum role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
