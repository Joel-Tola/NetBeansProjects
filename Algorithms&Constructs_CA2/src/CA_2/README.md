# Hospital Employee Management System

## Table of Contents
* **[Introduction](#Introduction)**
* **[Features](#Features)**
* **[Technical Details](#Technical-Details)**
* **[Data Structures](#Data-Structures)**
* **[Algorithms](#Algorithms)**
* **[Logic and Implementation](#Logic-and-Implementation)**
* **[Class and Method References](#Class-and-Method-References)**
* **[Main Class](#Main-Class)**
* **[Employee Management](#Employee-Management)**
* **[Random Employee Generation](#Random-Employee-Generation)**
* **[Sorting Employees](#Sorting-Employees)**
* **[Searching Employees](#Searching-Employees)**
* **[Editing Employees](#Editing-Employees)**
* **[Displaying Employees with Pagination](#Displaying-Employees-with-Pagination)**
* **[Enums and Interfaces](#Enums-and-Interfaces)**
* **[External API Integration](#External-API-Integration)**
* **[Conclusion](#Conclusion)**

### Introduction
The System is a Java-based console application, in their first steps, designed to manage employees in a hospital setting. It allows users to add, edit, sort, search, and generate employees either from a file or via an API. The application utilizes object-oriented programming principles, data structures, and algorithms to provide efficient and user-friendly functionality.

### Features
1. *Add Employee*: Add new employees (staff or managers) with specific roles and departments.
2. *Generate Random Employees*:
        * By Text File: Load employees from a user-specified text file.
        * By API: Fetch random user data from an external API([https://randomuser.me/documentation]) to generate employees random names. And implementing internal logic to add random roles and departments.
3. *Sort Employees*: Sort the employee list alphabetically by name using the _Merge Sort_ algorithm.
4. *Search Employee*: Search for an employee by name using the _Binary Search_ algorithm.
5. *Edit Existing Employee*: Edit an employee's name, role, and department.
6. *Display Employees with Pagination*: Display employee _lists in pages of 20 entries_ to improve readability.
7. *Menu-Driven Interface*: Navigate through the application using a menu system implemented with enums and interfaces.

### Technical Details
#### Data Structures
1. *List of Employees*: Uses List<Employee> employees (an ArrayList) to store employee objects dynamically.
2. *Enums* Implementing MenuOptionInterface: Enums are used for menu options, roles, departments, and other categorizations to enhance code clarity and maintainability.
3. *Classes and Inheritance*: Utilizes a class hierarchy with base classes and subclasses to represent different types of employees and departments.

#### Algorithms
1. *Merge Sort* (mergeSort and merge methods):
    Purpose: Sort employees alphabetically by name.
    Complexity: O(n log n).
    Implementation: Recursively divides the list and merges sorted halves.
2. *Binary Search* (binarySearch method):
    Purpose: Efficiently search for an employee by name in a sorted list.
    Complexity: O(log n).
    Implementation: Compares the target with the middle element and narrows the search range accordingly.
    
#### Logic and Implementation
*Menu System*: Implements a menu-driven interface using enums and the MenuOptionInterface. The displayMenuOptions method is used to display various menus.

*Adding Employees*: Validates input, ensures uniqueness of names, and allows the selection of roles and departments.

*Editing Employees*: Enables modification of an employee's name, role, and department. Role changes involve creating a new instance due to sub-classing.

*Random Employee Generation*: Provides options to generate employees from a file or via an API. Random roles and departments are assigned internally.

*Displaying Employees with Pagination*: Enhances readability by showing 20 employees per page and allowing the user to navigate through pages.

### Class and Method References
#### Main Class
File: [Main.java]

public static void main(String[] args): The entry point of the application. Handles the main menu loop and user interactions.

#### Employee Management
##### Adding Employees
* _Method_: addEmployeeMenu(Scanner myScan)
* _Description_: Handles adding new employees, including input validation and role/department selection.

* _Method_: addStaff(String name, Scanner myScan)
* _Description_: Handles adding staff members.

_Method_: addManager(String name, Scanner myScan)
_Description_: Handles adding managers.

###### Checking for Duplicate Names
* _Method_: employeeExistsByName(String name)
* _Description_: Checks if an employee with the given name already exists.

##### Random Employee Generation
###### Generating Employees from File
* _Method_: generateRandomEmployeeByFileName(Scanner myScan)
* _Description_: Prompts for a filename and loads employees from the specified file.

###### Generating Employees via API
_Method_: generateRandomEmployeeByAPI(Scanner myScan)
_Description_: Prompts for the number of employees and generates them using the Random User API.

_Method_: generateRandomEmployees(int count, Scanner myScan)
_Description_: Fetches random user data from the API and creates employee instances.

### Sorting Employees
_Method_: sortEmployees()
_Description_: Initiates the sorting of the employee list.

_Method_: mergeSort(List<Employee> employeeList)
_Description_: Recursively sorts the employee list using Merge Sort.

_Method_: merge(List<Employee> left, List<Employee> right)
_Description_: Merges two sorted lists.

### Searching Employees
_Method_: searchEmployee(Scanner myScan)
_Description_: Searches for an employee by name using Binary Search.

_Method_: binarySearch(List<Employee> employeeList, String targetName)
_Description_: Performs the binary search algorithm.

### Editing Employees
_Method_: editEmployee(Scanner myScan)
_Description_: Allows editing of an existing employee's details.

_Method_: editEmployeeRole(Employee employee, Scanner myScan)
_Description_: Handles role changes for staff employees.

_Method_: editManagerRole(Manager manager, Scanner myScan)
_Description_: Handles role changes for managers.

### Displaying Employees with Pagination
_Method_: printEmployeesList(List<Employee> employees, Scanner scanner)
_Description_: Displays employees in pages of 20 entries.

_Method_: printEmployeeDetails(Employee newEmployee)
_Description_: Prints details of a single employee.

### Enums and Interfaces
_Interface_: MenuOptionInterface
_Description_: Ensures all menu enums have getValue() and getStringValue() methods.

_Enum_: MenuOption
_Description_: Defines main menu options.

_Enum_: EmployeeCategory
_Description_: Defines categories of employees (Manager or Staff).

_Enum_: ManagerType
_Description_: Defines types of manager roles.

_Enum_: EmployeeType
_Description_: Defines types of staff roles.

_Enum_: DepartmentType
_Description_: Defines available departments.

_Enum_: EditOption
_Description_: Options when editing an employee.

_Enum_: GenerateEmployeeOption
_Description_: Options for generating employees.

### External API Integration
#### API Used: Random User API
_Purpose_: To fetch random user data for generating employee names.

_Methods Involved_:
generateRandomEmployeeByAPI(Scanner myScan) (Lines 189-200)
generateRandomEmployees(int count, Scanner myScan) (Lines 202-257)

_Implementation Details_:
Sends an HTTP GET request to the API endpoint requesting XML data.
Parses the XML response to extract first and last names.
Randomly assigns roles and departments to create employee objects.

### Conclusion
The Employee Management System provides a comprehensive solution for managing hospital employees through a console-based application. By leveraging object-oriented programming, data structures, and algorithms, the system ensures efficient performance and a user-friendly experience. The use of enums and interfaces enhances code maintainability, and the integration of external data sources demonstrates practical application of I/O operations and API usage.

Note: This README provides an overview of the application's features and technical implementation. For more detailed information, refer to the source code comments and method implementations within the AlgorithmsConstructs_CA2.java file.