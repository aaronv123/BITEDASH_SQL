package dao;
import  java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Aaron Penetrante
 */
public class ConnectionProvider {
        
    // Database configuration
    private static final String URL = "jdbc:mysql://localhost:3306/bitedashdata"; 
    private static final String USER = "root"; // replace with your MySQL username
    private static final String PASSWORD = "admin@123"; // replace with your MySQL password

    // Method to get connection
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connection successful!");
            return conn;
        } catch (SQLException e) {
            System.out.println("❌ Connection failed!");
            e.printStackTrace();
            return null;
        }
    }

    // Main method to test connection
    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

