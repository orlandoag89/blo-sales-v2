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
    
    public static final String SELECT_PRODUCT_BY_BAR_CODE = "SELECT id_product, product, quantity, cost_of_sale, price, timestamp, is_kg, bar_code, fk_category FROM stock WHERE bar_code = ?";
    
    /** historial */
    public static final String INSERT_MOVEMENT = "INSERT INTO history(fk_product, fk_user, type, quantity, reason, timestamp) VALUES (?, ?, ?, ?, ?, ?)";
    
    /** ventas */
    public static final String INSERT_SALE = "INSERT INTO sales(total, sale_status, timestamp) VALUES (?, ?, ?)";
    
    public static final String SELECT_SALE_BY_STATUS = "SELECT id_sale, total, sale_status, timestamp FROM sales WHERE sale_status = ?";
    
    public static final String SET_ON_CASHBOX = "UPDATE sales SET sale_status = 'ON_CASHBOX' WHERE id_sale = ?";
    
    /** ventas product */
    public static final String INSERT_SALE_PRODUCT = "INSERT INTO sale_product(fk_sale, fk_product, quantity_sale, total_on_sale, product_total_on_sale, timestamp) VALUES (?, ?, ?, ?, ?, ?)";
    
    public static final String SELECT_SALES_DETAIL = "SELECT s.id_sale, st.id_product, st.product, ps.quantity_sale, st.price, st.cost_of_sale, ps.total_on_sale, ps.timestamp, st.is_kg, ps.product_total_on_sale FROM sales s INNER JOIN sale_product ps ON s.id_sale = ps.fk_sale INNER JOIN stock st ON ps.fk_product = st.id_product";
    
    public static final String SELECT_SALE_CLOSED = "SELECT s.id_sale, st.id_product, st.product, ps.quantity_sale, st.price, st.cost_of_sale, ps.total_on_sale, ps.timestamp, ps.product_total_on_sale FROM sales s INNER JOIN sale_product ps ON s.id_sale = ps.fk_sale INNER JOIN stock st ON ps.fk_product = st.id_product WHERE s.sale_status = ?";
    /** cajas de dinero */
    public static final String INSERT_CASHBOX = "INSERT INTO cashboxes(fk_user, timestamp, status, amount) VALUES (?, ?, ?, ?)";
    
    public static final String UPDATE_CASHBOX = "UPDATE cashboxes SET timestamp = ?, status = ?, amount = ? WHERE id_cashbox = ?";
    
    //public static final String SELECT_OPEN_CASHBOX = "SELECT id_cashbox, fk_user, timestamp, status, amount FROM cashboxes WHERE status = ? LIMIT 1";
    public static final String SELECT_OPEN_CASHBOX = "SELECT id_cashbox, fk_user, timestamp, status, amount, username FROM cashboxes c INNER JOIN users u WHERE c.fk_user = u.id_user AND status = ? LIMIT 1";
    
    public static final String SELECT_ALL_CASHBOXES_AND_USERS = "SELECT id_cashbox, fk_user, timestamp, status, amount, username FROM cashboxes c INNER JOIN users u WHERE c.fk_user = u.id_user";
    
    /** deudores */
    public static final String INSERT_DEBTOR = "INSERT INTO debtors(name, debt, payments) VALUES (?, ?, ?)";
    
    public static final String SELECT_DEBTOR_BY_ID = "SELECT id_debtor, name, debt, payments FROM debtors WHERE id_debtor = ?";
    
    public static final String SELECT_DEBTORS = "SELECT id_debtor, name, debt, payments FROM debtors";
    
    public static final String UPDATE_DEBTOR = "UPDATE debtors SET name = ?, debt = ?, payments = ? WHERE id_debtor = ?";
    
    public static final String DEBTORS_DETAILS = "SELECT d.id_debtor, d.name, d.debt, d.payments, st.product, sp.quantity_sale, sp.total_on_sale, sp.timestamp FROM debtors d INNER JOIN debtor_sale ds ON ds.fk_debtor = d.id_debtor INNER JOIN sales s ON ds.fk_sale = s.id_sale INNER JOIN sale_product sp ON sp.fk_sale = s.id_sale INNER JOIN stock st ON st.id_product = sp.fk_product";
    
    public static final String DEBTOR_DELETE = "DELETE FROM debtors WHERE id_debtor = ?";
    
    /** deudores ventas */
    public static final String INSERT_DEBTOR_SALE = "INSERT INTO debtor_sale(fk_debtor, fk_sale, timestamp) VALUES (?, ?, ?)";

    public static final String DELETE_DEBTOR_SALE = "DELETE FROM debtor_sale WHERE debtor_sale.fk_debtor = ?";
    /** activos pasivos */
    public static final String INSERT_ACTIVE_COSTS = "INSERT INTO actives_costs(concept, amount, type, complete) VALUES (?, ?, ?, ?)";
    
    /** relacion activos pasivos con caja de dinero */
    public static final String INSERT_CASHBOXES_ACTIVE_COSTS = "INSERT INTO cashboxes_actives_costs(fk_cashbox, fk_actives_costs, timestamp) VALUES (?, ?, ?)";
    
    public static final String SELECT_CASHBOXES_DATA = "SELECT c.id_cashbox, c.status, c.amount, ac.concept, ac.type, cac.timestamp, ac.amount AS concept_amount FROM cashboxes c INNER JOIN cashboxes_actives_costs cac ON c.id_cashbox = cac.fk_cashbox INNER JOIN actives_costs ac ON cac.fk_actives_costs = ac.id_active_cost";
    
    /** notas */
    public static final String INSERT_NOTES = "INSERT INTO notes(note, timestamp, type_note, fk_user) VALUES (?, ?, ?, ?)";
    
    public static final String GET_NOTES_BY_ID_USER = "SELECT id_note, note, timestamp, type_note, fk_user FROM notes WHERE fk_user = ?";
    
    public static final String DELETE_NOTE = "DELETE FROM notes WHERE notes.id_note = ?";
    
    public static final String UPDATE_NOTE = "UPDATE notes SET notes.note = ? wHERE notes.id_note = ?";
}
