package com.blo.sales.v2.view.windows.beans;

public class BeanUser {
    
    private String username;
    
    private String password;

    public BeanUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
}
