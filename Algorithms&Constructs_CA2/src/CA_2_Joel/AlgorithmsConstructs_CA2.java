package CA_2_Joel;

import java.util.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;

public class AlgorithmsConstructs_CA2 {

    static List<Employee> employees;

    public static void main(String[] args) {

        Scanner myScan = new Scanner(System.in);
        boolean fileLoaded = false;
        String filePath = "Applicants_Form.txt";

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
            String line;
            while ((line = buffReader.readLine()) != null) {
                String[] lineData = line.split(",");

                // Assuming the file contains name and role and department
                String name = lineData[0];
                String role = lineData.length > 1 ? lineData[1].trim() : "noRole";
                String deptName = lineData.length > 2 ? lineData[2].trim() : null;
                Department department = createDepartmentFromName(deptName);
                Employee newEmployee;

                switch (role) {
                    case "Doctor":
                        newEmployee = new Doctor(name, department);
                        break;
                    case "Nurse":
                        newEmployee = new Nurse(name, department);
                        break;
                    case "Administrative Staff":
                        newEmployee = new AdministrativeStaff(name, department);
                        break;
                    case "Nursing Manager":
                        newEmployee = new NursingManager(name, department);
                        break;
                    case "Chief Medical Officer":
                        newEmployee = new ChiefMedicalOfficer(name, department);
                        break;
                    case "Administrative Manager":
                        newEmployee = new AdministrativeManager(name, department);
                        break;
                    default:
                        newEmployee = new Employee(name, null);
                        ; // Default to Employee
                        break;
                }
                employees.add(newEmployee);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the data: " + e.getMessage());
        }

        // Print employees list
        printEmployeesList(employees, myScan);

        MenuOption selectOption = null;

        do {
            displayMenu();

            // Process user input
            while (!myScan.hasNextInt()) {
                System.out.println("Please select integers only from the menu Options");
                myScan.next();
            }

            int option = myScan.nextInt();
            myScan.nextLine();
            selectOption = MenuOption.getValue(option);

            if (selectOption == null) {
                System.out.println("Please select from all the available options");
            } else {
                switch (selectOption) {
                    case SORT:
                        System.out.println("SORT SELECTED");
                        sortEmployees();
                        printEmployeesList(employees, myScan);
                        break;
                    case SEARCH:
                        System.out.println("SEARCH SELECTED");
                        searchEmployee(myScan);
                        break;
                    case ADD_EMPLOYEE:
                        System.out.println("ADD_EMPLOYEE SELECTED");
                        addEmployeeMenu(myScan);
                        break;
                    case GENERATE_RANDOM_EMPLOYEE:
                        System.out.println("GENERATE_RANDOM_EMPLOYEE SELECTED");
                        System.out.print("Enter the number of random employees to generate: ");
                        int numEmployees = 0;
                        while (numEmployees <= 0 || numEmployees > 5000) {
                            if (myScan.hasNextInt()) {
                                numEmployees = myScan.nextInt();
                                myScan.nextLine(); // Consume newline
                                if (numEmployees <= 0) {
                                    System.out.print("Please enter a positive integer: ");
                                }
                            } else {
                                System.out.print("Invalid input. Please enter a positive integer.");
                                System.out.print("Please enter a positive integer between 1 - 5000");
                                myScan.next(); // Consume invalid input
                            }
                        }
                        generateRandomEmployees(numEmployees, myScan);
                        break;
                    case EDIT_EMPLOYEE_:
                        editEmployee(myScan);
                    case EXIT:
                        System.out.println("You have exited the program!");
                        break;
                    default:
                        System.out.println("That option does not exist. Please try again.");
                }
            }
        } while (selectOption != MenuOption.EXIT);
        myScan.close();
    }

