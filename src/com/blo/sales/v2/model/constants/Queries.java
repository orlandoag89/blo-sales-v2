package com.blo.sales.v2.model.constants;

public class Queries {
    
    public static final String SELECT_ONLY_ID_USERS = "SELECT id_user FROM users WHERE username = ? LIMIT 1";
    
    public static final String SELECT_USER_ROL = "SELECT rol, username FROM users WHERE username = ? AND password = ?";
    
    public static final String INSERT_CATEGORY = "INSERT INTO categories(category, description) VALUES (?, ?)";
    
}
