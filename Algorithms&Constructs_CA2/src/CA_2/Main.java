package CA_2;

import java.util.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;

/**
 * The Main class serves as the main driver for an employee management system.
 * It provides functionality for managing a list of employees, including adding, editing,
 * searching, sorting, and generating random employees from a file or an API.
 *
 * Features include:
 *     ** Menu-driven system with multiple options for managing employees
 *     ** File input handling for loading employees
 *     ** Random employee generation using the Random User API
 *     ** Merge sort and binary search for employee list management
 *     ** Role and department assignment for employees
 * 
 * @author Joel Tola Soliz
 */
public class Main {

    static List<Employee> employees;

    /**
     * The entry point of the application.
     * Initializes the employee list and starts the main menu loop.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        // Create a Scanner object for user input
        Scanner myScan = new Scanner(System.in);
        // Initialize the list to store Employee objects
        employees = new ArrayList<>();

        MenuOption selectOption = null;

        // Main menu loop
        do {
            // Display the main menu options
            displayMenu();

            // Process user input and ensure it's an integer
            while (!myScan.hasNextInt()) {
                System.out.println("Please select integers only from the menu Options");
                myScan.next();
            }

            int option = myScan.nextInt();
            myScan.nextLine(); // Consume the newline character
            selectOption = MenuOption.getValue(option);

            if (selectOption == null) {
                // Handle invalid menu option
                System.out.println("Please select from all the available options");
            } else {
                // Execute the selected menu option
                switch (selectOption) {
                    case ADD_EMPLOYEE:
                        System.out.println("ADD_EMPLOYEE SELECTED");
                        // Call method to add a new employee
                        addEmployeeMenu(myScan);
                        break;
                    case GENERATE_RANDOM_EMPLOYEE:
                        System.out.println("GENERATE_RANDOM_EMPLOYEE SELECTED");
                        // Call method to generate random employees
                        generateRandomEmployeeMenu(myScan);
                        break;
                    case SORT:
                        System.out.println("SORT SELECTED");
                        // Sort the employees and display the sorted list
                        sortEmployees();
                        printEmployeesList(employees, myScan);
                        break;
                    case SEARCH:
                        System.out.println("SEARCH SELECTED");
                        // Search for an employee by name
                        searchEmployee(myScan);
                        break;
                    case EDIT_EMPLOYEE:
                        System.out.println("EDIT_EMPLOYEE SELECTED");
                        // Edit an existing employee's details
                        editEmployee(myScan);
                        break;
                    case EXIT:
                        System.out.println("You have exited the program!");
                        break;
                    default:
                        // This should not occur due to prior validation
                        System.out.println("That option does not exist. Please try again.");
                        break;
                }
            }
        } while (selectOption != MenuOption.EXIT);

        // Close the Scanner to free up resources
        myScan.close();
    }

    /**
     * Displays the menu for generating random employees and handles user input.
     * The user can choose to generate employees by reading a file or from an API.
     *
     * @param myScan Scanner object for user input.
     */
    private static void generateRandomEmployeeMenu(Scanner myScan) {
        GenerateEmployeeOption selectOption = null;

        // Loop to display the generate random employee menu until the user selects
        // "BACK"
        do {
            // Display menu options for generating random employees
            displayGenerateEmployeeMenu();

            // Validate user input to ensure it is an integer
            while (!myScan.hasNextInt()) {
                System.out.println("Please select integers only from the options");
                myScan.next();
            }

            // Read the user's selection
            int option = myScan.nextInt();
            myScan.nextLine(); // Consume the newline character

            // Convert the user input into a GenerateEmployeeOption enum
            selectOption = GenerateEmployeeOption.getValue(option);

            if (selectOption == null) {
                // Handle invalid menu option
                System.out.println("Please select from all the available options");
            } else {
                // Process the selected option.
                // The system might need support different integrations like file, API, or DB in
                // the future.
                switch (selectOption) {
                    case BY_FILE:
                        // Generate random employees by reading from a file
                        generateRandomEmployeeByFileName(myScan);
                        break;
                    case BY_API:
                        // Generate random employees by fetching from an API
                        // https://randomuser.me/documentation is a Free API
                        // For this POC is enough but could be an good example to integrate more API
                        // In this case the response is an XML file which is a standard for an API, but
                        // it could be a JSO as well
                        // Nevertheless, is important consider include a third-party library to add
                        // handling support for different responses.
                        // Manipulating XML without any library was difficult and could include some
                        // issues...
                        generateRandomEmployeeByAPI(myScan);
                        break;
                    case BACK:
                        // Return to the previous menu
                        break;
                    default:
                        // Handle any unexpected options
                        System.out.println("Please select a valid option.");
                        break;
                }
                break; // Exit the loop after processing the selection
            }
        } while (selectOption != GenerateEmployeeOption.BACK);
    }

