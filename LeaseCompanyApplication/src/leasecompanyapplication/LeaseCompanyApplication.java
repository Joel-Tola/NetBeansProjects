/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package leasecompanyapplication;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import inpututilities.InputUtilities;
import mysqlconnection.MySQLConnection;

public class LeaseCompanyApplication {
    /**
     * The main method of the program, responsible for handling user interactions
     * through a menu system.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Create an instance of InputUtilities to handle user input
        InputUtilities inputHandler = new InputUtilities();

        // Create a MySQLConnection instance to handle database connections
        MySQLConnection dbHandler = MySQLConnection.getInstance();

        // Get a connection to the MySQL database
        Connection conn = dbHandler.getConnection();

        // Declare a variable to store the user's menu option choice
        int userOption;

        // Define the menu options that will be displayed to the user
        String menuOptions = "************************* MENU SYSTEM ****************************** \n" +
                "\t 1) List all data for a given table name \n" +
                "\t 2) Find all Customers information by initial letter name. \n" +
                "\t 3) Find clients and properties by date between a range of dates  \n" +
                "\t 4) Quote properties by monthly rent. \n" +
                "\t 5) Create a new Client. \n" +
                "\t 6) Update a existing Client by Client_No. \n" +
                "\t 7) Remove a existing Client by Client_No. \n" +
                "\t 8) QUIT \n" +
                "********************************************************************\n" +
                "Your Option: ";

        // Start a loop to repeatedly display the menu and process user input until the
        // user chooses to quit
        do {
            // Ask the user to input their choice based on the menu options
            userOption = inputHandler.askUserForInt(menuOptions);

            // Use a switch statement to determine the action based on the user's choice
            switch (userOption) {
                case 1:
                    // If the user chooses option 1, call the method to list all data for a given
                    // table name
                    handleListElementsForTable(inputHandler, conn);
                    break;
                case 2:
                    // If the user chooses option 2, call the method to find all Customers
                    // information by initial letter name
                    handleFindCustomersByInitial(inputHandler, conn);
                    break;
                case 3:
                    // If the user chooses option 3, call the method to find clients and properties
                    // by date between a range of dates
                    handleListClientsByRentalStartDate(inputHandler, conn);
                    break;
                case 4:
                    // If the user chooses option 4, call the method to quote properties by monthly
                    // rent
                    handlePropertiesByBudget(inputHandler, conn);
                    break;
                case 5:
                    // If the user chooses option 5, call the method to create a new Client
                    handleCreateNewClient(inputHandler, conn);
                    break;
                case 6:
                    // If the user chooses option 6, call the method to update an existing Client by
                    // Client_No
                    handleUpdateClientByClientNo(inputHandler, conn);
                    break;
                case 7:
                    // If the user chooses option 7, call the method to remove an existing Client by
                    // Client_No
                    handleRemoveClientByClientNo(inputHandler, conn);
                    break;
                case 8:
                    // If the user chooses option 8, display a message indicating the program is
                    // ending
                    System.out.println("Thank You.");
                    break;
                default:
                    // If the user enters an invalid option, display an error message
                    System.out.println("That is not a valid option");
                    break;
            }
        } while (userOption != 8); // Continue the loop until the user chooses to quit
    }

    /**
     * Handles finding customers by the initial letter of their name.
     * 
     * @param inputHandler InputUtilities object for handling user input.
     * @param conn         Database connection.
     */
    private static void handleFindCustomersByInitial(InputUtilities inputHandler, Connection conn) {
        // Ask the user to input the initial letter of the client's name
        String prompt = "Please enter the initial letter of the client's name to list their properties:";
        String userInput = inputHandler.askUserForText(prompt);

        // SQL query to retrieve properties of customers whose names start with the
        // provided initial letter
        String stringQuery = "SELECT p.Property_Address, p.`Monthly_rent`, c.Client_Name " +
                "FROM Property p " +
                "JOIN Client_Property cp ON p.PropertyNo = cp.PropertyNo " +
                "JOIN Client c ON cp.Client_No = c.Client_No " +
                "WHERE c.Client_Name LIKE ?";

        try (PreparedStatement pStmt = conn.prepareStatement(stringQuery)) {
            // Set the parameter in the prepared statement to filter by the provided initial
            // letter
            pStmt.setString(1, userInput + "%");

            // Display a message indicating the initial letter the user provided
            System.out.println("Client list that start with letter: " + userInput);

            // Execute the query and print the results
            printQueryResults(pStmt);
        } catch (SQLException e) {
            // Handle any SQL exceptions that may occur during query execution
            System.out.println("SQLException trying to fetch Customer by Initial letter: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            System.out
                    .println("Error trying to fetch Customer by Initial letter. Please contact con the administrator.");
        }
    }

    /**
     * Handles listing clients by rental start date.
     * 
     * @param inputHandler InputUtilities object for handling user input.
     * @param conn         Database connection.
     */
    private static void handleListClientsByRentalStartDate(InputUtilities inputHandler, Connection conn) {
        // Create a date formatter to parse user input
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        // Ask the user for the initial and final rent start dates
        String prompt = "Please enter the initial rent start date:";
        String userInput = inputHandler.askUserForDate(prompt);
        Date sqlStartDate = parseDate(userInput, dateFormatter);

        prompt = "Please enter the final rent start date:";
        userInput = inputHandler.askUserForDate(prompt);
        Date sqlFinishDate = parseDate(userInput, dateFormatter);

        // SQL query to retrieve clients and their rented properties within the
        // specified date range
        String stringQuery = "SELECT DISTINCT c.Client_Name, cp.`Rent_Start`, cp.`Rent_Finish`," +
                "p.`Property_Address`, p.`Monthly_rent` " +
                "FROM Client c " +
                "JOIN Client_Property cp ON c.Client_No = cp.Client_No " +
                "JOIN Property p on cp.`PropertyNo` = p.`PropertyNo` " +
                "WHERE cp.Rent_Start BETWEEN ? AND ?";

        try (PreparedStatement pStmt = conn.prepareStatement(stringQuery)) {
            // Set the parameters in the prepared statement to filter by the specified date
            // range
            pStmt.setDate(1, sqlStartDate);
            pStmt.setDate(2, sqlFinishDate);

            // Display a message indicating the date range the user provided
            System.out.println(
                    "Client and their rented properties list between: " + sqlStartDate + " and " + sqlFinishDate);

            // Execute the query and print the results
            printQueryResults(pStmt);
        } catch (SQLException e) {
            // Handle any SQL exceptions that may occur during query execution
            System.out.println("SQLException trying to fetch the list of clients: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            System.out.println(
                    "Error trying to fetch the list of clients by Date. Please contact con the administrator.");
        }
    }

    /**
     * Handles listing elements for a given table.
     * 
     * @param inputHandler InputUtilities object for handling user input.
     * @param conn         Database connection.
     */
    private static void handleListElementsForTable(InputUtilities inputHandler, Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            // Execute SQL query to retrieve table names
            ResultSet rs = stmt.executeQuery("Show Tables");
            ArrayList<String> tables = new ArrayList<>();
            while (rs.next()) {
                tables.add(rs.getString(1)); // Add each table name to the list
            }
            // Print available table options to the user
            System.out.println("Available options: " + tables);

            // Ask the user to input the name of the table they want to view
            String userInput = inputHandler.askUserForText("Please insert table name: ");
            if (tables.contains(userInput)) {
                // If the user input matches an existing table, construct and execute a query to
                // fetch all records from that table
                String stringQuery = "SELECT * FROM " + userInput;
                try (PreparedStatement pStmt = conn.prepareStatement(stringQuery)) {
                    // Print the table name and all records
                    System.out.println("All records of table: " + userInput);
                    printQueryResults(pStmt);
                }
            } else {
                // If the user input does not match any existing tables, inform the user
                System.out.println("Table does not exist.");
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions that may occur during query execution
            System.out.println("SQLException trying to fetch all the elements of the table: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            System.out.println(
                    "Error trying to fetch all the elements of the table. Please contact con the administrator.");
        }
    }

    /**
     * Handles quoting properties based on a budget for monthly rent.
     * 
     * @param inputHandler InputUtilities object for handling user input.
     * @param conn         Database connection.
     */
    private static void handlePropertiesByBudget(InputUtilities inputHandler, Connection conn) {
        // Prompt the user to enter their budget for monthly rent
        String prompt = "Please enter your budget for monthly rent:";
        double budget = inputHandler.askUserForInt(prompt); // Assuming there's a method to get double input

        // Prepare SQL queries for properties below and above the budget
        String queryBelow = "SELECT Property_Address AS Address, Monthly_rent AS Rent FROM Property " +
                "WHERE Monthly_rent < ? ORDER BY Monthly_rent DESC LIMIT 5";

        String queryAbove = "SELECT Property_Address AS Address, Monthly_rent AS Rent FROM Property " +
                "WHERE Monthly_rent > ? ORDER BY Monthly_rent ASC LIMIT 5";

        try {
            // Print properties below the budget
            System.out.println("Properties with lower rent:");
            PreparedStatement pStmtBelow = conn.prepareStatement(queryBelow);
            pStmtBelow.setDouble(1, budget);
            printQueryResults(pStmtBelow);

            // Print properties above the budget
            System.out.println("Properties with higher rent:");
            PreparedStatement pStmtAbove = conn.prepareStatement(queryAbove);
            pStmtAbove.setDouble(1, budget);
            printQueryResults(pStmtAbove);
        } catch (SQLException e) {
            // Handle any SQL exceptions that may occur during query execution
            System.out.println("Error processing properties by budget: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            System.out.println(
                    "Error trying to fetch the list of properties for a budget. Please contact con the administrator.");
        }
    }

    /**
     * Handles the creation of a new client.
     * 
     * @param inputHandler InputUtilities object for handling user input.
     * @param conn         Database connection.
     */
    private static void handleCreateNewClient(InputUtilities inputHandler, Connection conn) {
        // Prompt the user to enter the name of the new client
        String clientName = inputHandler.askUserForText("Enter client name:");

        // Call the createClient() method to add the new client to the database
        createClient(conn, clientName);

        // Display the updated client table after adding the new client
        displayClientTable(conn);
    }

    /**
     * Handles the removal of a client by client number.
     * 
     * @param inputHandler InputUtilities object for handling user input.
     * @param conn         Database connection.
     */
    private static void handleRemoveClientByClientNo(InputUtilities inputHandler, Connection conn) {
        // Display the client table to show all available clients
        displayClientTable(conn);

        // Prompt the user to enter the client number of the client to be removed
        String clientNo = inputHandler.askUserForTextWithNumbers("Enter client number to remove:");

        // Call the deleteClient() method to remove the client from the database
        deleteClient(conn, clientNo);

        // Display the updated client table after removing the client
        displayClientTable(conn);
    }

    /**
     * Handles the updating of a client by client number.
     * 
     * @param inputHandler InputUtilities object for handling user input.
     * @param conn         Database connection.
     */
    private static void handleUpdateClientByClientNo(InputUtilities inputHandler, Connection conn) {
        // Display the client table to show all available clients
        displayClientTable(conn);

        // Prompt the user to enter the client number of the client to be updated
        String clientNo = inputHandler.askUserForTextWithNumbers("Enter client number to update:");

        // Prompt the user to enter the new client name
        String newClientName = inputHandler.askUserForText("Enter new client name:");

        // Call the updateClient() method to update the client's name in the database
        updateClient(conn, clientNo, newClientName);

        // Display the updated client table after updating the client's name
        displayClientTable(conn);
    }

    /**
     * Displays the client table.
     * 
     * @param conn Database connection.
     */
    private static void displayClientTable(Connection conn) {
        // SQL query to select all columns from the Client table
        String query = "SELECT * FROM Client";

        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            // Call the printQueryResults() method to execute the query and print the results
            printQueryResults(pStmt);
        } catch (SQLException e) {
            // Handle any SQL exception that may occur during the execution of the query
            System.out.println("Error displaying client table: " + e.getMessage());
        }
    }

