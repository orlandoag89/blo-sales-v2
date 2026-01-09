package com.blo.sales.v2.model.entities;

import com.blo.sales.v2.model.entities.enums.RolesEntityEnum;

public class UserEntity {
    
    private int id_user;
    
    private String username;
    
    private String password;
    
    private RolesEntityEnum role;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolesEntityEnum getRole() {
        return role;
    }

    public void setRole(RolesEntityEnum role) {
        this.role = role;
    }
    
}