    /**
     * Reads employee data from a file specified by the user and adds employees to
     * the list.
     * The user can input the file path or press 'q' to quit the operation.
     *
     * @param myScan Scanner object for user input.
     */
    private static void generateRandomEmployeeByFileName(Scanner myScan) {
        boolean fileLoaded = false; // Flag to indicate if the file was successfully loaded
        String filePath = ""; // Path to the file provided by the user

        // Loop until the file is successfully loaded or the user chooses to quit
        while (!fileLoaded) {
            System.out.println("Please enter the filename to read, or press q to quit:");
            String userInput = myScan.nextLine();

            // Check if the user wants to quit
            if (userInput.equalsIgnoreCase("q")) {
                return;
            }

            filePath = userInput; // Capture the file path

            // Attempt to read the file to verify its existence
            try (BufferedReader buffReader = new BufferedReader(new FileReader(filePath))) {
                System.out.println("File read successfully!");
                fileLoaded = true; // File loaded successfully
            } catch (FileNotFoundException e) {
                System.out.println("File not found, try again.");
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file: " + e.getMessage());
            }
        }

        // Process the file to add employees to the list
        try (BufferedReader buffReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = buffReader.readLine()) != null) {
                String[] lineData = line.split(","); // Split the line into data elements

                // For this example the file support adding name, role, and department
                // If the file just contains names, default values will be initialized for role
                // and department.
                // Extract employee details: name, role, and department
                String name = lineData[0];
                String role = lineData.length > 1 ? lineData[1].trim() : "noRole"; // Default to "noRole" if missing
                String deptName = lineData.length > 2 ? lineData[2].trim() : null; // Default to null if missing
                Department department = createDepartmentFromName(deptName); // Create a Department object
                Employee newEmployee;

                // Determine the role and create the corresponding employee object
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
                        newEmployee = new Employee(name, null); // Default to a generic Employee
                        break;
                }