    private static void generateRandomEmployees(int count, Scanner myScan) {
        HttpURLConnection connection = null;
        List<Employee> randomEmployees = new ArrayList<>();
    
        try {
            System.out.println("Sending the request for " + count + " random employees.");
            System.out.println("Please wait...");

            // Create a URL object with the API endpoint (requesting XML format)
            String apiUrl = "https://randomuser.me/api/?results=" + count + "&?nat=ie&format=xml";
            URL url = new URL(apiUrl);
    
            // Open a connection
            connection = (HttpURLConnection) url.openConnection();
    
            // Set the request method to GET
            connection.setRequestMethod("GET");
    
            // Get the response code
            int responseCode = connection.getResponseCode();
    
            if (responseCode == HttpURLConnection.HTTP_OK) { // Success
                // Parse the XML response
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(connection.getInputStream());
                doc.getDocumentElement().normalize();
    
                // Get the NodeList of <results> elements
                NodeList resultsList = doc.getElementsByTagName("results");
    
                if (resultsList.getLength() > 0) {
                    //resultsList.getLength() - 1 Because las node is Request Info not User Data
                    for (int i = 0; i < resultsList.getLength() - 1; i++) {
                        Node resultsNode = resultsList.item(i);
                        if (resultsNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element resultsElement = (Element) resultsNode;
                            Element nameElement = (Element) resultsElement.getElementsByTagName("name").item(0);
    
                            if (nameElement != null) {
                                String firstName = nameElement.getElementsByTagName("first").item(0).getTextContent();
                                String lastName = nameElement.getElementsByTagName("last").item(0).getTextContent();
                                String name = firstName + " " + lastName;
    
                                // Randomly select EmployeeType or ManagerType
                                Random random = new Random();
                                int roleType = random.nextInt(2); // 0 for Employee, 1 for Manager
                                Employee newEmployee;
                                Department department = selectRandomDepartment();
    
                                if (roleType == 0) {
                                    EmployeeType[] employeeTypes = EmployeeType.values();
                                    EmployeeType employeeType;
                                    do {
                                        employeeType = employeeTypes[random.nextInt(employeeTypes.length)];
                                    } while (employeeType == EmployeeType.BACK);
                                    newEmployee = createEmployee(name, employeeType, department);
                                } else {
                                    ManagerType[] managerTypes = ManagerType.values();
                                    ManagerType managerType;
                                    do {
                                        managerType = managerTypes[random.nextInt(managerTypes.length)];
                                    } while (managerType == ManagerType.BACK);
                                    newEmployee = createManager(name, managerType, department);
                                }
    
                                employees.add(newEmployee);
                                randomEmployees.add(newEmployee);
                            } else {
                                System.out.println("Name element not found for one of the users.");
                            }
                        }
                    }
                    System.out.println(randomEmployees.size() + " random employee(s) generated and added to the list.");
                } else {
                    System.out.println("No user data received from the API.");
                }
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while fetching or parsing user data: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception ex) {
                System.out.println("An error occurred while closing resources: " + ex.getMessage());
            }
        }
        printEmployeesList(randomEmployees, myScan);
    }

    private static Department selectRandomDepartment() {
        Random random = new Random();
        int index = random.nextInt(DepartmentType.values().length - 1); // Exclude BACK
        DepartmentType selectedDept = DepartmentType.values()[index];
        switch (selectedDept) {
            case CARDIOLOGY:
                return new Cardiology();
            case EMERGENCY:
                return new Emergency();
            case PEDIATRICS:
                return new Pediatrics();
            default:
                return null;
        }
    }

    private static void sortEmployees() {
        if (employees == null || employees.size() <= 1) {
            System.out.println("Employee list is empty or has only one employee.");
            return;
        }
        employees = mergeSort(employees);
        System.out.println("Employees have been sorted by name.");
    }

