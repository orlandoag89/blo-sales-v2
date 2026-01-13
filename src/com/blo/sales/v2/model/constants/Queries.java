package com.blo.sales.v2.model.constants;

public final class Queries {
    
    private Queries() { }
    
    /** users */
    public static final String SELECT_ONLY_ID_USERS = "SELECT id_user FROM users WHERE username = ? LIMIT 1";
    
    public static final String SELECT_USER_ROL = "SELECT rol, username, id_user FROM users WHERE username = ? AND password = ?";
    
    public static final String SELECT_ID_FROM_USER = "SELECT id_user, rol, username FROM users WHERE id_user = ? LIMIT 1";
    
    /** categories */
    public static final String INSERT_CATEGORY = "INSERT INTO categories(category, description) VALUES (?, ?)";
    
    public static final String SELECT_ALL_DATA_FROM_CATEGORIES = "SELECT id_category, category, description FROM categories";
    
    public static final String SELECT_CATEGORY = "SELECT id_category, category, description FROM categories WHERE id_category = ?";
    
    public static final String UPDATE_CATEGORY = "UPDATE categories SET category = ?, description = ? WHERE id_category = ?";
    
    /** products */
    public static final String INSERT_PRODUCT = "INSERT INTO stock(product, quantity, cost_of_sale, price, timestamp, is_kg, bar_code, fk_category) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    public static final String SELECT_ALL_PRODUCTS = "SELECT id_product, product, quantity, cost_of_sale, price, timestamp, is_kg, bar_code, fk_category FROM stock";
    
    public static final String UPDATE_PRODUCT = "UPDATE stock SET product = ?, quantity = ?, cost_of_sale = ?, timestamp = ?, bar_code = ?, price = ? WHERE id_product = ?";
    
    public static final String SELECT_ONE_PRODUCT = "SELECT id_product, product, quantity, cost_of_sale, price, timestamp, is_kg, bar_code, fk_category FROM stock WHERE id_product = ?";
    
    /** historia */
    public static final String INSERT_MOVEMENT = "INSERT INTO history(fk_product, fk_user, type, quantity, reason, timestamp) VALUES (?, ?, ?, ?, ?, ?)";
}
