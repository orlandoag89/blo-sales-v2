package com.blo.sales.v2.controller.pojos;

import com.blo.sales.v2.controller.pojos.enums.RolesIntEnum;

public class PojoIntUser {
 
    private String userName;
    
    private String password;
    
    private RolesIntEnum rol;
    
    private long idUser;
    
    public PojoIntUser() { }

    public PojoIntUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setUserName(String username) {
        this.userName = username;
    }
    
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public RolesIntEnum getRol() {
        return rol;
    }

    public void setRol(RolesIntEnum rol) {
        this.rol = rol;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
    
}
