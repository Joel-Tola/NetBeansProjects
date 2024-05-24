package mainproject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import inpututilities.InputUtilities;
import mysqlhandler.MySQLHandler;

public class MainProject {
    public static void main(String[] args) {

        InputUtilities inputHandler = new InputUtilities();
        MySQLHandler dbHandler = new MySQLHandler();
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

            switch (userOption) {
                case 1:
                    try {

                        String query = "Select * from Customer";

                        //DBHandler code
                        ResultSet myResults = dbHandler.executeQuery(query);
                        ResultSetMetaData metaData = myResults.getMetaData();
                        int columnCount = metaData.getColumnCount();

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
                    
                    // CLosing DB connection
                    dbHandler.closeConnection();
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
