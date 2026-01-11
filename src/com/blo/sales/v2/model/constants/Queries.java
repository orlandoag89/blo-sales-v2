package com.blo.sales.v2.model.constants;

public final class Queries {
    
    private Queries() { }
    
    /** users */
    public static final String SELECT_ONLY_ID_USERS = "SELECT id_user FROM users WHERE username = ? LIMIT 1";
    
    public static final String SELECT_USER_ROL = "SELECT rol, username FROM users WHERE username = ? AND password = ?";
    
    /** categories */
    public static final String INSERT_CATEGORY = "INSERT INTO categories(category, description) VALUES (?, ?)";
    
    public static final String SELECT_ALL_DATA_FROM_CATEGORIES = "SELECT id_category, category, description FROM categories";
    
    public static final String SELECT_CATEGORY = "SELECT id_category, category, description FROM categories WHERE id_category = ?";
    
    public static final String UPDATE_CATEGORY = "UPDATE categories SET category = ?, description = ? WHERE id_category = ?";
    
}