    /**
     * Prints the results of a SQL query.
     * 
     * @param pStmt PreparedStatement object containing the executed query.
     * @throws SQLException if a database access error occurs.
     */
    private static void printQueryResults(PreparedStatement pStmt) throws SQLException {
        try (ResultSet rs = pStmt.executeQuery()) {
            // Get the metadata of the ResultSet to extract information about the columns
            ResultSetMetaData metaData = rs.getMetaData();
            // Get the number of columns in the ResultSet
            int columnCount = metaData.getColumnCount();

            // Array to hold the maximum width required for each column
            int[] columnWidths = new int[columnCount];

            // Find the maximum width needed for each column
            for (int i = 1; i <= columnCount; i++) {
                // Store the length of the column label (header) in the array
                columnWidths[i - 1] = metaData.getColumnLabel(i).length(); // Start with the column header's length
            }

            // Collect data to print while finding max width needed for each column
            List<List<String>> rows = new ArrayList<>();
            while (rs.next()) {
                // Create a list to store the values of each row
                List<String> row = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    // Get the value of each column in the current row
                    String value = rs.getString(i);
                    // Add the value to the row list
                    row.add(value);
                    // Update the maximum width for the column if necessary
                    columnWidths[i - 1] = Math.max(columnWidths[i - 1], value.length());
                }
                // Add the row to the list of rows
                rows.add(row);
            }

            // Function to create the horizontal line
            String horizontalLine = "+";
            for (int width : columnWidths) {
                // Add dashes to create the horizontal line based on the maximum width of each column
                horizontalLine += String.join("", Collections.nCopies(width + 2, "-")) + "+";
            }

            // Print the top border
            System.out.println(horizontalLine);

            // Print the column headers with appropriate spacing and borders
            System.out.print("|");
            for (int i = 1; i <= columnCount; i++) {
                // Format each column header with left alignment and padding based on the maximum width
                System.out.print(String.format(" %-" + columnWidths[i - 1] + "s |", metaData.getColumnLabel(i)));
            }
            System.out.println();

            // Print the separator line under headers
            System.out.println(horizontalLine);

            // Print rows with vertical borders
            for (List<String> row : rows) {
                System.out.print("|");
                for (int i = 0; i < columnCount; i++) {
                    // Format each value with left alignment and padding based on the maximum width
                    System.out.print(String.format(" %-" + columnWidths[i] + "s |", row.get(i)));
                }
                System.out.println();
            }

            // Print the bottom border
            System.out.println(horizontalLine);
        } catch (SQLException e) {
            // Handle any SQL exception that may occur during the printing process
            System.out.println("SQLException displaying table : " + e.getMessage());
        }
    }

    /**
     * Parses a string to a Date object.
     * 
     * @param dateString    The string representing the date.
     * @param dateFormatter SimpleDateFormat object for parsing the date string.
     * @return A Date object parsed from the string.
     */
    private static Date parseDate(String dateString, SimpleDateFormat dateFormatter) {
        try {
            // Attempt to parse the input string into a java.util.Date object using the provided SimpleDateFormat
            java.util.Date parsedDate = dateFormatter.parse(dateString);
            // Convert the java.util.Date object to a java.sql.Date object (for database compatibility) and return it
            return new Date(parsedDate.getTime());
        } catch (Exception e) {
            // Handle any exceptions that occur during the parsing process
            System.out.println("Error parsing date: " + e.getMessage());
            return null; // Return null if parsing fails
        }
    }

    /**
     * Generates a new client number.
     * 
     * @param conn Database connection.
     * @return The newly generated client number.
     */
    private static String generateNewClientNo(Connection conn) {
        // SQL query to retrieve the last client number from the database
        String query = "SELECT Client_No FROM Client ORDER BY Client_No DESC LIMIT 1";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            // Check if the query returned any result
            if (rs.next()) {
                // Extract the last client number from the ResultSet
                String lastNo = rs.getString("Client_No");
                // Extract the numeric part of the client number and increment it by 1
                int numericPart = Integer.parseInt(lastNo.substring(2)) + 1;
                // Format the new client number with leading zeros and return it
                return String.format("CL%03d", numericPart);
            }
            // Return the default client number "CL001" if no clients exist in the database
            return "CL001";
        } catch (SQLException e) {
            // Handle any SQLException that occurs during database interaction
            System.out.println("Error fetching the last client number: " + e.getMessage());
            return null; // Return null if an error occurs
        }
    }

    /**
     * Creates a new client in the database.
     * 
     * @param conn       Database connection.
     * @param clientName The name of the new client.
     */
    private static void createClient(Connection conn, String clientName) {
        // Generate a new client number using the generateNewClientNo() method
        String clientNo = generateNewClientNo(conn);
        // Check if clientNo generation failed
        if (clientNo == null) {
            // Print error message if client number generation failed
            System.out.println("Failed to generate a new client number.");
            return; // Exit the method if client number generation failed
        }

        // SQL query to insert a new client into the Client table
        String query = "INSERT INTO Client (Client_No, Client_Name) VALUES (?, ?)";
        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            // Set values for the PreparedStatement parameters
            pStmt.setString(1, clientNo); // Client_No
            pStmt.setString(2, clientName); // Client_Name
            // Execute the SQL query to insert the new client
            pStmt.executeUpdate();
            // Print success message after adding the client
            System.out.println("Client added successfully: " + clientNo + " - " + clientName);
        } catch (SQLException e) {
            // Handle any SQLException that occurs during database interaction
            System.out.println("Error adding new client: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other exception that may occur
            System.out.println("Error trying to create a new Client. Please contact con the administrator.");
        }
    }

    /**
     * Updates an existing client's name in the database.
     * 
     * @param conn     Database connection.
     * @param clientNo The client number of the client to be updated.
     * @param newName  The new name for the client.
     */
    private static void updateClient(Connection conn, String clientNo, String newName) {
        // SQL query to update the client's name based on client number
        String query = "UPDATE Client SET Client_Name = ? WHERE Client_No = ?";
        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            // Set the new name and client number as parameters for the prepared statement
            pStmt.setString(1, newName); // New client name
            pStmt.setString(2, clientNo); // Client number to be updated
            // Execute the SQL update query
            int affectedRows = pStmt.executeUpdate();
            // Check if any rows were affected by the update operation
            if (affectedRows > 0) {
                // Print success message if client was updated successfully
                System.out.println("Client updated successfully: " + clientNo);
            } else {
                // Print message if no client was found with the provided client number
                System.out.println("No client found with number: " + clientNo);
            }
        } catch (SQLException e) {
            // Handle any SQLException that occurs during database interaction
            System.out.println("Error updating client: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other exception that may occur
            System.out.println("Error trying to update a new Client. Please contact con the administrator.");
        }
    }

    /**
     * Deletes a client from the database.
     * 
     * @param conn     Database connection.
     * @param clientNo The client number of the client to be deleted.
     */
    private static void deleteClient(Connection conn, String clientNo) {
        // SQL query to delete the client based on client number
        String query = "DELETE FROM Client WHERE Client_No = ?";
        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            // Set the client number as a parameter for the prepared statement
            pStmt.setString(1, clientNo); // Client number to be deleted
            // Execute the SQL delete query
            int affectedRows = pStmt.executeUpdate();
            // Check if any rows were affected by the delete operation
            if (affectedRows > 0) {
                // Print success message if client was deleted successfully
                System.out.println("Client deleted successfully: " + clientNo);
            } else {
                // Print message if no client was found with the provided client number
                System.out.println("No client found with number: " + clientNo);
            }
        } catch (SQLException e) {
            // Handle any SQLException that occurs during database interaction
            System.out.println("Error deleting client: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other exception that may occur
            System.out.println("Error trying to delete a new Client. Please contact con the administrator.");
        }
    }
}
