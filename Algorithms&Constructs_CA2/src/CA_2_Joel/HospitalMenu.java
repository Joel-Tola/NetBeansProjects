package CA_2_Joel;

import java.util.Scanner;

public class HospitalMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            // Display the menu
            System.out.println("\nPlease select an option from the following:");
            for (MenuOption option : MenuOption.values()) {
                System.out.println(option.getValue() + ". " + option.getDescription());
            }
            System.out.print("Your choice: ");

            // Validate user input
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number corresponding to the menu options.");
                continue;
            }

            MenuOption selectedOption = MenuOption.fromInt(choice);
            if (selectedOption == null) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            // Handle the selected menu option
            switch (selectedOption) {
                case SORT_LIST:
                    sortStaffList();
                    break;
                case SEARCH_LIST:
                    searchStaffList(scanner);
                    break;
                case ADD_STAFF_MEMBER:
                    addStaffMember(scanner);
                    break;
                case GENERATE_RANDOM_STAFF:
                    generateRandomStaff();
                    break;
                case EXIT:
                    System.out.println("Exiting the program. Goodbye!");
                    exit = true;
                    break;
            }
        }

        scanner.close();
    }

    // Placeholder method for sorting staff list
    private static void sortStaffList() {
        System.out.println("Sorting a dummy list of staff...");
        // Implement sorting logic here
    }

    // Placeholder method for searching staff list
    private static void searchStaffList(Scanner scanner) {
        System.out.println("Enter the name of the staff member to search:");
        String name = scanner.nextLine();
        System.out.println("Searching for " + name + " in the staff list...");
        // Implement searching logic here
    }

    // Placeholder method for adding a new staff member
    private static void addStaffMember(Scanner scanner) {
        System.out.println("Adding a new staff member...");

        // Input staff name
        System.out.print("Please input the Staff Name: ");
        String name = scanner.nextLine();

        // Select Manager Type
        System.out.println("Please select from the following Manager Types:");
        System.out.println("1. Chief Medical Officer");
        System.out.println("2. Nursing Manager");
        System.out.println("3. Administrative Manager");
        System.out.print("Your choice: ");
        int managerChoice = Integer.parseInt(scanner.nextLine());
        String managerType = getManagerType(managerChoice);

        // Select Department
        System.out.println("Please select the Department:");
        System.out.println("1. Emergency");
        System.out.println("2. Pediatrics");
        System.out.println("3. Cardiology");
        System.out.print("Your choice: ");
        int departmentChoice = Integer.parseInt(scanner.nextLine());
        String department = getDepartment(departmentChoice);

        if (managerType != null && department != null) {
            System.out.println("\"" + name + "\" has been added as \"" + managerType + "\" to \"" + department + "\" successfully!");
            // Implement logic to add the new staff member to the system
        } else {
            System.out.println("Invalid Manager Type or Department selected. Please try again.");
        }
    }

    // Placeholder method for generating random staff
    private static void generateRandomStaff() {
        System.out.println("Generating random staff...");
        // Implement random staff generation logic here
    }

    // Helper method to get manager type based on user input
    private static String getManagerType(int choice) {
        switch (choice) {
            case 1:
                return "Chief Medical Officer";
            case 2:
                return "Nursing Manager";
            case 3:
                return "Administrative Manager";
            default:
                return null;
        }
    }

    // Helper method to get department based on user input
    private static String getDepartment(int choice) {
        switch (choice) {
            case 1:
                return "Emergency";
            case 2:
                return "Pediatrics";
            case 3:
                return "Cardiology";
            default:
                return null;
        }
    }
}
