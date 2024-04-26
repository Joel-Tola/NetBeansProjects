/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mysqlconection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sljoe
 */
public class MySQLConection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sales", "root", "admin");
            System.out.println("OK!");

            // Statement myStmt = con.createStatement();

            // List<User> users=new ArrayList<User>();

            // while(rs.next()) {
            //     User user = new User();      
            //     user.setUserId(rs.getString("UserId"));
            //     user.setFName(rs.getString("FirstName"));

            //     users.add(user);
            // } 
            
        } catch (SQLException e) {
            
            System.out.println("SQL Error --->");
            System.out.println(e.getMessage()); // prints out the java error
            System.out.println(e.getSQLState()); //this will print out the estate
        }
        catch (ClassNotFoundException e) {
            System.out.println("Class error -- " + e.getMessage());
            System.out.println("Class Trace -- " + e.getStackTrace());
        }
    }
    
}
