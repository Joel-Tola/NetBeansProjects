/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CA_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nicol
 */
public class EmployeeSystem {

    /**
     * @param args the command line arguments
     */
    static List<Employee> employees;

    enum DepartmentType {
        FINANCE,
        ENGINEERING,
        HR,
        BACK
    }

    enum StaffType {
        JR_STAFF,
        SR_STAFF,
        CONSULTANT,
        BACK
    }

    enum ManagerType {
        HEAD_MANAGER,
        ASSISTANT_MANAGER,
        TEAM_LEAD,
        BACK
    }

    enum EmployeeType {
        STAFF,
        MANAGER,
        BACK
    }

    enum MainMenu {
        SORT,
        SEARCH,
        ADD_EMPLOYEE,
        GENERATE_RANDOM_EMPLOYEE,
        EXIT
    }

    public static void main(String[] args) {

        Scanner myScan = new Scanner(System.in);
        boolean fileLoaded = false; // with a boolean set as false because the file hasnt been read yet
        String filePath = "Applicants_Form.txt"; // thiss will work as the default file to read for the system
        while (!fileLoaded) {
            System.out.println("Please enter the filename to read:");
            filePath = myScan.nextLine();
            try (BufferedReader buffReader = new BufferedReader(new FileReader(filePath))) {
                System.out.println("File read successfully!");
                fileLoaded = true;
            } catch (FileNotFoundException e) {
                System.out.println("File not found, try again.");
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file: " + e.getMessage());
            }
        }

        employees = new ArrayList<>();

        // Read file and fill employees list
        try (BufferedReader buffReader = new BufferedReader(new FileReader(filePath))) {
            int index = 0;
            String line;
            while ((line = buffReader.readLine()) != null) {
                String[] lineName = line.split(",");

                employees.add(new Employee(lineName[0], null));

                index++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the data: " + e.getMessage());
        }

        // Print empoyees list
        for (Employee employee : employees) {
            System.out.println(employee.name);
        }

        int intOption = 0;
        do {
            displayMenu();

            // process user input
            while (!myScan.hasNextInt()) {
                System.out.println("Please select integers only from the menu Options");
                myScan.next();
            }

            // now we can process the request
            int option = myScan.nextInt();
            myScan.nextLine();
            MainMenu selectOption = null;
            // check the # of options in enums
            // this will dinamically check the user input to be a min value 1 or hight
            // wathever the numb of enums is
            if (option < 1 || option > MainMenu.values().length) {
                System.out.println("PLease select from all the available options");

            } else {
                intOption = option;
                selectOption = MainMenu.values()[option - 1];

                switch (selectOption) {
                    case SORT:
                        // sortEmployees(employeeNames);
                        System.out.println("SORTED SELECTED");
                        break;
                    case SEARCH:
                        // searchEmployees(employeeNames, myScan);
                        System.out.println("SEARCH SELECTED");
                        break;
                    case ADD_EMPLOYEE:
                        addEmployeeMenu(employees, myScan);
                        System.out.println("ADD_EMPLOYEE SELECTED");
                        break;
                    case GENERATE_RANDOM_EMPLOYEE:
                        // generateRandomEmp(employeeNames);
                        System.out.println("GENERATE_RANDOM_EMPLOYEE SELECTED");
                        break;
                    case EXIT:
                        System.out.println("You have exited the program!");
                        break;
                    default:
                        System.out.println("That option does not exist. Please try again.");
                }
            }
        } while (intOption != 5);
        myScan.close();
        return;
    }

    private static void addEmployeeMenu(List<Employee> employees, Scanner myScan) {

        // Asking for Employee Name
        boolean isValidName = false;
        String name = "";

        do {
            System.out.println("Please insert employee Name: ");

            String input = myScan.nextLine();

            // validating input contains numbers
            if (!input.matches(".*\\d.*") && !input.contentEquals("")) {
                isValidName = true;
                name = input;
            } else {
                System.out.println("ERROR: Invalid input. Please type just letters");
            }
        } while (!isValidName);

        // Asking for Employee type
        int option = 0;
        do {
            displayTypeEmployeeMenu();

            // process user input
            while (!myScan.hasNextInt()) {
                System.out.println("Please select integers only from the menu Options");
                myScan.next();
            }

            // now we can process the request
            option = myScan.nextInt();
            EmployeeType selectOption = null;
            // check the # of options in enums
            // this will dinamically check the user input to be a min value 1 or hight
            // wathever the numb of enums is
            if (option < 1 || option > EmployeeType.values().length) {
                System.out.println("PLease select from all the available options");

            } else {
                selectOption = EmployeeType.values()[option - 1];

                switch (selectOption) {
                    case STAFF:
                        // sortEmployees(employeeNames);
                        System.out.println("STAFF SELECTED");
                        addStaffEmployee(name, selectOption, myScan);
                        break;
                    case MANAGER:
                        // searchEmployees(employeeNames, myScan);
                        System.out.println("MANAGER SELECTED");

                        addManagerEmployee(name, selectOption, myScan);
                        break;
                    case BACK:
                        break;
                    default:
                        System.out.println("That option does not exist. Please try again.");
                }
            }
        } while (option != 3);
    }

    private static void addManagerEmployee(String name, EmployeeType selectOption, Scanner myScan) {
        int option = 0;
        do {
            displayManagerTypeMenu();

            // process user input
            while (!myScan.hasNextInt()) {
                System.out.println("Please select integers only from the menu Options");
                myScan.next();
            }

            // now we can process the request
            option = myScan.nextInt();
            myScan.nextLine();
            ManagerType managerType = null;
            // check the # of options in enums
            // this will dinamically check the user input to be a min value 1 or hight
            // wathever the numb of enums is
            if (option < 1 || option > ManagerType.values().length) {
                System.out.println("PLease select from all the available options");

            } else {
                managerType = ManagerType.values()[option - 1];

                switch (managerType) {
                    case HEAD_MANAGER:
                        // SubMenu select Department
                        selectDepartmentMenu(name, "manager", myScan);
                        System.out.println("HEAD_MANAGER selected");
                        break;
                    case ASSISTANT_MANAGER:
                        System.out.println("ASSISTANT_MANAGER selected");
                        selectDepartmentMenu(name, "manager", myScan);
                        break;
                    case TEAM_LEAD:
                        System.out.println("TEAM_LEAD selected");
                        selectDepartmentMenu(name, "manager", myScan);
                        break;
                    case BACK:
                        break;
                    default:
                        System.out.println("That option does not exist. Please try again.");
                }
            }
        } while (option != 4);
    }

    private static void displayManagerTypeMenu() {
        System.out.println("\nPlease select an option (number) from the following:");
        int index = 1;

        for (ManagerType option : ManagerType.values()) {
            System.out.println(index + ": " + option.name());
            index++;
        }
        System.out.print("Option: ");
    }

    private static void addStaffEmployee(String name, EmployeeType employeeType, Scanner myScan) {
        // Asking for Employee type
        int option = 0;
        do {
            displayStaffTypeEmployeeMenu();

            // process user input
            while (!myScan.hasNextInt()) {
                System.out.println("Please select integers only from the menu Options");
                myScan.next();
            }

            // now we can process the request
            option = myScan.nextInt();
            myScan.nextLine();
            StaffType selectOption = null;
            // check the # of options in enums
            // this will dinamically check the user input to be a min value 1 or hight
            // wathever the numb of enums is
            if (option < 1 || option > StaffType.values().length) {
                System.out.println("PLease select from all the available options");

            } else {
                selectOption = StaffType.values()[option - 1];

                switch (selectOption) {
                    case JR_STAFF:
                        // SubMenu select Department
                        selectDepartmentMenu(name, "employee", myScan);
                        System.out.println("JR_STAFF selected");
                        break;
                    case SR_STAFF:
                        System.out.println("SR_STAFF selected");
                        selectDepartmentMenu(name, "employee", myScan);
                        break;
                    case CONSULTANT:
                        System.out.println("CONSULTANT selected");
                        selectDepartmentMenu(name, "employee", myScan);
                        break;
                    case BACK:
                        break;
                    default:
                        System.out.println("That option does not exist. Please try again.");
                }
            }
        } while (option != 4);
    }

    private static void selectDepartmentMenu(String name, String employeeType, Scanner myScan) {

        int option = 0;
        do {
            displayDepartmentOptions();
            // process user input
            while (!myScan.hasNextInt()) {
                System.out.println("Please select integers only from the menu Options");
                myScan.next();
            }

            // now we can process the request
            option = myScan.nextInt();
            myScan.nextLine();
            DepartmentType selectOption = null;
            // check the # of options in enums
            // this will dinamically check the user input to be a min value 1 or hight
            // wathever the numb of enums is
            if (option < 1 || option > StaffType.values().length) {
                System.out.println("PLease select from all the available options");

            } else {
                selectOption = DepartmentType.values()[option - 1];
                Employee newEmployee;
                Department department;
                switch (selectOption) {
                    case ENGINEERING:
                        System.out.println("ENGINEERING selected");
                        // TODO Consult with Charly!!!
                        department = new Engineering("Engineering");
                        newEmployee = createEmployee(employeeType, name, department);
                        employees.add(newEmployee);
                        // Just for testing. Printin all employees to confirm employee creation.
                        printAllEmployees();
                        break;
                    case FINANCE:
                        System.out.println("FINANCE selected");
                        department = new Finance("Finance");
                        newEmployee = createEmployee(employeeType, name, department);

                        employees.add(newEmployee);
                        // Just for testing. Printin all employees to confirm employee creation.
                        printAllEmployees();
                        break;
                    case HR:
                        System.out.println("HR selected");
                        department = new HR("HR");
                        newEmployee = createEmployee(employeeType, name, department);

                        employees.add(newEmployee);
                        // Just for testing. Printin all employees to confirm employee creation.
                        printAllEmployees();
                        break;
                    case BACK:
                        break;
                    default:
                        System.out.println("That option does not exist. Please try again.");
                }
            }
        } while (option != 4);
    }

    private static Employee createEmployee(String employeeType, String name, Department department) {
        if (employeeType.equalsIgnoreCase("Staff")) {
            return new Staff(name, department);
        } else {
            return new Manager(name, department);
        }

    }

    private static void printAllEmployees() {
        for (Employee employee : employees) {
            // Get the employee type and department type as the class names
            String employeeType = employee.getClass().getSimpleName();
            String departmentType = (employee.department != null) ? employee.department.getClass().getSimpleName() : "No Department";
    
            System.out.println(employee.name + " | Type: " + employeeType + " | Department: " + departmentType);
        }
    }
    

    private static void displayDepartmentOptions() {
        System.out.println("\nPlease select an option (number) from the following:");
        int index = 1;

        for (DepartmentType option : DepartmentType.values()) {
            System.out.println(index + ": " + option.name());
            index++;
        }
        System.out.print("Option: ");
    }

    private static void displayStaffTypeEmployeeMenu() {
        System.out.println("\nPlease select an option (number) from the following:");
        int index = 1;

        for (StaffType option : StaffType.values()) {
            System.out.println(index + ": " + option.name());
            index++;
        }
        System.out.print("Option: ");
    }

    private static void displayTypeEmployeeMenu() {
        System.out.println("\nPlease select an option (number) from the following:");
        int index = 1;

        for (EmployeeType option : EmployeeType.values()) {
            System.out.println(index + ": " + option.name());
            index++;
        }
        System.out.print("Option: ");
    }

    // Display Main Menu
    private static void displayMenu() {
        System.out.println("\nPlease select an option (number) from the following:");
        int index = 1;

        for (MainMenu option : MainMenu.values()) {
            System.out.println(index + ": " + option.name());
            index++;
        }
        System.out.print("Option: ");
    }
}
