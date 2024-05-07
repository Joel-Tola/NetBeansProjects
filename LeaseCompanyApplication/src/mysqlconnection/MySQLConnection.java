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
    private static final String URL = "jdbc:mysql://localhost:3306/sales";
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

    // public static void main(String[] args) {

    //     try {
    //         Class.forName("com.mysql.cj.jdbc.Driver");
    //         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sales", "root", "admin");
    //         System.out.println("OK!");

    //         // Statement myStmt = con.createStatement();

    //         // List<User> users=new ArrayList<User>();

    //         // while(rs.next()) {
    //         //     User user = new User();      
    //         //     user.setUserId(rs.getString("UserId"));
    //         //     user.setFName(rs.getString("FirstName"));

    //         //     users.add(user);
    //         // } 
            
    //     } catch (SQLException e) {
            
    //         System.out.println("SQL Error --->");
    //         System.out.println(e.getMessage()); // prints out the java error
    //         System.out.println(e.getSQLState()); //this will print out the estate
    //     }
    //     catch (ClassNotFoundException e) {
    //         System.out.println("Class error -- " + e.getMessage());
    //         System.out.println("Class Trace -- " + e.getStackTrace());
    //     }
    // }
    
}
