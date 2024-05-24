package mysqlhandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLHandler {

    private static MySQLHandler instance = null;
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/sales";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    private Connection connection;

    private void startConnection() {
        try {
            // Attempt to establish the database connection
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established successfully.");
        } catch (SQLException e) {
            System.out.println("Error establishing the connection: " + e.getMessage());
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement myStmt;

        startConnection();
        myStmt = connection.createStatement();
        ResultSet myResults = myStmt.executeQuery(query);
        return myResults;
    }

    public void closeConnection() {
        try {
            this.connection.close();
            System.out.println("Connection closed successfully.");
        } catch (SQLException e) {
            System.out.println("Error closing the connection: " + e.getMessage());
        }
    }
}
