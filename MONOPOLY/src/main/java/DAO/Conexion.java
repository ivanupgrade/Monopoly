package DAO;

import java.sql.Connection;

public class Conexion {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/monopoly";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        try (
                Connection connection = java.sql.DriverManager.getConnection(DB_URL, USER, PASS)
        ) {
            return connection;

        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }

        return null;
    }
}
