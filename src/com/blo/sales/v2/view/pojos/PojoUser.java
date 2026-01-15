package com.blo.sales.v2.view.pojos;

public class PojoUser {
    
    private String username;
    
    private String password;

    public PojoUser(String username, String password) {
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
