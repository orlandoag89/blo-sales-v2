package com.blo.sales.v2.controller.beans;

public class BeanIntUser {
 
    private String userName;
    
    private String password;

    public BeanIntUser(String userName, String password) {
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
