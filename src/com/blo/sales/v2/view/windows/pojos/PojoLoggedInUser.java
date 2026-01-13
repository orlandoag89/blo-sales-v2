package com.blo.sales.v2.view.windows.pojos;

import com.blo.sales.v2.view.windows.pojos.enums.RolesEnum;

public class PojoLoggedInUser {
    
    private long idUser;
    
    private RolesEnum role;
    
    private String username;

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

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
