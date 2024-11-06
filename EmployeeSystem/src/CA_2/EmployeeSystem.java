package CA_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeSystem {

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
                String[] lineName = line.split(",");

                employees.add(new Employee(lineName[0], null));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the data: " + e.getMessage());
        }

        // Print employees list
        printAllEmployees();

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
            if (option < 1 || option > MainMenu.values().length) {
                System.out.println("Please select from all the available options");

            } else {
                intOption = option;
                selectOption = MainMenu.values()[option - 1];

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
                        addEmployeeMenu(employees, myScan);
                        System.out.println("ADD_EMPLOYEE SELECTED");
                        break;
                    case GENERATE_RANDOM_EMPLOYEE:
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

        // Merge while both lists have elements
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).name.compareToIgnoreCase(right.get(rightIndex).name) <= 0) {
                merged.add(left.get(leftIndex));
                leftIndex++;
            } else {
                merged.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        // Add remaining elements from left
        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex));
            leftIndex++;
        }

        // Add remaining elements from right
        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex));
            rightIndex++;
        }

        return merged;
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
            myScan.nextLine();
            EmployeeType selectOption = null;
            if (option < 1 || option > EmployeeType.values().length) {
                System.out.println("Please select from all the available options");

            } else {
                selectOption = EmployeeType.values()[option - 1];

                switch (selectOption) {
                    case STAFF:
                        System.out.println("STAFF SELECTED");
                        addStaffEmployee(name, selectOption, myScan);
                        break;
                    case MANAGER:
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
            if (option < 1 || option > ManagerType.values().length) {
                System.out.println("Please select from all the available options");

            } else {
                managerType = ManagerType.values()[option - 1];

                switch (managerType) {
                    case HEAD_MANAGER:
                        System.out.println("HEAD_MANAGER selected");
                        selectDepartmentMenu(name, "Manager", managerType, myScan);
                        break;
                    case ASSISTANT_MANAGER:
                        System.out.println("ASSISTANT_MANAGER selected");
                        selectDepartmentMenu(name, "Manager", managerType, myScan);
                        break;
                    case TEAM_LEAD:
                        System.out.println("TEAM_LEAD selected");
                        selectDepartmentMenu(name, "Manager", managerType, myScan);
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
            if (option < 1 || option > StaffType.values().length) {
                System.out.println("Please select from all the available options");

            } else {
                selectOption = StaffType.values()[option - 1];

                switch (selectOption) {
                    case JR_STAFF:
                        System.out.println("JR_STAFF selected");
                        selectDepartmentMenu(name, "Staff", selectOption, myScan);
                        break;
                    case SR_STAFF:
                        System.out.println("SR_STAFF selected");
                        selectDepartmentMenu(name, "Staff", selectOption, myScan);
                        break;
                    case CONSULTANT:
                        System.out.println("CONSULTANT selected");
                        selectDepartmentMenu(name, "Staff", selectOption, myScan);
                        break;
                    case BACK:
                        break;
                    default:
                        System.out.println("That option does not exist. Please try again.");
                }
            }
        } while (option != 4);
    }

    private static void selectDepartmentMenu(String name, String employeeType, Enum<?> specificType, Scanner myScan) {

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
            if (option < 1 || option > DepartmentType.values().length) {
                System.out.println("Please select from all the available options");

            } else {
                selectOption = DepartmentType.values()[option - 1];
                Employee newEmployee;
                Department department;
                switch (selectOption) {
                    case ENGINEERING:
                        System.out.println("ENGINEERING selected");
                        department = new Engineering("Engineering");
                        newEmployee = createEmployee(employeeType, name, department, specificType);
                        employees.add(newEmployee);
                        System.out.println("Employee added successfully.");
                        break;
                    case FINANCE:
                        System.out.println("FINANCE selected");
                        department = new Finance("Finance");
                        newEmployee = createEmployee(employeeType, name, department, specificType);
                        employees.add(newEmployee);
                        System.out.println("Employee added successfully.");
                        break;
                    case HR:
                        System.out.println("HR selected");
                        department = new HR("HR");
                        newEmployee = createEmployee(employeeType, name, department, specificType);
                        employees.add(newEmployee);
                        System.out.println("Employee added successfully.");
                        break;
                    case BACK:
                        break;
                    default:
                        System.out.println("That option does not exist. Please try again.");
                }
            }
        } while (option != 4);
    }

    private static Employee createEmployee(String employeeType, String name, Department department, Enum<?> specificType) {
        if (employeeType.equalsIgnoreCase("Staff")) {
            switch ((StaffType) specificType) {
                case JR_STAFF:
                    return new JuniorStaff(name, department);
                case SR_STAFF:
                    return new SeniorStaff(name, department);
                case CONSULTANT:
                    return new Consultant(name, department);
                default:
                    return new Staff(name, department);
            }
        } else if (employeeType.equalsIgnoreCase("Manager")) {
            switch ((ManagerType) specificType) {
                case HEAD_MANAGER:
                    return new HeadManager(name, department);
                case ASSISTANT_MANAGER:
                    return new AssistantManager(name, department);
                case TEAM_LEAD:
                    return new TeamLead(name, department);
                default:
                    return new Manager(name, department);
            }
        } else {
            return new Employee(name, department);
        }
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

            // Get the employee type and department type as the class names
            String employeeType = foundEmployee.getClass().getSimpleName();
            String departmentType = (foundEmployee.department != null) ? foundEmployee.department.getClass().getSimpleName() : "No Department";

            System.out.println("Employee found: " + foundEmployee.name + " | Type: " + employeeType + " | Department: " + departmentType);

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
            int comparison = midEmployee.name.compareToIgnoreCase(targetName);

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

    // Employee and related classes

    public static class Employee {
        String name;
        Department department;

        public Employee(String name, Department department) {
            this.name = name;
            this.department = department;
        }
    }

    public static class Manager extends Employee {

        public Manager(String name, Department department) {
            super(name, department);
        }
    }

    public static class HeadManager extends Manager {
        public HeadManager(String name, Department department) {
            super(name, department);
        }
    }

    public static class TeamLead extends Manager {
        public TeamLead(String name, Department department) {
            super(name, department);
        }
    }

    public static class AssistantManager extends Manager {
        public AssistantManager(String name, Department department) {
            super(name, department);
        }
    }

    public static class Staff extends Employee {

        public Staff(String name, Department department) {
            super(name, department);
        }
    }

    public static class JuniorStaff extends Staff {
        public JuniorStaff(String name, Department department) {
            super(name, department);
        }
    }

    public static class SeniorStaff extends Staff {
        public SeniorStaff(String name, Department department) {
            super(name, department);
        }
    }

    public static class Consultant extends Staff {
        public Consultant(String name, Department department) {
            super(name, department);
        }
    }

    public static class Department {
        protected String deptName;

        public Department(String deptName) {
            this.deptName = deptName;
        }
    }

    public static class Engineering extends Department {
        public Engineering(String deptName) {
            super(deptName);
        }
    }

    public static class Finance extends Department {
        public Finance(String deptName) {
            super(deptName);
        }
    }

    public static class HR extends Department {
        public HR(String deptName) {
            super(deptName);
        }
    }

}
