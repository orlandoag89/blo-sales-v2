package com.blo.sales.v2.model.constants;

public final class BloSalesV2Queries {
    
    private BloSalesV2Queries() { }
    
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
    
    /** historial */
    public static final String INSERT_MOVEMENT = "INSERT INTO history(fk_product, fk_user, type, quantity, reason, timestamp) VALUES (?, ?, ?, ?, ?, ?)";
    
    /** ventas */
    public static final String INSERT_SALE = "INSERT INTO sales(total, sale_status, timestamp) VALUES (?, ?, ?)";
    
    /** ventas product */
    public static final String INSERT_SALE_PRODUCT = "INSERT INTO sale_product(fk_sale, fk_product, quantity_sale, total_on_sale, timestamp) VALUES (?, ?, ?, ?, ?)";
    
    /** cajas de dinero */
    public static final String INSERT_CASHBOX = "INSERT INTO cashboxes(fk_user, timestamp, status, amount) VALUES (?, ?, ?, ?)";
    
    public static final String UPDATE_CASHBOX = "UPDATE cashboxes SET timestamp = ?, status = ?, amount = ? WHERE id_cashbox = ?";
    
    public static final String SELECT_OPEN_CASHBOX = "SELECT id_cashbox, fk_user, timestamp, status, amount FROM cashboxes WHERE status = ? LIMIT 1";
    
    /** deudores */
    public static final String INSERT_DEBTOR = "INSERT INTO debtors(name, debt, payments) VALUES (?, ?, ?)";
    
    public static final String SELECT_DEBTOR_BY_ID = "SELECT id_debtor, name, debt, payments FROM debtors WHERE id_debtor = ?";
    
    public static final String SELECT_DEBTORS = "SELECT id_debtor, name, debt, payments FROM debtors";
    
    public static final String UPDATE_DEBTOR = "UPDATE debtors SET name = ?, debt = ?, payments = ? WHERE id_debtor = ?";
    
    /** deudores ventas */
    public static final String INSERT_DEBTOR_SALE = "INSERT INTO debtor_sale(fk_debtor, fk_sale, timestamp) VALUES (?, ?, ?)";

}
