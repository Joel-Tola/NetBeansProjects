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
                "\t 1) List all properties rented out by all clients whose name begins with user prompt \n" +
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
                        String prompt = "Please enter the initial letter of the client's name to list their properties:";
                        String userInput = inputHandler.askUserForText(prompt);
                        String query = "SELECT p.Property_Address, p.`Monthly_rent`, c.Client_Name\r\n" + //
                                "FROM Property p\r\n" + //
                                "JOIN Client_Property cp ON p.PropertyNo = cp.PropertyNo\r\n" + //
                                "JOIN Client c ON cp.Client_No = c.Client_No\r\n" + //
                                "WHERE c.Client_Name LIKE '" + userInput + "%'";

                        // DBHandler code
                        ResultSet myResults = dbHandler.executeQuery(query);

                        // get metaDate from Result Set
                        ResultSetMetaData metaData = myResults.getMetaData();
                        int columnCount = metaData.getColumnCount();

                        System.out.println();
                        System.out.println();

                        // Print column headers
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(String.format("%-30s", metaData.getColumnLabel(i)));
                        }

                        System.out.println(); // New line after headers


                        

                        // Print records
                        while (myResults.next()) {
                            for (int i = 1; i <= columnCount; i++) {
                                String columnValue = myResults.getString(i);
                                System.out.print(String.format("%-30s", columnValue));
                            }
                            System.out.println(); // New line after each row
                        }

                        System.out.println();
                        System.out.println();

                    } catch (SQLException e) {
                        System.out.println("SQLException: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Exception: " + e.getMessage());
                    }

                    dbHandler.closeConnection();
                    break;
                case 2:

                    try {
                        String prompt = "Please enter the initial letter of the client's name to list their properties:";
                        String userInput = inputHandler.askUserForText(prompt);
                        String query = "SELECT p.Property_Address, p.`Monthly_rent`, c.Client_Name\r\n" + //
                                "FROM Property p\r\n" + //
                                "JOIN Client_Property cp ON p.PropertyNo = cp.PropertyNo\r\n" + //
                                "JOIN Client c ON cp.Client_No = c.Client_No\r\n" + //
                                "WHERE c.Client_Name LIKE '" + userInput + "%'";

                        // DBHandler code
                        ResultSet myResults = dbHandler.executeQuery(query);

                        // get metaDate from Result Set
                        ResultSetMetaData metaData = myResults.getMetaData();
                        int columnCount = metaData.getColumnCount();

                        System.out.println();
                        System.out.println();

                        // Print column headers
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(String.format("%-30s", metaData.getColumnLabel(i)));
                        }

                        System.out.println(); // New line after headers

                        // Print records
                        while (myResults.next()) {
                            for (int i = 1; i <= columnCount; i++) {
                                String columnValue = myResults.getString(i);
                                System.out.print(String.format("%-30s", columnValue));
                            }
                            System.out.println(); // New line after each row
                        }

                        System.out.println();
                        System.out.println();

                    } catch (SQLException e) {
                        System.out.println("SQLException: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Exception: " + e.getMessage());
                    }

                    dbHandler.closeConnection();

                    break;
                default:
                    System.out.println("That is not a valid option");
                    break;
            }

        } while (userOption != 4);
    }
}
