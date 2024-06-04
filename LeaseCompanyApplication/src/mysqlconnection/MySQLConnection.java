/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mysqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sljoe
 */
public class MySQLConnection {

    private static MySQLConnection instance = null;
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/RentalDB";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    private Connection connection;


    private MySQLConnection() {
        try {
            // Attempt to establish the database connection
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established successfully.");
        } catch (SQLException e) {
            System.out.println("Error establishing the connection: " + e.getMessage());
            // Handle other cleanup here if necessary
        }
    }

    // Public method to get the instance of the class
    public static synchronized MySQLConnection getInstance() {
        if (instance == null) {
            instance = new MySQLConnection();
        }
        return instance;
    }

    // Method to get the database connection
    public Connection getConnection() {
        return this.connection;
    }

    // Method to close the database connection
    public void closeConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                System.out.println("Error closing the connection: " + e.getMessage());
            }
        }
    }
}