    private static List<Employee> mergeSort(List<Employee> employeeList) {
        if (employeeList.size() <= 1) {
            return employeeList;
        }

        int mid = employeeList.size() / 2;
        List<Employee> left = new ArrayList<>(employeeList.subList(0, mid));
        List<Employee> right = new ArrayList<>(employeeList.subList(mid, employeeList.size()));

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private static List<Employee> merge(List<Employee> left, List<Employee> right) {
        List<Employee> merged = new ArrayList<>();
        int leftIndex = 0, rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).getName().compareToIgnoreCase(right.get(rightIndex).getName()) <= 0) {
                merged.add(left.get(leftIndex));
                leftIndex++;
            } else {
                merged.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex));
            rightIndex++;
        }

        return merged;
    }

    private static void searchEmployee(Scanner myScan) {
        System.out.print("Enter the name of the employee to search: ");
        String searchName = myScan.nextLine().trim();

        // Ensure the employee list is sorted before performing binary search
        sortEmployees();

        // Perform binary search
        int index = binarySearch(employees, searchName);
        if (index != -1) {
            // Define the format string for consistent column widths
            String format = "| %-20s | %-25s | %-20s |\n";
            Employee foundEmployee = employees.get(index);
            System.out.println("\nEmployee found:");
            System.out.println(
                    "----------------------------------------------------------------------------------------");
            System.out.printf(format, "Name", "Role", "Department");
            System.out.println(
                    "----------------------------------------------------------------------------------------");
            String name = foundEmployee.getName();
            String role = foundEmployee.getRole();
            String departmentName = (foundEmployee.getDepartment() != null)
                    ? foundEmployee.getDepartment().getDeptName()
                    : "No Department";
            System.out.printf(format, name, role, departmentName);
            System.out.println(
                    "----------------------------------------------------------------------------------------");
        } else {
            System.out.println("Employee with name '" + searchName + "' not found.");
        }
    }

    private static int binarySearch(List<Employee> employeeList, String targetName) {
        int low = 0;
        int high = employeeList.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Employee midEmployee = employeeList.get(mid);
            int comparison = midEmployee.getName().compareToIgnoreCase(targetName);

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1; // Employee not found
    }

    private static void addEmployeeMenu(Scanner myScan) {
        // Asking for Employee Name
        boolean isValidName = false;
        String name = "";

        do {
            System.out.println("Please insert employee Name: ");
            String input = myScan.nextLine();

            // Validating input contains no numbers and is not empty
            if (!input.matches(".*\\d.*") && !input.isEmpty()) {
                if (employeeExistsByName(input)) {
                    System.out
                            .println("An employee with the name '" + input + "' already exists. Duplication avoided.");
                    return; // Stop further input and return to the main menu
                }
                isValidName = true;
                name = input;
            } else {
                System.out.println("ERROR: Invalid input. Please type letters only.");
            }
        } while (!isValidName);

        EmployeeCategory selectOption = null;
        do {
            displayEmployeeCategoryMenu();

            while (!myScan.hasNextInt()) {
                System.out.println("Please select integers only from the options");
                myScan.next();
            }

            int option = myScan.nextInt();
            myScan.nextLine();

            selectOption = EmployeeCategory.getValue(option);

            if (selectOption == null) {
                System.out.println("Please select from all the available options");
            } else {
                switch (selectOption) {
                    case MANAGER:
                        addManager(name, myScan);
                        break;
                    case STAFF:
                        addStaff(name, myScan);
                        break;
                    case BACK:
                        break;
                    default:
                        System.out.println("Please select a valid option.");
                        break;
                }
                break; // Exit the loop after processing the selection
            }
        } while (selectOption != EmployeeCategory.BACK);
    }

    private static boolean employeeExistsByName(String name) {
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                return true; // Employee with the same name exists
            }
        }
        return false; // No duplicate found
    }

    private static void addStaff(String name, Scanner myScan) {
        int option = 0;
        do {
            displayEmployeeTypeMenu();

            while (!myScan.hasNextInt()) {
                System.out.println("Please select integers only from the options");
                myScan.next();
            }

            option = myScan.nextInt();
            myScan.nextLine();
            EmployeeType selectOption = EmployeeType.getValue(option);

            if (selectOption == null) {
                System.out.println("Please select from all the available options");
            } else if (selectOption == EmployeeType.BACK) {
                break;
            } else {
                // Select Department
                Department department = selectDepartment(myScan);
                if (department == null) {
                    System.out.println("Department selection canceled.");
                    break;
                }

                Employee newEmployee = createEmployee(name, selectOption, department);
                if (newEmployee != null) {
                    employees.add(newEmployee);
                    System.out.println("Employee added successfully.");
                    // Print the added employee's details
                    printEmployeeDetails(newEmployee);
                }
                break;
            }
        } while (option != 4);
    }

    private static void addManager(String name, Scanner myScan) {
        int option = 0;
        do {
            displayManagerTypeMenu();

            while (!myScan.hasNextInt()) {
                System.out.println("Please select integers only from the options");
                myScan.next();
                break;
            }

            option = myScan.nextInt();
            myScan.nextLine();
            ManagerType selectOption = ManagerType.getValue(option);

            if (selectOption == null) {
                System.out.println("Please select from all the available options");
                break;
            } else if (selectOption == ManagerType.BACK) {
                break;
            } else {
                // Select Department
                Department department = selectDepartment(myScan);
                if (department == null) {
                    System.out.println("Department selection canceled.");
                    break;
                }

                Manager newManager = createManager(name, selectOption, department);
                if (newManager != null) {
                    employees.add(newManager);
                    System.out.println("Manager added successfully.");
                    // Print the added manager's details
                    printEmployeeDetails(newManager);
                }
                break;
            }
        } while (option != 4);
    }

    private static Department selectDepartment(Scanner myScan) {
        System.out.println("\nSelect a Department:");
        displayDepartmentTypeMenu();
    
        DepartmentType selectedDept = null;
        while (selectedDept == null) {
            if (myScan.hasNextInt()) {
                int option = myScan.nextInt();
                myScan.nextLine(); // Consume newline
                selectedDept = DepartmentType.getValue(option);
                if (selectedDept == null || selectedDept == DepartmentType.BACK) {
                    System.out.println("Invalid selection or cancelled. Returning to previous menu.");
                    return null;
                }
            } else {
                System.out.print("Invalid input. Please enter a number: ");
                myScan.next(); // Consume invalid input
            }
        }
    
        // Map DepartmentType to Department object
        return new Department(selectedDept.getStringValue());
    }

    private static Department createDepartmentFromName(String deptName) {
        if (deptName == null || deptName.isEmpty()) {
            return null;
        }
        switch (deptName.toLowerCase()) {
            case "cardiology":
                return new Cardiology();
            case "emergency":
                return new Emergency();
            case "pediatrics":
                return new Pediatrics();
            default:
                // Handle unknown departments if necessary
                return new Department(deptName);
        }
    }

    private static Employee createEmployee(String name, EmployeeType employeeType, Department department) {
        switch (employeeType) {
            case DOCTOR:
                return new Doctor(name, department);
            case NURSE:
                return new Nurse(name, department);
            case ADMINISTRATIVE_STAFF:
                return new AdministrativeStaff(name, department);
            default:
                return null;
        }
    }

    private static Manager createManager(String name, ManagerType managerType, Department department) {
        switch (managerType) {
            case NURSING_MANAGER:
                return new NursingManager(name, department);
            case CHIEF_MEDICAL_OFFICER:
                return new ChiefMedicalOfficer(name, department);
            case ADMINISTRATIVE_MANAGER:
                return new AdministrativeManager(name, department);
            default:
                return null;
        }
    }

    private static void editEmployee(Scanner myScan) {
        if (employees.isEmpty()) {
            System.out.println("No employees available to edit.");
            return;
        }
    
        // Display list of employees with indices using MenuOptionInterface
        System.out.println("\nSelect an employee to edit:");
    
        // Create an array of MenuOptionInterface for employees
        MenuOptionInterface[] employeeOptions = new MenuOptionInterface[employees.size()];
        for (int i = 0; i < employees.size(); i++) {
            final int index = i;
            final Employee emp = employees.get(i);
            employeeOptions[i] = new MenuOptionInterface() {
                @Override
                public int getValue() {
                    return index + 1; // Start from 1
                }
    
                @Override
                public String getStringValue() {
                    return emp.getName() + " - " + emp.getRole();
                }
            };
        }
    
        // Display the employee selection menu
        displayMenuOptions(employeeOptions);
    
        // Get user's selection
        int selectedOption = -1;
        while (true) {
            if (myScan.hasNextInt()) {
                selectedOption = myScan.nextInt();
                myScan.nextLine(); // Consume newline
                if (selectedOption >= 1 && selectedOption <= employees.size()) {
                    break;
                } else {
                    System.out.print("Invalid selection. Please enter a number between 1 and " + employees.size() + ": ");
                }
            } else {
                System.out.print("Invalid input. Please enter a number between 1 and " + employees.size() + ": ");
                myScan.next(); // Consume invalid input
            }
        }
    
        int index = selectedOption - 1; // Adjust for zero-based index
        Employee employeeToEdit = employees.get(index);
    
        // Display editing options using the enum and generic method
        boolean doneEditing = false;
        do {
            System.out.println("\nEditing Employee: " + employeeToEdit.getName());
            displayMenuOptions(EditOption.values());
    
            int editOptionValue = -1;
            while (true) {
                if (myScan.hasNextInt()) {
                    editOptionValue = myScan.nextInt();
                    myScan.nextLine(); // Consume newline
                    break;
                } else {
                    System.out.print("Invalid input. Please enter a valid option number: ");
                    myScan.next(); // Consume invalid input
                }
            }
    
            EditOption editOption = EditOption.getValue(editOptionValue);
            if (editOption == null) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }
    
            switch (editOption) {
                case EDIT_NAME:
                    // Edit Name
                    System.out.print("Enter new name: ");
                    String newName = myScan.nextLine().trim();
                    while (newName.isEmpty()) {
                        System.out.print("Name cannot be empty. Enter new name: ");
                        newName = myScan.nextLine().trim();
                    }
                    employeeToEdit.setName(newName);
                    System.out.println("Name updated successfully.");
                    break;
                case EDIT_ROLE:
                    // Edit Role
                    if (employeeToEdit instanceof Manager) {
                        editManagerRole((Manager) employeeToEdit, myScan);
                    } else {
                        editEmployeeRole(employeeToEdit, myScan);
                    }
                    // Update reference in case the employee instance was replaced
                    employeeToEdit = employees.get(index);
                    break;
                case EDIT_DEPARTMENT:
                    // Edit Department
                    Department newDept = selectDepartment(myScan);
                    if (newDept != null) {
                        employeeToEdit.setDepartment(newDept);
                        System.out.println("Department updated successfully.");
                    } else {
                        System.out.println("Department update cancelled.");
                    }
                    break;
                case FINISH_EDITING:
                    doneEditing = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!doneEditing);
    
        System.out.println("\nUpdated Employee Details:");
        printEmployeeDetails(employeeToEdit);
    }

    private static void editEmployeeRole(Employee employee, Scanner myScan) {
        System.out.println("\nSelect a new role for the employee:");
        displayMenuOptions(EmployeeType.values());
    
        EmployeeType newRole = null;
        while (newRole == null) {
            if (myScan.hasNextInt()) {
                int option = myScan.nextInt();
                myScan.nextLine(); // Consume newline
                newRole = EmployeeType.getValue(option);
                if (newRole == null || newRole == EmployeeType.BACK) {
                    System.out.print("Invalid selection. Please select a valid role: ");
                    newRole = null;
                }
            } else {
                System.out.print("Invalid input. Please enter a number: ");
                myScan.next(); // Consume invalid input
            }
        }
    
        // Update the employee's role by creating a new instance
        Employee updatedEmployee = createEmployee(employee.getName(), newRole, employee.getDepartment());
        employees.set(employees.indexOf(employee), updatedEmployee);
        System.out.println("Role updated successfully.");
    }

    private static void editManagerRole(Manager manager, Scanner myScan) {
        System.out.println("\nSelect a new role for the manager:");
        displayMenuOptions(ManagerType.values());
    
        ManagerType newRole = null;
        while (newRole == null) {
            if (myScan.hasNextInt()) {
                int option = myScan.nextInt();
                myScan.nextLine(); // Consume newline
                newRole = ManagerType.getValue(option);
                if (newRole == null || newRole == ManagerType.BACK) {
                    System.out.print("Invalid selection. Please select a valid role: ");
                    newRole = null;
                }
            } else {
                System.out.print("Invalid input. Please enter a number: ");
                myScan.next(); // Consume invalid input
            }
        }
    
        // Update the manager's role by creating a new instance
        Manager updatedManager = createManager(manager.getName(), newRole, manager.getDepartment());
        employees.set(employees.indexOf(manager), updatedManager);
        System.out.println("Role updated successfully.");
    }

    private static void printEmployeesList(List<Employee> employees, Scanner scanner) {
        int pageSize = 20;
        int totalEmployees = employees.size();
        int totalPages = (totalEmployees + pageSize - 1) / pageSize;
    
        String format = "| %-20s | %-25s | %-20s |\n";
    
        for (int page = 0; page < totalPages; page++) {
            int start = page * pageSize;
            int end = Math.min(start + pageSize, totalEmployees);
    
            System.out.println("\nPage " + (page + 1) + " of " + totalPages);
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.printf(format, "Name", "Role", "Department");
            System.out.println("----------------------------------------------------------------------------------------");
    
            for (int i = start; i < end; i++) {
                Employee employee = employees.get(i);
                String name = employee.getName();
                String role = employee.getRole();
                String departmentName = (employee.getDepartment() != null) ? employee.getDepartment().getDeptName()
                        : "No Department";
                System.out.printf(format, name, role, departmentName);
            }
            System.out.println("----------------------------------------------------------------------------------------");
    
            if (page < totalPages - 1) {
                System.out.print("Press Enter to see the next page, or type 'q' to quit: ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("q")) {
                    break;
                }
            }
        }
    }

    private static void printEmployeeDetails(Employee newEmployee) {
        // Define the format string
        String format = "| %-20s | %-25s | %-20s |\n";
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf(format, "Name", "Role", "Department");
        System.out.println("----------------------------------------------------------------------------------------");
        String departmentName = (newEmployee.getDepartment() != null) ? newEmployee.getDepartment().getDeptName()
                : "No Department";
        System.out.printf(format, newEmployee.getName(), newEmployee.getRole(), departmentName);
        System.out.println("----------------------------------------------------------------------------------------");
    }

    private static void displayMenuOptions(MenuOptionInterface[] options) {
        String format = "| %-2d: %-35s |\n";
        System.out.println("\nPlease select an option:");
        System.out.println("---------------------------------------------------");
        for (MenuOptionInterface option : options) {
            System.out.printf(format, option.getValue(), option.getStringValue());
        }
        System.out.println("---------------------------------------------------");
        System.out.print("Option: ");
    }

    private static void displayMenu() {
        displayMenuOptions(MenuOption.values());
    }

    private static void displayEmployeeCategoryMenu() {
        displayMenuOptions(EmployeeCategory.values());
    }

    private static void displayEmployeeTypeMenu() {
        displayMenuOptions(EmployeeType.values());
    }

    private static void displayManagerTypeMenu() {
        displayMenuOptions(ManagerType.values());
    }

    private static void displayDepartmentTypeMenu() {
        displayMenuOptions(DepartmentType.values());
    }

    // Department class
    public static class Department {
        private String deptName;

        public Department(String deptName) {
            this.deptName = deptName;
        }

        public String getDeptName() {
            return deptName;
        }
    }

    public static class Cardiology extends Department {
        public Cardiology() {
            super("Cardiology");
        }
    }

    public static class Emergency extends Department {
        public Emergency() {
            super("Emergency");
        }
    }

    public static class Pediatrics extends Department {
        public Pediatrics() {
            super("Pediatrics");
        }
    }

    // Employee and Manager classes and their subclasses
    public static class Employee {
        protected String name;
        protected Department department;
        protected String role;

        public Employee(String name, Department department) {
            this.name = name;
            this.department = department;
        }

        public String getRole() {
            return "No Role";
        };

        public String getName() {
            return name;
        }

        public Department getDepartment() {
            return department;
        }

        // Setter methods
        public void setName(String name) {
            this.name = name;
        }

        public void setDepartment(Department department) {
            this.department = department;
        }
    }

    public static class Doctor extends Employee {
        public Doctor(String name, Department department) {
            super(name, department);
        }

        @Override
        public String getRole() {
            return "Doctor";
        }
    }

    public static class Nurse extends Employee {
        public Nurse(String name, Department department) {
            super(name, department);
        }

        @Override
        public String getRole() {
            return "Nurse";
        }
    }

    public static class AdministrativeStaff extends Employee {
        public AdministrativeStaff(String name, Department department) {
            super(name, department);
        }

        @Override
        public String getRole() {
            return "Administrative Staff";
        }
    }

    public static abstract class Manager extends Employee {
        public Manager(String name, Department department) {
            super(name, department);
        }

        public abstract String getManagerType();

        @Override
        public String getRole() {
            return getManagerType();
        }
    }

    public static class NursingManager extends Manager {
        public NursingManager(String name, Department department) {
            super(name, department);
        }

        @Override
        public String getManagerType() {
            return "Nursing Manager";
        }
    }

    public static class ChiefMedicalOfficer extends Manager {
        public ChiefMedicalOfficer(String name, Department department) {
            super(name, department);
        }

        @Override
        public String getManagerType() {
            return "Chief Medical Officer";
        }
    }

    public static class AdministrativeManager extends Manager {
        public AdministrativeManager(String name, Department department) {
            super(name, department);
        }

        @Override
        public String getManagerType() {
            return "Administrative Manager";
        }
    }

    // Enums as per the provided code
    public interface MenuOptionInterface {
        int getValue();

        String getStringValue();
    }

    enum MenuOption implements MenuOptionInterface {
        SORT(1, "Sorting"),
        SEARCH(2, "Searching"),
        ADD_EMPLOYEE(3, "Add Employee"),
        GENERATE_RANDOM_EMPLOYEE(4, "Generate Random Employee"),
        EDIT_EMPLOYEE_(5, "Edit Existing Employee"),
        EXIT(6, "Exit");

        private final int value;
        private final String stringValue;

        MenuOption(int value, String stringValue) {
            this.value = value;
            this.stringValue = stringValue;
        }

        public static MenuOption getValue(int value) {
            for (MenuOption option : values()) {
                if (option.value == value) {
                    return option;
                }
            }
            return null;
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public String getStringValue() {
            return stringValue;
        }
    }

    enum EmployeeCategory implements MenuOptionInterface {
        MANAGER(1, "Manager"),
        STAFF(2, "Staff"),
        BACK(3, "Back");

        private final int value;
        private final String stringValue;

        EmployeeCategory(int value, String stringValue) {
            this.value = value;
            this.stringValue = stringValue;
        }

        public static EmployeeCategory getValue(int value) {
            for (EmployeeCategory option : values()) {
                if (option.value == value) {
                    return option;
                }
            }
            return null;
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public String getStringValue() {
            return stringValue;
        }
    }

    enum ManagerType implements MenuOptionInterface {
        NURSING_MANAGER(1, "Nursing Manager"),
        CHIEF_MEDICAL_OFFICER(2, "Chief Medical Officer"),
        ADMINISTRATIVE_MANAGER(3, "Administrative Manager"),
        BACK(4, "Back");

        public final int value;
        public final String stringValue;

        ManagerType(int value, String stringValue) {
            this.value = value;
            this.stringValue = stringValue;
        }

        public static ManagerType getValue(int value) {
            for (ManagerType option : values()) {
                if (option.value == value) {
                    return option;
                }
            }
            return null;
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public String getStringValue() {
            return stringValue;
        }
    }

    enum EmployeeType implements MenuOptionInterface {
        DOCTOR(1, "Doctor"),
        NURSE(2, "Nurse"),
        ADMINISTRATIVE_STAFF(3, "Administrative Staff"),
        BACK(4, "Back");

        public final int value;
        public final String stringValue;

        EmployeeType(int value, String stringValue) {
            this.value = value;
            this.stringValue = stringValue;
        }

        public static EmployeeType getValue(int value) {
            for (EmployeeType option : values()) {
                if (option.value == value) {
                    return option;
                }
            }
            return null;
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public String getStringValue() {
            return stringValue;
        }
    }

    enum DepartmentType implements MenuOptionInterface {
        EMERGENCY(1, "Emergency"),
        PEDIATRICS(2, "Pediatrics"),
        CARDIOLOGY(3, "Cardiology"),
        BACK(4, "Back");

        private final int value;
        private final String stringValue;

        DepartmentType(int value, String stringValue) {
            this.value = value;
            this.stringValue = stringValue;
        }

        public static DepartmentType getValue(int value) {
            for (DepartmentType option : values()) {
                if (option.value == value) {
                    return option;
                }
            }
            return null;
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public String getStringValue() {
            return stringValue;
        }
    }

    enum EditOption implements MenuOptionInterface {
        EDIT_NAME(1, "Edit Name"),
        EDIT_ROLE(2, "Edit Role"),
        EDIT_DEPARTMENT(3, "Edit Department"),
        FINISH_EDITING(4, "Finish Editing");
    
        private final int value;
        private final String stringValue;
    
        EditOption(int value, String stringValue) {
            this.value = value;
            this.stringValue = stringValue;
        }
    
        public static EditOption getValue(int value) {
            for (EditOption option : values()) {
                if (option.value == value) {
                    return option;
                }
            }
            return null;
        }
    
        @Override
        public int getValue() {
            return value;
        }
    
        @Override
        public String getStringValue() {
            return stringValue;
        }
    }
}
