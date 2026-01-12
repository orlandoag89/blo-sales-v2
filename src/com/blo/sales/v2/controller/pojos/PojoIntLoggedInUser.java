package com.blo.sales.v2.controller.pojos;

import com.blo.sales.v2.controller.pojos.enums.RolesIntEnum;

public class PojoIntLoggedInUser {
    
    private int idUser;
    
    private String username;
    
    private RolesIntEnum role;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

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