                // Add the new employee to the list
                employees.add(newEmployee);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the data: " + e.getMessage());
        }
    }

    /**
     * Prompts the user to specify the number of random employees to generate via
     * the API.
     * Validates the input to ensure it's a positive integer within a valid range (1
     * to 5000).
     * Calls the method to generate employees based on the user-provided count.
     *
     * @param myScan Scanner object for user input.
     */
    private static void generateRandomEmployeeByAPI(Scanner myScan) {
        System.out.print("Enter the number of random employees to generate: ");
        int numEmployees = 0; // Number of employees to generate

        // Validate user input to ensure a positive integer within the range [1, 5000]
        // This because the https://randomuser.me/ API supports request up to 5000 user
        // names per request.
        while (numEmployees <= 0 || numEmployees > 5000) {
            if (myScan.hasNextInt()) {
                numEmployees = myScan.nextInt(); // Read input
                myScan.nextLine(); // Consume newline character
                if (numEmployees <= 0) { // Check for non-positive integers
                    System.out.print("Please enter a positive integer: ");
                }
            } else {
                // Handle invalid input (e.g., non-integer values)
                System.out.print("Invalid input. Please enter a positive integer between 1 and 5000: ");
                myScan.next(); // Consume invalid input
            }
        }

        // Call the method to generate random employees via the API
        generateRandomEmployees(numEmployees, myScan);
    }

    /**
     * Fetches random employee data from the RandomUser API and adds them to the
     * employees list.
     * Uses XML parsing to process the API response, randomly assigns roles, and
     * adds the generated
     * employees to the global employee list.
     *
     * @param count  The number of random employees to generate.
     * @param myScan Scanner object for displaying results in pages.
     */
    private static void generateRandomEmployees(int count, Scanner myScan) {
        HttpURLConnection connection = null;
        List<Employee> randomEmployees = new ArrayList<>(); // List to hold the generated employees.

        try {
            System.out.println("Sending the request for " + count + " random employees.");
            System.out.println("Please wait...");

            // Construct the API URL for the RandomUser API, requesting 'count' users in XML
            // format.
            String apiUrl = "https://randomuser.me/api/?results=" + count + "&?nat=ie&format=xml";
            URL url = new URL(apiUrl);

            // Open a connection to the API
            connection = (HttpURLConnection) url.openConnection();

            // Set the HTTP request method to GET
            connection.setRequestMethod("GET");

            // Get the HTTP response code to check the status of the request
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { // If the response code indicates success
                // Set up an XML parser to parse the API response
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(connection.getInputStream());
                doc.getDocumentElement().normalize();

                // Extract all <results> nodes from the XML response
                NodeList resultsList = doc.getElementsByTagName("results");

                if (resultsList.getLength() > 0) {
                    // Iterate over each <results> element, excluding the last one (request info)
                    for (int i = 0; i < resultsList.getLength() - 1; i++) {
                        Node resultsNode = resultsList.item(i);
                        if (resultsNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element resultsElement = (Element) resultsNode;

                            // Extract the <name> element containing the first and last names
                            Element nameElement = (Element) resultsElement.getElementsByTagName("name").item(0);

                            if (nameElement != null) {
                                // Extract first and last names
                                String firstName = nameElement.getElementsByTagName("first").item(0).getTextContent();
                                String lastName = nameElement.getElementsByTagName("last").item(0).getTextContent();
                                String name = firstName + " " + lastName;

                                // Randomly assign a role to the new employee (Employee or Manager)
                                Random random = new Random();
                                int roleType = random.nextInt(2); // 0 for Employee, 1 for Manager
                                Employee newEmployee;
                                Department department = selectRandomDepartment(); // Assign a random department.

                                if (roleType == 0) { // Assign an Employee role
                                    EmployeeType[] employeeTypes = EmployeeType.values();
                                    EmployeeType employeeType;

                                    // Ensure the "BACK" option is not selected
                                    do {
                                        employeeType = employeeTypes[random.nextInt(employeeTypes.length)];
                                    } while (employeeType == EmployeeType.BACK);

                                    // Create a new Employee
                                    newEmployee = createEmployee(name, employeeType, department);
                                } else { // Assign a Manager role
                                    ManagerType[] managerTypes = ManagerType.values();
                                    ManagerType managerType;

                                    // Ensure the "BACK" option is not selected
                                    do {
                                        managerType = managerTypes[random.nextInt(managerTypes.length)];
                                    } while (managerType == ManagerType.BACK);

                                    // Create a new Manager
                                    newEmployee = createManager(name, managerType, department);
                                }

                                // Add the newly created employee to the global list and the local result list
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
            // Ensure the HTTP connection is closed properly
            try {
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception ex) {
                System.out.println("An error occurred while closing resources: " + ex.getMessage());
            }
        }

        // Display the generated employees in a paginated format
        printEmployeesList(randomEmployees, myScan);
    }

    /**
     * Selects a random department from the available `DepartmentType` enum values,
     * excluding the "BACK" option.
     * Creates and returns a new instance of the corresponding department class
     * (e.g., `Cardiology`, `Emergency`).
     *
     * @return A randomly selected `Department` object, or `null` if no valid
     *         department is selected.
     */
    private static Department selectRandomDepartment() {
        Random random = new Random();

        // Generate a random index, excluding the last "BACK" option
        int index = random.nextInt(DepartmentType.values().length - 1);

        // Retrieve the selected DepartmentType using the random index
        DepartmentType selectedDept = DepartmentType.values()[index];

        // Return a corresponding Department instance based on the selected
        // DepartmentType
        switch (selectedDept) {
            case CARDIOLOGY:
                return new Cardiology();
            case EMERGENCY:
                return new Emergency();
            case PEDIATRICS:
                return new Pediatrics();
            default:
                return null; // Default case if no valid department is selected
        }
    }

    /**
     * Sorting the employees is important because we are implementing binary search,
     * and one requirement for it is that the list must be ordered.
     * Plus, sorting the list offer the system the possibility to keep the list tidy
     * for
     * display the list.
     * Sorts the list of employees by their names in ascending order using the merge
     * sort algorithm.
     * If the employee list is null or contains one or fewer elements, the method
     * exits without sorting.
     */
    private static void sortEmployees() {
        // Check if the employees list is null or has one or fewer elements
        if (employees == null || employees.size() <= 1) {
            System.out.println("Employee list is empty or has only one employee.");
            return; // Exit early if no sorting is needed
        }

        // Perform merge sort on the employees list
        employees = mergeSort(employees);

        // Confirm sorting completion
        System.out.println("Employees have been sorted by name.");
    }

    /**
     * Recursively sorts a list of employees by their names using the merge sort
     * algorithm.
     * The method divides the list into smaller sublists, sorts them, and merges the
     * results.
     *
     * @param employeeList The list of employees to be sorted.
     * @return A sorted list of employees by name in ascending order.
     */
    private static List<Employee> mergeSort(List<Employee> employeeList) {
        // Base case: if the list has one or no elements, it is already sorted
        if (employeeList.size() <= 1) {
            return employeeList;
        }

        // Find the midpoint of the list
        int mid = employeeList.size() / 2;

        // Split the list into two halves
        List<Employee> left = new ArrayList<>(employeeList.subList(0, mid));
        List<Employee> right = new ArrayList<>(employeeList.subList(mid, employeeList.size()));

        // Recursively sort each half
        left = mergeSort(left);
        right = mergeSort(right);

        // Merge the sorted halves and return the result
        return merge(left, right);
    }

    /**
     * Merges two sorted lists of employees into a single sorted list.
     * The merge is performed based on employee names in ascending order,
     * case-insensitive.
     *
     * @param left  The first sorted list of employees.
     * @param right The second sorted list of employees.
     * @return A merged and sorted list of employees.
     */
    private static List<Employee> merge(List<Employee> left, List<Employee> right) {
        // Initialize the merged list and indices for traversing the left and right
        // lists
        List<Employee> merged = new ArrayList<>();
        int leftIndex = 0, rightIndex = 0;

        // Compare elements from both lists and add the smaller element to the merged
        // list
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).getName().compareToIgnoreCase(right.get(rightIndex).getName()) <= 0) {
                merged.add(left.get(leftIndex));
                leftIndex++; // Move to the next element in the left list
            } else {
                merged.add(right.get(rightIndex));
                rightIndex++; // Move to the next element in the right list
            }
        }

        // Add any remaining elements from the left list
        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex));
            leftIndex++;
        }

        // Add any remaining elements from the right list
        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex));
            rightIndex++;
        }

        return merged; // Return the merged and sorted list
    }

    private static void searchEmployee(Scanner myScan) {
        if (employees == null || employees.size() <= 1) {
            System.out.println("Employee list is empty or has only one employee.");
            return;
        }

        System.out.print("Enter the name of the employee to search: ");
        String searchName = myScan.nextLine().trim();

        // Ensure the employee list is sorted before performing binary search
        sortEmployees();

        // Perform binary search
        int index = binarySearch(employees, searchName);
        if (index != -1) {
            Employee foundEmployee = employees.get(index);
            System.out.println("\nEmployee found:");
            // Print Employee details
            printEmployeeDetails(foundEmployee);

        } else {
            System.out.println("Employee with name '" + searchName + "' not found.");
        }
    }

    /**
     * Performs a binary search on a sorted list of employees to find the index of
     * an employee with the specified name.
     * This method assumes that the list is already sorted by employee names.
     *
     * @param employeeList The list of employees to search.
     * @param targetName   The name of the employee to search for.
     * @return The index of the employee in the list if found; -1 if not found.
     */
    private static int binarySearch(List<Employee> employeeList, String targetName) {
        // Initialize the search boundaries
        int low = 0;
        int high = employeeList.size() - 1;

        // Continue searching as long as the lower bound is less than or equal to the
        // upper bound
        while (low <= high) {
            // Calculate the middle index
            int mid = (low + high) / 2;

            // Retrieve the employee at the middle index
            Employee midEmployee = employeeList.get(mid);

            // Compare the middle employee's name with the target name, ignoring case
            int comparison = midEmployee.getName().compareToIgnoreCase(targetName);

            // If the names match, return the index
            if (comparison == 0) {
                return mid;
            }
            // If the middle name is lexicographically less than the target, adjust the
            // lower bound
            else if (comparison < 0) {
                low = mid + 1;
            }
            // If the middle name is lexicographically greater than the target, adjust the
            // upper bound
            else {
                high = mid - 1;
            }
        }

        // If the loop ends without finding the target, return -1 to indicate not found
        return -1;
    }

    /**
     * Displays a menu to add a new employee. The user is prompted to enter the
     * employee's name and
     * choose whether the employee is a manager or staff. The method also validates
     * the input and ensures
     * that duplicate employee names are avoided.
     *
     * @param myScan A Scanner object for reading user input.
     */
    private static void addEmployeeMenu(Scanner myScan) {
        // Variable to store the validity of the entered name
        boolean isValidName = false;
        String name = "";

        // Prompt the user to enter a valid employee name
        do {
            System.out.println("Please insert employee Name: ");
            String input = myScan.nextLine();

            // Validate that the input does not contain numbers and is not empty
            // regex: ".*\\d.*" checks is there is just digits in the input
            if (!input.matches(".*\\d.*") && !input.isEmpty()) {
                // Check if an employee with the same name already exists
                if (employeeExistsByName(input)) {
                    System.out
                            .println("An employee with the name '" + input + "' already exists. Duplication avoided.");
                    return; // Exit to the main menu
                }
                isValidName = true;
                name = input;
            } else {
                System.out.println("ERROR: Invalid input. Please type letters only.");
            }
        } while (!isValidName);

        // Variable to store the selected employee category (Manager or Staff)
        EmployeeCategory selectOption = null;
        do {
            // Display the employee category menu
            displayEmployeeCategoryMenu();

            // Validate the input for selecting an option
            while (!myScan.hasNextInt()) {
                System.out.println("Please select integers only from the options");
                myScan.next();
            }

            int option = myScan.nextInt();
            myScan.nextLine(); // Consume newline

            selectOption = EmployeeCategory.getValue(option);

            if (selectOption == null) {
                System.out.println("Please select from all the available options");
            } else {
                // Process the selected option
                switch (selectOption) {
                    case MANAGER:
                        // Add a manager
                        addManager(name, myScan);
                        break;
                    case STAFF:
                        // Add a staff member
                        addStaff(name, myScan);
                        break;
                    case BACK:
                        // Return to the previous menu
                        break;
                    default:
                        System.out.println("Please select a valid option.");
                        break;
                }
                break; // Exit the loop after processing the selection
            }
        } while (selectOption != EmployeeCategory.BACK);
    }

    /**
     * Checks if an employee with the given name already exists in the employee
     * list.
     * This method performs a case-insensitive comparison to avoid duplicate
     * entries.
     *
     * @param name The name of the employee to check for duplicates.
     * @return {@code true} if an employee with the same name exists; {@code false}
     *         otherwise.
     */
    private static boolean employeeExistsByName(String name) {
        // Iterate through the list of employees
        for (Employee employee : employees) {
            // Compare the given name with the employee's name (case-insensitive)
            if (employee.getName().equalsIgnoreCase(name)) {
                return true; // Duplicate found
            }
        }
        // No duplicate found
        return false;
    }

    /**
     * Adds a staff member to the employee list.
     * This method allows the user to select the role and department for the new
     * staff member.
     *
     * @param name   The name of the staff member to be added.
     * @param myScan The {@link Scanner} object for reading user input.
     */
    private static void addStaff(String name, Scanner myScan) {
        int option = 0; // Variable to hold the user's menu selection.
        do {
            // Display the menu for selecting an employee type (e.g., Doctor, Nurse, etc.).
            displayEmployeeTypeMenu();

            // Validate that the user inputs an integer.
            while (!myScan.hasNextInt()) {
                System.out.println("Please select integers only from the options");
                myScan.next(); // Consume invalid input.
            }

            // Read the user's selection and map it to an EmployeeType.
            option = myScan.nextInt();
            myScan.nextLine(); // Consume newline character.
            EmployeeType selectOption = EmployeeType.getValue(option);

            if (selectOption == null) {
                System.out.println("Please select from all the available options");
            } else if (selectOption == EmployeeType.BACK) {
                // Exit the menu if the user selects the "Back" option.
                break;
            } else {
                // Allow the user to select a department for the new staff member.
                Department department = selectDepartment(myScan);
                if (department == null) {
                    System.out.println("Department selection canceled.");
                    break; // Exit if the department selection is canceled.
                }

                // Create a new Employee object based on the provided name, role, and
                // department.
                Employee newEmployee = createEmployee(name, selectOption, department);
                if (newEmployee != null) {
                    // Add the new employee to the employee list and confirm the addition.
                    employees.add(newEmployee);
                    System.out.println("Employee added successfully.");
                    // Display the details of the newly added employee.
                    printEmployeeDetails(newEmployee);
                }
                break; // Exit after successfully adding the employee.
            }
        } while (option != 4); // Loop until the user selects the "Back" option.
    }

    /**
     * Adds a manager to the employee list.
     * This method allows the user to select the manager's role and department.
     *
     * @param name   The name of the manager to be added.
     * @param myScan The {@link Scanner} object for reading user input.
     */
    private static void addManager(String name, Scanner myScan) {
        int option = 0; // Variable to hold the user's menu selection.
        do {
            // Display the menu for selecting a manager type (e.g., Nursing Manager, Chief
            // Medical Officer, etc.).
            displayManagerTypeMenu();

            // Validate that the user inputs an integer.
            while (!myScan.hasNextInt()) {
                System.out.println("Please select integers only from the options");
                myScan.next(); // Consume invalid input.
            }

            // Read the user's selection and map it to a ManagerType.
            option = myScan.nextInt();
            myScan.nextLine(); // Consume newline character.
            ManagerType selectOption = ManagerType.getValue(option);

            if (selectOption == null) {
                // Notify the user of an invalid selection.
                System.out.println("Please select from all the available options");
            } else if (selectOption == ManagerType.BACK) {
                // Exit the menu if the user selects the "Back" option.
                break;
            } else {
                // Allow the user to select a department for the manager.
                Department department = selectDepartment(myScan);
                if (department == null) {
                    System.out.println("Department selection canceled.");
                    break; // Exit if the department selection is canceled.
                }

                // Create a new Manager object based on the provided name, role, and department.
                Manager newManager = createManager(name, selectOption, department);
                if (newManager != null) {
                    // Add the new manager to the employee list and confirm the addition.
                    employees.add(newManager);
                    System.out.println("Manager added successfully.");
                    // Display the details of the newly added manager.
                    printEmployeeDetails(newManager);
                }
                break; // Exit after successfully adding the manager.
            }
        } while (option != 4); // Loop until the user selects the "Back" option.
    }

    /**
     * Allows the user to select a department from a menu of department options.
     * Returns the selected {@link Department} object or null if the selection is
     * invalid or canceled.
     *
     * @param myScan The {@link Scanner} object for reading user input.
     * @return The selected {@link Department} object, or null if no valid selection
     *         is made.
     */
    private static Department selectDepartment(Scanner myScan) {
        System.out.println("\nSelect a Department:");
        // Display the department options using the predefined menu.
        displayDepartmentTypeMenu();

        DepartmentType selectedDept = null; // Holds the selected department type.
        while (selectedDept == null) {
            if (myScan.hasNextInt()) {
                // Read the user's selection and map it to a DepartmentType.
                int option = myScan.nextInt();
                myScan.nextLine(); // Consume newline character.
                selectedDept = DepartmentType.getValue(option);
                // Check for invalid selection or cancellation.
                if (selectedDept == null || selectedDept == DepartmentType.BACK) {
                    System.out.println("Invalid selection or cancelled. Returning to previous menu.");
                    return null; // Return null to indicate cancellation or invalid input.
                }
            } else {
                // Notify the user of invalid input and prompt again.
                System.out.print("Invalid input. Please enter a number: ");
                myScan.next(); // Consume invalid input.
            }
        }

        // Create and return a new Department object based on the selected department
        // type.
        return new Department(selectedDept.getStringValue());
    }

    /**
     * Creates a {@link Department} object based on the provided department name.
     * Matches the input string to predefined department names, returning a specific
     * department object.
     * If the input does not match any predefined names, a generic
     * {@link Department} is created.
     *
     * @param deptName The name of the department as a {@String}.
     * @return A specific {@link Department} object (e.g., {@link Cardiology},
     *         {@link Emergency}, {@link Pediatrics}),
     *         or a generic {@link Department} if the name does not match predefined
     *         options.
     */
    private static Department createDepartmentFromName(String deptName) {
        // Return null if the input is null or empty.
        if (deptName == null || deptName.isEmpty()) {
            return null;
        }

        // Match the department name to predefined cases (case-insensitive).
        switch (deptName.toLowerCase()) {
            case "cardiology":
                return new Cardiology();
            case "emergency":
                return new Emergency();
            case "pediatrics":
                return new Pediatrics();
            default:
                // Return a generic Department object for unrecognized department names.
                return new Department(deptName);
        }
    }

    /**
     * Creates an {@link Employee} object based on the provided
     * {@link EmployeeType}.
     * Returns a specific subclass of {@link Employee} (e.g., {@link Doctor},
     * {@link Nurse}, or {@link AdministrativeStaff})
     * depending on the provided {@link EmployeeType}.
     *
     * @param name         The name of the employee as a {@link String}.
     * @param employeeType The {@link EmployeeType} representing the role of the
     *                     employee.
     * @param department   The {@link Department} to which the employee belongs.
     * @return A specific {@link Employee} object (e.g., {@link Doctor},
     *         {@link Nurse}, or {@link AdministrativeStaff}),
     *         or {@code null} if the provided {@link EmployeeType} is unrecognized.
     */
    private static Employee createEmployee(String name, EmployeeType employeeType, Department department) {
        // Match the EmployeeType to the corresponding Employee subclass.
        switch (employeeType) {
            case DOCTOR:
                return new Doctor(name, department); // Create and return a Doctor object.
            case NURSE:
                return new Nurse(name, department); // Create and return a Nurse object.
            case ADMINISTRATIVE_STAFF:
                return new AdministrativeStaff(name, department); // Create and return an AdministrativeStaff object.
            default:
                // Return null if the EmployeeType does not match any predefined roles.
                return null;
        }
    }

    /**
     * Creates a {@link Manager} object based on the provided {@link ManagerType}.
     * Returns a specific subclass of {@link Manager} (e.g., {@link NursingManager},
     * {@link ChiefMedicalOfficer}, or {@link AdministrativeManager})
     * depending on the provided {@link ManagerType}.
     *
     * @param name        The name of the manager as a {@link String}.
     * @param managerType The {@link ManagerType} representing the role of the
     *                    manager.
     * @param department  The {@link Department} to which the manager belongs.
     * @return A specific {@link Manager} object (e.g., {@link NursingManager},
     *         {@link ChiefMedicalOfficer}, or {@link AdministrativeManager}),
     *         or {@code null} if the provided {@link ManagerType} is unrecognized.
     */
    private static Manager createManager(String name, ManagerType managerType, Department department) {
        // Match the ManagerType to the corresponding Manager subclass.
        switch (managerType) {
            case NURSING_MANAGER:
                return new NursingManager(name, department); // Create and return a NursingManager object.
            case CHIEF_MEDICAL_OFFICER:
                return new ChiefMedicalOfficer(name, department); // Create and return a ChiefMedicalOfficer object.
            case ADMINISTRATIVE_MANAGER:
                return new AdministrativeManager(name, department); // Create and return an AdministrativeManager
                                                                    // object.
            default:
                // Return null if the ManagerType does not match any predefined roles.
                return null;
        }
    }

    /**
     * Allows the user to edit the details of an existing employee in the system.
     * Especially for those employees imported from a file which did contain role,
     * and/or department.
     * The user can choose an employee to edit from a list, and modify the
     * employee's name, role, or department.
     * Changes are applied directly to the employee in the employees list.
     *
     * @param myScan The {@link Scanner} object used for reading user input.
     */
    private static void editEmployee(Scanner myScan) {
        // Check if the employee list is empty
        if (employees.isEmpty()) {
            System.out.println("No employees available to edit.");
            return;
        }

        // Display the list of employees for selection
        System.out.println("\nSelect an employee to edit:");

        // Create an array of IMenuOptionInterface for employees
        IMenuOptionInterface[] employeeOptions = new IMenuOptionInterface[employees.size()];
        for (int i = 0; i < employees.size(); i++) {
            final int index = i;
            final Employee emp = employees.get(i);
            employeeOptions[i] = new IMenuOptionInterface() {
                @Override
                public int getValue() {
                    return index + 1; // Start from 1 for user-friendly indexing
                }

                @Override
                public String getStringValue() {
                    return emp.getName() + " - " + emp.getRole(); // Display employee name and role
                }
            };
        }

        // Display the employee selection menu
        displayMenuOptions(employeeOptions);

        // Get user's selection of employee
        int selectedOption = -1;

        do {
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
        } while (selectedOption == -1 || selectedOption > employees.size());


        int index = selectedOption - 1; // Adjust for zero-based index
        Employee employeeToEdit = employees.get(index);

        // Editing loop: allows the user to choose what to edit
        boolean doneEditing = false;
        do {
            System.out.println("\nEditing Employee: " + employeeToEdit.getName());
            displayMenuOptions(EditOption.values()); // Display the editing options

            int editOptionValue = -1;
            do {
                if (myScan.hasNextInt()) {
                    editOptionValue = myScan.nextInt();
                    myScan.nextLine(); // Consume newline
                    break;
                } else {
                    System.out.print("Invalid input. Please enter a valid option number: ");
                    myScan.next(); // Consume invalid input
                }
            } while (editOptionValue == -1);

            // Convert user selection to an EditOption
            EditOption editOption = EditOption.getValue(editOptionValue);
            if (editOption == null) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            // Handle the user's choice for editing
            switch (editOption) {
                case EDIT_NAME:
                    // Edit the employee's name
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
                    // Edit the employee's role
                    if (employeeToEdit instanceof Manager) {
                        editManagerRole((Manager) employeeToEdit, myScan); // Handle manager role editing
                    } else {
                        editEmployeeRole(employeeToEdit, myScan); // Handle general employee role editing
                    }
                    // Update reference in case the employee object was replaced
                    employeeToEdit = employees.get(index);
                    break;

                case EDIT_DEPARTMENT:
                    // Edit the employee's department
                    Department newDept = selectDepartment(myScan);
                    if (newDept != null) {
                        employeeToEdit.setDepartment(newDept);
                        System.out.println("Department updated successfully.");
                    } else {
                        System.out.println("Department update cancelled.");
                    }
                    break;

                case FINISH_EDITING:
                    // Exit the editing loop
                    doneEditing = true;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!doneEditing);

        // Display the updated employee details
        System.out.println("\nUpdated Employee Details:");
        printEmployeeDetails(employeeToEdit);
    }

    /**
     * Allows the user to update the role of a given employee.
     * Prompts the user to select a new role from the available options and updates
     * the employee record accordingly.
     *
     * @param employee The {@link Employee} object whose role is to be updated.
     * @param myScan   The {@link Scanner} object used for user input.
     */
    private static void editEmployeeRole(Employee employee, Scanner myScan) {
        System.out.println("\nSelect a new role for the employee:");

        // Display available employee roles
        displayMenuOptions(EmployeeType.values());

        EmployeeType newRole = null;

        // Prompt the user until a valid role is selected
        while (newRole == null) {
            if (myScan.hasNextInt()) {
                int option = myScan.nextInt();
                myScan.nextLine(); // Consume newline
                newRole = EmployeeType.getValue(option); // Get corresponding EmployeeType enum

                // Validate the selected role
                if (newRole == null || newRole == EmployeeType.BACK) {
                    System.out.print("Invalid selection. Please select a valid role: ");
                    newRole = null;
                }
            } else {
                System.out.print("Invalid input. Please enter a number: ");
                myScan.next(); // Consume invalid input
            }
        }

        // Create a new Employee instance with the updated role
        Employee updatedEmployee = createEmployee(employee.getName(), newRole, employee.getDepartment());

        // Replace the current employee object with the updated instance in the
        // employees list
        employees.set(employees.indexOf(employee), updatedEmployee);

        // Notify the user of the successful update
        System.out.println("Role updated successfully.");
    }

    /**
     * Allows the user to update the role of a given manager.
     * Prompts the user to select a new manager role from the available options and
     * updates the manager record accordingly.
     *
     * @param manager The {@link Manager} object whose role is to be updated.
     * @param myScan  The {@link Scanner} object used for user input.
     */
    private static void editManagerRole(Manager manager, Scanner myScan) {
        System.out.println("\nSelect a new role for the manager:");

        // Display available manager roles
        displayMenuOptions(ManagerType.values());

        ManagerType newRole = null;

        // Prompt the user until a valid role is selected
        while (newRole == null) {
            if (myScan.hasNextInt()) {
                int option = myScan.nextInt();
                myScan.nextLine(); // Consume newline
                newRole = ManagerType.getValue(option); // Get corresponding ManagerType enum

                // Validate the selected role
                if (newRole == null || newRole == ManagerType.BACK) {
                    System.out.print("Invalid selection. Please select a valid role: ");
                    newRole = null;
                }
            } else {
                System.out.print("Invalid input. Please enter a number: ");
                myScan.next(); // Consume invalid input
            }
        }

        // Create a new Manager instance with the updated role
        Manager updatedManager = createManager(manager.getName(), newRole, manager.getDepartment());

        // Replace the current manager object with the updated instance in the employees
        // list
        employees.set(employees.indexOf(manager), updatedManager);

        // Notify the user of the successful update
        System.out.println("Role updated successfully.");
    }

    /**
     * Prints the list of employees in a paginated format.
     * Displays employee details including name, role, and department. Supports
     * navigation through pages.
     *
     * @param employees The list of {@link Employee} objects to display.
     * @param scanner   The {@link Scanner} object for user input to navigate
     *                  through pages.
     */
    private static void printEmployeesList(List<Employee> employees, Scanner scanner) {
        // Number of employees displayed per page (20 by default but might me more)
        int pageSize = 20;

        // Calculate the total number of employees and pages
        int totalEmployees = employees.size();
        int totalPages = (totalEmployees + pageSize - 1) / pageSize;

        // Define the format string for consistent column alignment
        // for a better user experience.
        String format = "| %-20s | %-25s | %-20s |\n";

        // Loop through each page
        for (int page = 0; page < totalPages; page++) {
            int start = page * pageSize; // Starting index of the current page
            int end = Math.min(start + pageSize, totalEmployees); // Ending index of the current page

            // Print page header
            System.out.println("\nPage " + (page + 1) + " of " + totalPages);
            System.out.println(
                    "----------------------------------------------------------------------------------------");
            System.out.printf(format, "Name", "Role", "Department");
            System.out.println(
                    "----------------------------------------------------------------------------------------");

            // Print employee details for the current page
            for (int i = start; i < end; i++) {
                Employee employee = employees.get(i);
                String name = employee.getName();
                String role = employee.getRole();
                String departmentName = (employee.getDepartment() != null) ? employee.getDepartment().getDeptName()
                        : "No Department";
                System.out.printf(format, name, role, departmentName);
            }
            System.out.println(
                    "----------------------------------------------------------------------------------------");

            // If not on the last page, prompt user for input to continue or quit
            if (page < totalPages - 1) {
                System.out.print("Press Enter to see the next page, or type 'q' to quit: ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("q")) {
                    break; // Exit pagination if the user chooses to quit
                }
            }
        }
    }

    /**
     * Prints the details of a single employee.
     * Displays the employee's name, role, and department in a tabular format.
     *
     * @param employee The {@link Employee} object whose details are to be
     *                 displayed.
     */
    private static void printEmployeeDetails(Employee employee) {
        // Define the format string for consistent column alignment
        // Same format idea that printEmployeesList() method.
        String format = "| %-20s | %-25s | %-20s |\n";

        // Print the header
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf(format, "Name", "Role", "Department");
        System.out.println("----------------------------------------------------------------------------------------");

        // Extract employee details
        String name = employee.getName();
        String role = employee.getRole();
        String departmentName = (employee.getDepartment() != null) ? employee.getDepartment().getDeptName()
                : "No Department";

        // Print the employee's details
        System.out.printf(format, name, role, departmentName);

        // Print the footer
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Displays a menu of options for the user to choose from.
     * The options are dynamically generated based on the provided array of
     * {@link IMenuOptionInterface} objects.
     * In this way we can display whatever menu using different enums.
     * 
     * @param options An array of {@link IMenuOptionInterface} objects representing
     *                the menu options to display.
     */
    private static void displayMenuOptions(IMenuOptionInterface[] options) {
        // Define the format string for consistent column alignment
        String format = "| %-2d: %-35s |\n";

        // Print the menu header
        System.out.println("\nPlease select an option:");
        System.out.println("---------------------------------------------------");

        // Iterate through each option and print its value and description
        for (IMenuOptionInterface option : options) {
            System.out.printf(format, option.getValue(), option.getStringValue());
        }

        // Print the menu footer
        System.out.println("---------------------------------------------------");

        // Prompt the user to select an option
        System.out.print("Option: ");
    }

    /**
     * Displays the main menu options to the user by the {@link displayMenuOptions}
     * method.
     * The menu options are based on the {@link MenuOption} enum.
     */
    private static void displayMenu() {
        // Passes the array of MenuOption enum values to the displayMenuOptions method
        // for rendering.
        displayMenuOptions(MenuOption.values());
    }

    /**
     * Displays the employee category menu to the user.
     * Utilizes the {@link displayMenuOptions} method with the
     * {@link EmployeeCategory} enum.
     */
    private static void displayEmployeeCategoryMenu() {
        // Displays options for Employee categories such as Manager, Staff, etc.
        displayMenuOptions(EmployeeCategory.values());
    }

    /**
     * Displays the employee type menu to the user.
     * Utilizes the {@link displayMenuOptions} method with the {@link EmployeeType}
     * enum.
     */
    private static void displayEmployeeTypeMenu() {
        // Displays options for specific Employee types such as Doctor, Nurse, etc.
        displayMenuOptions(EmployeeType.values());
    }

    /**
     * Displays the manager type menu to the user.
     * Utilizes the {@link displayMenuOptions} method with the {@link ManagerType}
     * enum.
     */
    private static void displayManagerTypeMenu() {
        // Displays options for specific Manager types such as Nursing Manager,
        // Administrative Manager, etc.
        displayMenuOptions(ManagerType.values());
    }

    /**
     * Displays the department type menu to the user.
     * Utilizes the {@link displayMenuOptions} method with the
     * {@link DepartmentType} enum.
     */
    private static void displayDepartmentTypeMenu() {
        // Displays options for departments such as Cardiology, Pediatrics, etc.
        displayMenuOptions(DepartmentType.values());
    }

    /**
     * Displays the menu for generating random employees.
     * Utilizes the {@link displayMenuOptions} method with the
     * {@link GenerateEmployeeOption} enum.
     */
    private static void displayGenerateEmployeeMenu() {
        // Displays options for generating employees via file or API.
        displayMenuOptions(GenerateEmployeeOption.values());
    }
}
