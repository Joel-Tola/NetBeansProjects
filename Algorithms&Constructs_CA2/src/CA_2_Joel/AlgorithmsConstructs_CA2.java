package CA_2_Joel;

import java.util.*;
import java.io.*;

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

                // Assuming the file contains name and role
                String name = lineData[0];
                String role = lineData.length > 1 ? lineData[1] : null;
                Employee newEmployee;

                switch (role) {
                    case "Doctor":
                        newEmployee = new Doctor(name, null);
                        break;
                    case "Nurse":
                        newEmployee = new Nurse(name, null);
                        break;
                    case "Administrative Staff":
                        newEmployee = new AdministrativeStaff(name, null);
                        break;
                    case "Nursing Manager":
                        newEmployee = new NursingManager(name, null);
                        break;
                    case "Chief Medical Officer":
                        newEmployee = new ChiefMedicalOfficer(name, null);
                        break;
                    case "Administrative Manager":
                        newEmployee = new AdministrativeManager(name, null);
                        break;
                    default:
                        newEmployee = new Doctor(name, null); // Default to Doctor
                        break;
                }
                employees.add(newEmployee);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the data: " + e.getMessage());
        }

        // Print employees list
        for (Employee employee : employees) {
            System.out.println(employee.getName() + " | Role: " + employee.getRole());
        }

        int intOption = 0;
        do {
            displayMenu();

            // Process user input
            while (!myScan.hasNextInt()) {
                System.out.println("Please select integers only from the menu Options");
                myScan.next();
            }

            int option = myScan.nextInt();
            myScan.nextLine();
            MenuOption selectOption = MenuOption.getValue(option);

            if (selectOption == null) {
                System.out.println("Please select from all the available options");
            } else {
                intOption = option;

                switch (selectOption) {
                    case SORT:
                        System.out.println("SORT SELECTED");
                        sortEmployees();
                        printAllEmployees();
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
                        generateRandomEmployee();
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
    }

    private static void generateRandomEmployee() {
        // Generate a random employee
        String[] names = {"Alice", "Bob", "Charlie", "Diana", "Edward"};
        Random random = new Random();
        String name = names[random.nextInt(names.length)];

        // Randomly select EmployeeType or ManagerType
        int roleType = random.nextInt(2); // 0 for Employee, 1 for Manager
        Employee newEmployee;
        if (roleType == 0) {
            EmployeeType[] employeeTypes = EmployeeType.values();
            EmployeeType employeeType = employeeTypes[random.nextInt(employeeTypes.length - 1)]; // Exclude BACK
            newEmployee = createEmployee(name, employeeType, null);
        } else {
            ManagerType[] managerTypes = ManagerType.values();
            ManagerType managerType = managerTypes[random.nextInt(managerTypes.length - 1)]; // Exclude BACK
            newEmployee = createManager(name, managerType, null);
        }

        employees.add(newEmployee);
        System.out.println("Random employee generated and added:");
        System.out.println(newEmployee.getName() + " | Role: " + newEmployee.getRole());
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
            Employee foundEmployee = employees.get(index);
            System.out.println("Employee found: " + foundEmployee.getName() + " | Role: " +
                foundEmployee.getRole() + " | Department: " +
                (foundEmployee.getDepartment() != null ? foundEmployee.getDepartment().getDeptName() : "No Department"));
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
                isValidName = true;
                name = input;
            } else {
                System.out.println("ERROR: Invalid input. Please type letters only.");
            }
        } while (!isValidName);

        int option = 0;
        do {
            System.out.println("\nIs the employee a Manager or Staff?");
            System.out.println("1: Manager");
            System.out.println("2: Staff");
            System.out.println("3: Back");
            System.out.print("Option: ");

            while (!myScan.hasNextInt()) {
                System.out.println("Please select integers only from the options");
                myScan.next();
            }

            option = myScan.nextInt();
            myScan.nextLine();

            if (option == 1) {
                addManager(name, myScan);
                break;
            } else if (option == 2) {
                addStaff(name, myScan);
                break;
            } else if (option == 3) {
                break;
            } else {
                System.out.println("Please select a valid option.");
            }
        } while (option != 3);
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
                Employee newEmployee = createEmployee(name, selectOption, null);
                employees.add(newEmployee);
                System.out.println("Employee added successfully.");
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
            }

            option = myScan.nextInt();
            myScan.nextLine();
            ManagerType selectOption = ManagerType.getValue(option);

            if (selectOption == null) {
                System.out.println("Please select from all the available options");
            } else if (selectOption == ManagerType.BACK) {
                break;
            } else {
                Manager newManager = createManager(name, selectOption, null);
                employees.add(newManager);
                System.out.println("Manager added successfully.");
                break;
            }
        } while (option != 4);
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

    private static void printAllEmployees() {
        for (Employee employee : employees) {
            String role = employee.getRole();
            String departmentName = (employee.getDepartment() != null) ? employee.getDepartment().getDeptName() : "No Department";
            System.out.println(employee.getName() + " | Role: " + role + " | Department: " + departmentName);
        }
    }

    private static void displayMenu() {
        System.out.println("\nPlease select an option (number) from the following:");
        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.value + ": " + option.stringValue);
        }
        System.out.print("Option: ");
    }

    private static void displayEmployeeTypeMenu() {
        System.out.println("\nPlease select an Employee Type (number) from the following:");
        for (EmployeeType option : EmployeeType.values()) {
            System.out.println(option.value + ": " + option.stringValue);
        }
        System.out.print("Option: ");
    }

    private static void displayManagerTypeMenu() {
        System.out.println("\nPlease select a Manager Type (number) from the following:");
        for (ManagerType option : ManagerType.values()) {
            System.out.println(option.value + ": " + option.stringValue);
        }
        System.out.print("Option: ");
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

    // Employee and Manager classes and their subclasses
    public static abstract class Employee {
        protected String name;
        protected Department department;

        public Employee(String name, Department department) {
            this.name = name;
            this.department = department;
        }

        public abstract String getRole();

        public String getName() {
            return name;
        }

        public Department getDepartment() {
            return department;
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
    enum MenuOption {
        SORT(1, "Sorting"),
        SEARCH(2, "Searching"),
        ADD_EMPLOYEE(3, "Add Employee"),
        GENERATE_RANDOM_EMPLOYEE(4, "Generate Random Employee"),
        EXIT(5, "Exit");

        public final int value;
        public final String stringValue;

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
    }

    enum ManagerType {
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
    }

    enum EmployeeType {
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
    }
}
