package com.blo.sales.v2.controller.pojos;

public class PojoIntUser {
 
    private String userName;
    
    private String password;

    public PojoIntUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    
}
