package com.blo.sales.v2.model.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Datos de configuración
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/blo-sales-v2-dev";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    // Objeto de conexión
    private static Connection conexion = null;

    public static Connection getConnection() {
        try {
            if (conexion == null || conexion.isClosed()) {
                // Registrar el driver (opcional en versiones nuevas, pero seguro)
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Establecer la conexión
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexi\u00f3n establecida con \u00e9xito.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
        }
        return conexion;
    }
    
    /**
     * privene un guardado de cambios en la base de datos
     * @throws SQLException 
     */
    public static void disableAutocommit() throws SQLException {
        conexion.setAutoCommit(false);
    }
    
    /**
     * realiza commit
     * @throws SQLException 
     */
    public static void doCommit() throws SQLException {
        conexion.commit();
    }
    
    /**
     * Activa autocommit
     * @throws SQLException 
     */
    public static void enableAutocommit() throws SQLException {
        conexion.setAutoCommit(true);
    }
}
