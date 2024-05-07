/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package leasecompanyapplication;

import java.sql.*;
import inpututilities.InputUtilities;
import mysqlconnection.MySQLConnection;

/**
 *
 * @author sljoe
 */
public class LeaseCompanyApplication {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        InputUtilities inputHandler = new InputUtilities();
        MySQLConnection dbHandler = MySQLConnection.getInstance();
        Connection conn = dbHandler.getConnection();
        int userOption;

        String menuOptions = "************************* MENU SYSTEM ****************************** \n" +
                        "\t 1) Find all Customers information. \n" +
                        "\t 2) Find a client who is paying above a certain rent amount \n" +
                        "\t 3) etc… \n" +
                        "\t 4) QUIT \n" +
                        "********************************************************************\n";
        
        do {
            System.out.println(menuOptions);
            
            userOption = inputHandler.askUserForInt("Your Option: ");

            // if (userOption == 4) {
            //     System.out.println("Good Bye. Thanks!");
            // } else if (userOption > 4) {
            //     System.out.println("That is not a valid option");
            // }

            switch (userOption) {
                case 1:
                    try {
                        Statement myStmt = conn.createStatement();

                        String query = "Select * from Customer where name = ";

                        ResultSet myResults = myStmt.executeQuery(query);

                        ResultSetMetaData metaData = myResults.getMetaData();
                        int columnCount = metaData.getColumnCount();

                        System.out.println(columnCount);

                        // Print column headers
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(String.format("%-20s", metaData.getColumnLabel(i)));
                        }
                        System.out.println(); // New line after headers

                        // Print records
                        while (myResults.next()) {
                            for (int i = 1; i <= columnCount; i++) {
                                String columnValue = myResults.getString(i);
                                System.out.print(String.format("%-20s", columnValue));
                            }
                            System.out.println(); // New line after each row
                        }

                    } catch (SQLException e) {
                        System.out.println("SQLException: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Exception: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Good Bye. Thanks!");
                    break;
            
                default:
                    System.out.println("That is not a valid option");
                    break;
            }

        } while (userOption != 4);

    }
    
}
