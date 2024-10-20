/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CA_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

 /**
 * The following program responsible for managing a collection of books and providing
 * functionalities for sorting, searching, and assessing different algorithms on the book data
 * for the first CA in Algorithms module.
 * 
 * The class utilizes three primary features:
 * 1. **Sorting** - Allows users to sort books by title or number of pages in ascending or descending order.
 * 2. **Searching** - Enables searching for books either by title or by the number of pages.
 * 3. **Algorithm Assessment** - Compares the performance of different sorting and searching algorithms
 *    by measuring their execution time on different data sizes.
 * 
 * The book data is stored in two arrays: one for book titles and another for page counts. The program 
 * reads the book data from a CSV file provided by the user.
 * 
 * This class also manages user interaction through a menu system and provides formatted output for
 * sorting and search results.
 * 
 * The class contains static fields and methods to store and process the data globally for ease of access
 * across methods.
 * 
 * Attributes:
 * - `bookTitles`: An array of strings that stores the titles of books.
 * - `bookPages`: An array of integers that stores the corresponding number of pages for each book.
 * - `bookCount`: An integer that represents the total number of books (derived from the data file).
 * - `scanner`: A `Scanner` object for reading user input from the console.
 * - `NANO_TO_MILLIS`: A constant used to convert nanoseconds to milliseconds when measuring time.
 * - `SEPARATOR_LENGTH`: A constant that defines the length of the separator used in printed outputs.
 * - `SEPARATOR_CHAR`: A constant that defines the character used to create visual separators in outputs.
 * - `HEADER_FORMAT`: A constant that defines the format used for printing headers in formatted outputs.
 * - `SEPARATOR`: A constant that defines a precomputed separator string used in formatted output.
 * 
 * The class is structured to be flexible for further expansion, such as adding more search and sorting
 * methods or handling additional book attributes.
 * 
 * @author Joel Tola Soliz
 */
public class JOEL_TOLA_SOLIZ_CA_1 {
    static String[] bookTitles;
    static int[] bookPages;
    static int bookCount;

    static Scanner scanner = new Scanner(System.in);

    private static final double NANO_TO_MILLIS = 1000000.0;
    private static final int SEPARATOR_LENGTH = 86;
    private static final String SEPARATOR_CHAR = "-";
    private static final String HEADER_FORMAT = "%-5s %-60s %5s\n";
    private static final String SEPARATOR = String.join("", Collections.nCopies(SEPARATOR_LENGTH, SEPARATOR_CHAR));

    /**
     * The `main` method is the entry point of the program. It controls the flow of the application by:
     * 1. Prompting the user for the CSV file that contains book data.
     * 2. Reading the data from the file into arrays for book titles and page counts.
     * 3. Invoking the `mainMenu` method to present the user with options to sort, search, or assess algorithms.
     *
     * This method uses helper methods to handle specific tasks such as getting the file name from the user 
     * and reading the file contents. The actual functionality of sorting, searching, and assessment is 
     * delegated to separate methods.
     * 
     * **Advantages:**
     * - Simple and clean structure for initializing the program and managing its overall flow.
     * - Clearly separates concerns by delegating file reading and user interaction to specialized methods.
     * 
     * **Drawbacks:**
     * - No error handling for edge cases like invalid file formats or files with missing data. Although handled 
     *   in `readFile`, no recovery mechanism is in place in case of failure.
     * 
     * **Possible Improvements:**
     * - Add error handling and user feedback for cases where the file read operation fails.
     * - Allow the user to exit the program early if they provide an invalid filename repeatedly.
     * - Provide more detailed instructions or feedback to the user if the file format is invalid.
     */
    public static void main(String[] args) {
        // Prompt the user for the CSV filename
        String fileName = getFilenameFromUser();
        
        // Read the book data into arrays
        readFile(fileName);

        /**
         * After reading the file, we need to ensure that the book data was successfully loaded. 
         * If `bookTitles`, `bookPages`, or `bookCount` are invalid (i.e., `null` or empty), it 
         * indicates that no valid data was loaded, and the program should finish.
         */
        if (bookTitles == null || bookPages == null || bookCount == 0) {
            System.out.println("No valid data loaded. Exiting...");
            return; // Exit the program if no valid data is loaded
        }
        
        // Invoke the main menu to present options (Sort, Search, Assess Algorithms, Exit)
        mainMenu();
    }

    /**
     * The `getFilenameFromUser` method prompts the user to input the name of a CSV file that contains book data.
     * It repeatedly asks the user for a valid filename until the file is found and can be read successfully.
     *
     * This method utilizes a loop that continues until a valid file is provided. The file's existence and 
     * readability are verified using the `fileExists` method. If a valid file is found, the filename is returned.
     *
     * **Advantages:**
     * - Ensures the user provides a valid filename before proceeding, reducing the likelihood of file-related errors later in the program.
     * - Provides continuous prompts until a valid filename is entered.
     * 
     * **Drawbacks:**
     * - The method forces the user to stay in an infinite loop until they provide a valid filename, with no option to exit the loop if they cannot or do not wish to continue.
     * - The error feedback may be more descriptive, so users are not informed about the specific reason why a file could not be read for any reason.
     * 
     * **Possible Improvements:**
     * - Add an exit option where users can type "exit" or "quit" to leave the loop if they do not wish to continue providing filenames.
     *
     * @return String - Returns the filename provided by the user if it exists and can be read.
     * @throws IOException - This method itself does not throw exceptions directly! but delegates the file checking to `fileExists`.
     *                       However, `fileExists` internally handles `IOException` in its logic.
     */
    private static String getFilenameFromUser() {
        String filename;
        // Infinite loop that continues until the user provides a valid file
        while (true) {
            // Prompt the user to enter a filename
            System.out.print("Please enter the CSV filename containing the book data: ");
            filename = scanner.nextLine().trim(); // Read and trim the user's input to remove extra spaces
    
            // Check if the file exists and can be read
            if (fileExists(filename)) {
                break; // Exit the loop if a valid file is found
            } else {
                // Inform the user that the file could not be found or read
                System.out.println("File not found or cannot be read. Please try again.");
            }
        }
        return filename; // Return the valid filename
    }

    /**
     * The `fileExists` method checks if the specified file exists and can be opened for reading. It attempts to open
     * the file using a `BufferedReader`. If the file can be opened without errors, the method returns `true`, indicating
     * that the file exists and is readable. If an `IOException` occurs (e.g., the file does not exist, the file path is
     * incorrect, or the file cannot be read due to permissions), the method returns `false`.
     * 
     * **Advantages:**
     * - Simple and effective way to determine if a file can be accessed before attempting to process it.
     * - Uses a try-catch statement to ensure the BufferedReader is closed automatically after use, preventing resource leaks.
     * 
     * **Drawbacks:**
     * - This method does not distinguish between different causes of failure. It only returns false without further explanations.
     * 
     * **Possible Improvements:**
     * - Log the exception details and/or return specific error messages based on the cause of the `IOException`.
     *
     * @param filename The name of the file to check.
     * @return boolean - Returns `true` if the file exists and can be read, `false` if the file cannot be opened.
     * @throws IOException - This method internally catches `IOException` and returns `false` when an exception occurs. It does not propagate exceptions.
     */
    private static boolean fileExists(String filename) {
        // Try to open the file using a BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // If the file can be opened, return true indicating the file exists and can be read
            return true;
        } catch (IOException e) {
            // If any IOException occurs like file not found, or permission issues, return false
            return false;
        }
    }

    /**
     * The `mainMenu` method displays the main menu of the program and handles user interaction by 
     * allowing them to choose between sorting, searching, assessing algorithms, or exiting the program.
     * 
     * The method presents a set of options to the user, prompting them to input their choice. 
     * Based on the user's input, the appropriate method is called (sortingMenu, searchingMenu, or 
     * assessAlgorithms). If the user selects "EXIT", the program terminates, and the scanner resource 
     * is closed.
     * 
     * **Advantages:**
     * - Keeps the user interaction for the main menu centralized and cleanly separated from other parts of the program.
     * - Uses an infinite loop (do-while) to repeatedly show the menu until the user selects the "EXIT" option.
     * - Validates user input to ensure a valid option is selected.
     * 
     * **Drawbacks:**
     * - If the user repeatedly enters invalid input, they are stuck in a loop with no option to exit except by selecting "EXIT" correctly.
     * 
     * **Possible Improvements:**
     * - Provide more detailed feedback if an invalid option is selected multiple times, such as offering examples of valid input.
     * 
     * @throws InputMismatchException - This method depends on askUserForInt method for input handling, which 
     *                                 may throw an `InputMismatchException` if non-integer input is provided.
     */
    private static void mainMenu() {
        // Define the main menu options as a formatted string
        String menuOptions = "************************** MAIN MENU ******************************* \n" +
                             "\t Options: \n" +
                             "\t 1) SORT \n" +
                             "\t 2) SEARCH \n" +
                             "\t 3) ASSESS ALGORITHMS \n" +
                             "\t 4) EXIT \n" +
                             "********************************************************************\n" +
                             "Your Option: ";
    
        MenuOption option;
    
        // Loop until the user selects the "EXIT" option
        do {
            // Ask the user to input their choice
            int userInput = askUserForInt(menuOptions);
            option = MenuOption.getValue(userInput);
    
            // Handle invalid options
            if (option == null) {
                System.out.println("Invalid option, please select a valid option!");
            } else {
                // Switch-case to handle valid options
                switch (option) {
                    case SORT:
                        sortingMenu(); // Call the sorting menu
                        break;
                    case SEARCH:
                        searchingMenu(); // Call the searching menu
                        break;
                    case ASSESS_ALGORITHMS:
                        assessAlgorithms(); // Call the algorithm assessment menu
                        break;
                    case EXIT:
                        System.out.println("Closing Program... Thank You!");
                        scanner.close(); // Close the scanner resource before exiting
                        break;
                }
            }
        } while (option != MenuOption.EXIT); // Continue the loop until the user selects "EXIT"
    }
    

    /**
     * The `sortingMenu` method presents the user with sorting options for the book data. The user can choose
     * to sort the books by title or by the number of pages. The method then calls `sortingOrderMenu` to further
     * ask the user whether they want to sort in ascending or descending order.
     * 
     * The method uses a `do-while` loop to continuously display the menu until the user selects the "BACK" option,
     * which returns them to the main menu.
     * 
     * **Advantages:**
     * - Provides a simple and clear interface for selecting sorting options.
     * - Keeps the logic for sorting centralized in a dedicated menu, making the code modular and easier to maintain.
     * 
     * **Drawbacks:**
     * - Like in the other menus if the user repeatedly enters invalid input, they are stuck in a loop with no guidance on valid input values.
     * 
     * **Possible Improvements:**
     * - Allow the user to input words like "title", "pages", or "back" instead of just numbers for a more user-friendly experience.
     *
     * @throws InputMismatchException - This method depends on `askUserForInt` for input handling, which 
     *                                 may throw an `InputMismatchException` if non-integer input is provided.
     */
    private static void sortingMenu() {
        // Define the sorting menu options as a formatted string
        String menuOptions = "************************** SORTING MENU ******************************* \n" +
                             "\t Options: \n" +
                             "\t 1) BY TITLE \n" +
                             "\t 2) BY PAGES \n" +
                             "\t 3) BACK \n" +
                             "********************************************************************\n" +
                             "Your Option: ";
    
        SortingOption option;
    
        // Loop until the user selects the "BACK" option
        do {
            // Ask the user to input their choice
            int userInput = askUserForInt(menuOptions);
            option = SortingOption.getValue(userInput);
    
            // Handle invalid options
            if (option == null) {
                System.out.println("Invalid option, please select a valid option!");
            } else {
                // If the user selects "BACK", exit the sorting menu
                if (option == SortingOption.BACK) {
                    return; // Exit the method and return to the main menu
                }
                // Call sortingOrderMenu to ask the user for sorting order (ascending/descending)
                sortingOrderMenu(option);
            }
        } while (true); // Continue displaying the sorting menu until the user chooses "BACK"
    }

    /**
     * The `sortingOrderMenu` method presents the user with options to sort the book data either in ascending
     * or descending order. This method is called after the user has selected a sorting criteria (by title or by pages).
     * 
     * Once the user selects the sorting order, the method performs a sorting operation on a cloned version of 
     * the `bookTitles` and `bookPages` arrays. The sorted results are then displayed to the user, and the time 
     * taken for the sorting operation is measured and printed.
     * 
     * **Advantages:**
     * - Provides a flexible way for users to sort data based on different sorting criteria and in different orders.
     * - Modularizes sorting order selection, separating it from the sorting criterion logic, making the code easier to maintain.
     * 
     * **Drawbacks:**
     * - DITTO: If the user repeatedly enters invalid input, they are stuck in the loop without any guidance on valid input values.
     * 
     * **Possible Improvements:**
     * - Allow users to input keywords like "ascending" or "descending" instead of just numbers.
     *
     * @param sortOption The sorting criterion selected by the user (either by title or by pages).
     * @throws InputMismatchException - This method depends on `askUserForInt` for input handling, which 
     *                                 may throw an `InputMismatchException` if non-integer input is provided.
     */
    private static void sortingOrderMenu(SortingOption sortOption) {
        // Define the sorting order menu options as a formatted string
        String menuOptions = "************************** SORTING ORDER MENU ******************************* \n" +
                             "\t Options: \n" +
                             "\t 1) ASCENDING \n" +
                             "\t 2) DESCENDING \n" +
                             "\t 3) BACK \n" +
                             "********************************************************************\n" +
                             "Your Option: ";
    
        SortingOrder sortingOrder;
    
        // Loop until the user selects "BACK"
        do {
            // Ask the user to input their choice
            int userInput = askUserForInt(menuOptions);
            sortingOrder = SortingOrder.getValue(userInput);
    
            // Handle invalid options
            if (sortingOrder == null) {
                System.out.println("Invalid option, please select a valid option!");
            } else if (sortingOrder == SortingOrder.BACK) {
                return; // Exit the sorting order menu and return to the sorting menu
            } else {
                // Start the sorting process and measure its execution time
                long startTime = System.nanoTime();
                
                // Create clones of the original arrays to avoid modifying the original data
                String[] bookTitlesClone = bookTitles.clone();
                int[] bookPagesClone = bookPages.clone();
    
                // Perform sorting based on the selected criterion and order
                if (sortOption == SortingOption.ByTITLE) {
                    quickSort(bookTitlesClone, bookPagesClone, 0, bookCount - 1, sortingOrder == SortingOrder.ASCENDING);
                } else {
                    quickSort(bookPagesClone, bookTitlesClone, 0, bookCount - 1, sortingOrder == SortingOrder.ASCENDING);
                }
    
                long endTime = System.nanoTime();
    
                // Print a separator for formatting the output
                System.out.println(SEPARATOR);
    
                // Display the sorting result (either by title or by pages, ascending or descending)
                System.out.printf("Sorted by %s - %s\n",
                    (sortOption == SortingOption.ByTITLE ? "Book Title" : "Number of Pages"),
                    (sortingOrder == SortingOrder.ASCENDING ? "Ascending" : "Descending"));
    
                // Print a separator again for formatting
                System.out.println(SEPARATOR);
    
                // Display the sorted books (first 50 results)
                formatAndDisplayBooks(bookTitlesClone, bookPagesClone);
    
                // Display the time taken for the sorting operation
                System.out.println("Sorting took: " + (endTime - startTime) / NANO_TO_MILLIS + " ms");
    
                // Print a separator at the end
                System.out.println(SEPARATOR);
            }
        } while (true); // Continue showing the sorting order menu until the user selects "BACK"
    }
    

    /**
     * The `searchingMenu` method presents the user with options for searching the book data either by title or 
     * by the number of pages. Based on the user's selection, the method calls the `searchBooks` method to perform 
     * the search operation. The user also has the option to go back to the main menu.
     * 
     * The method uses a `do-while` loop to continuously display the search menu until the user selects the "BACK" option.
     * 
     * **Advantages:**
     * - Provides a modular interface for users to choose the search criteria (either by title or by pages).
     * - Keeps the logic for the search menu separate from the actual search functionality, improving code modularity.
     * 
     * **Drawbacks:**
     * - DITTO: If the user repeatedly enters invalid input, they are stuck in the loop without any guidance on valid input values.
     * 
     * **Possible Improvements:**
     * - Allow users to input keywords like "title" or "pages" instead of just numbers for a more user-friendly experience.
     *
     * @throws InputMismatchException - This method depends on `askUserForInt` for input handling, which 
     *                                 may throw an `InputMismatchException` if non-integer input is provided.
     */
    private static void searchingMenu() {
        // Define the search menu options as a formatted string
        String menuOptions = "************************** SEARCH MENU ******************************* \n" +
                             "\t Options: \n" +
                             "\t 1) BY TITLE \n" +
                             "\t 2) BY PAGES \n" +
                             "\t 3) BACK \n" +
                             "********************************************************************\n" +
                             "Your Option: ";
    
        SearchOption option;
    
        // Loop until the user selects "BACK"
        do {
            // Ask the user to input their choice
            int userInput = askUserForInt(menuOptions);
            option = SearchOption.getValue(userInput);
    
            // Handle invalid options
            if (option == null) {
                System.out.println("Invalid option, please select a valid option!");
            } else {
                // If the user selects "BACK", exit the search menu
                if (option == SearchOption.BACK) {
                    return; // Exit the method and return to the main menu
                }
                // Call searchBooks to execute the search based on the selected option
                searchBooks(option);
            }
        } while (true); // Continue displaying the search menu until the user chooses "BACK"
    }

    /**
     * The `searchBooks` method performs a search operation based on the user's input. The user can search
     * for books either by title (*partial match*) or by the number of pages. The method measures the time taken
     * for the search and displays the search results if there is any.
     * 
     * The method first determines whether the user is searching by title or by pages based on the `SearchOption` enum.
     * If the search is by title, the user is prompted to enter a partial title, and the method searches for matching
     * book titles. If the search is by pages, the user is asked to input a specific number of pages to search for.
     * The search results (book index, title, and pages) are then displayed in a formatted table, and the time taken
     * for the search is printed.
     * 
     * **Advantages:**
     * - Provides flexible search functionality, allowing searches by title (partial match) or by pages.
     * - Displays the search results in a clear and formatted way, showing the book index, title, and page count.
     * - Measures the search time and displays it, providing feedback on search performance.
     * 
     * **Drawbacks:**
     * - DITTO: If the user repeatedly enters an empty search query (for title), they are prompted again without an option to exit the loop.
     * - The method assumes that `askUserForInt` will always receive valid numeric input when searching by pages.
     * 
     * **Possible Improvements:**
     * - Add an option to allow the user to cancel the search if they do not wish to continue typing "cancel" during the search.
     * - Improve input validation for the number of pages, such as handling non-integer.
     *
     * @param option The search option selected by the user (either by title or by pages).
     * @throws InputMismatchException - This method relies on `askUserForInt` for searching by pages, which 
     *                                 may throw an `InputMismatchException` if non-integer input is provided.
     */
    private static void searchBooks(SearchOption option) {
        // Start measuring search time
        long startTime = 0;
        List<Integer> matchedIndices = new ArrayList<>(); // List to store matching book indices
    
        // If searching by title
        if (option == SearchOption.ByTITLE) {
            String searchTitle;
            do {
                // Prompt the user for a partial book title
                System.out.println("Please enter the partial title of the book you wish to find:");
                searchTitle = scanner.nextLine().trim();
                if (searchTitle.isEmpty()) {
                    System.out.println("Input cannot be empty. Please try again.");
                }
            } while (searchTitle.isEmpty()); // Keep prompting until a valid title is entered
    

            // Start measuring search time
            startTime = System.nanoTime();

            // Perform a partial title search and store matching indices
            matchedIndices = partialTitleSearch(bookTitles, searchTitle);
    
        // If searching by pages
        } else if (option == SearchOption.ByPAGES) {
            // Ask the user for the number of pages to search for
            int searchPages = askUserForInt("Please enter the number of pages to search for:");

            // Start measuring search time
            startTime = System.nanoTime();

            // Perform a page search and store matching indices
            matchedIndices = partialPageSearch(bookPages, searchPages);
        }
    
        // End measuring search time
        long endTime = System.nanoTime();
    
        // Print separator
        System.out.println(SEPARATOR);
    
        // Display the search results if matches are found
        if (!matchedIndices.isEmpty()) {
            System.out.println("Books found:");
            System.out.printf(HEADER_FORMAT, "Index", "Book Title", "Pages");
            System.out.println(SEPARATOR);
            // Iterate through the matched indices and display each matching book
            for (int index : matchedIndices) {
                System.out.printf(HEADER_FORMAT, index, bookTitles[index], bookPages[index]);
            }
        } else {
            // No books found matching the search criteria
            System.out.println("No books found matching your search criteria.");
        }
    
        // Display the time taken for the search
        System.out.println("Searching took: " + (endTime - startTime) / NANO_TO_MILLIS + " ms");
    }    

    /**
     * The `assessAlgorithms` method evaluates the performance of different sorting and searching algorithms 
     * (Quick Sort, Bubble Sort, Linear Search, and Binary Search) on different data sizes. The method compares
     * the execution times of each algorithm and displays which one performs better for each scenario.
     * 
     * The MOKC_DATA.txt is not being used for the test cases because sample data is being generated to add more scenarios.
     * The method generates sample book data of varying sizes (1000, 5000, 10000 items) and performs the following:
     * - Sorting by title using Quick Sort and Bubble Sort, comparing their performance.
     * - Sorting by the number of pages using Quick Sort and Bubble Sort, comparing their performance.
     * - Searching by title using Linear Search and Binary Search, comparing their performance.
     * 
     * After each set of tests, the method prints the time taken for each algorithm and declares which one performed better.
     * 
     * **Advantages:**
     * - Provides a detailed performance comparison of multiple algorithms on varying data sizes.
     * - Offers useful feedback for understanding the efficiency of different algorithms in sorting and searching tasks.
     * - Displays performance data in a clear format, making it easy to identify the most efficient algorithms.
     * 
     * **Drawbacks:**
     * - The method only tests for specific data sizes (1000, 5000, 10000), which might not represent all possible use cases.
     * - The sample data is randomly generated, which may not represent real-world data distributions.
     * 
     * **Possible Improvements:**
     * - Add support for testing different types of data (e.g., partially sorted, reverse sorted) to assess algorithm performance in various scenarios.
     * - Allow the user to specify custom data sizes for testing.
     * - Add memory usage measurement to compare the algorithms' space efficiency.
     * 
     * @throws IllegalArgumentException - The method assumes the algorithms will function correctly on the sample data.
     *                                   If there are issues (e.g., data inconsistency), an exception may occur during sorting.
     */
    private static void assessAlgorithms() {
        // Inform the user that algorithm assessment is starting
        System.out.println("Assessing Sorting and Searching Algorithms...");
    
        int[] dataSizes = {1000, 5000, 10000}; // Example data sizes for testing
    
        // Loop through each data size for performance assessment
        for (int size : dataSizes) {
            System.out.println("\nData Size: " + size);
    
            // Generate sample data for the test
            String[] sampleTitles = new String[size];
            int[] samplePages = new int[size];
            for (int i = 0; i < size; i++) {
                sampleTitles[i] = "Book " + i; // Create sample titles
                samplePages[i] = (int) (Math.random() * 1000) + 100; // Random page count
            }
    
            // ---- Sorting by Title ----
    
            // Quick Sort (Titles)
            String[] titlesCloneQS = sampleTitles.clone();
            int[] pagesCloneQS = samplePages.clone();
            long startTime = System.nanoTime();
            quickSort(titlesCloneQS, pagesCloneQS, 0, size - 1, true); // Sort in ascending order by title
            long endTime = System.nanoTime();
            double quickSortTimeTitles = (endTime - startTime) / NANO_TO_MILLIS; // Time in milliseconds
    
            // Bubble Sort (Titles)
            String[] titlesCloneBS = sampleTitles.clone();
            int[] pagesCloneBS = samplePages.clone();
            startTime = System.nanoTime();
            bubbleSort(titlesCloneBS, pagesCloneBS, true); // Sort in ascending order by title
            endTime = System.nanoTime();
            double bubbleSortTimeTitles = (endTime - startTime) / NANO_TO_MILLIS;
    
            // Determine better algorithm for sorting by title
            String betterSortTitle = (quickSortTimeTitles < bubbleSortTimeTitles) ? "Quick Sort" : "Bubble Sort";
    
            // ---- Sorting by Pages ----
    
            // Quick Sort (Pages)
            titlesCloneQS = sampleTitles.clone();
            pagesCloneQS = samplePages.clone();
            startTime = System.nanoTime();
            quickSort(pagesCloneQS, titlesCloneQS, 0, size - 1, true); // Sort in ascending order by pages
            endTime = System.nanoTime();
            double quickSortTimePages = (endTime - startTime) / NANO_TO_MILLIS;
    
            // Bubble Sort (Pages)
            titlesCloneBS = sampleTitles.clone();
            pagesCloneBS = samplePages.clone();
            startTime = System.nanoTime();
            bubbleSort(pagesCloneBS, titlesCloneBS, true); // Sort in ascending order by pages
            endTime = System.nanoTime();
            double bubbleSortTimePages = (endTime - startTime) / NANO_TO_MILLIS;
    
            // Determine better algorithm for sorting by pages
            String betterSortPages = (quickSortTimePages < bubbleSortTimePages) ? "Quick Sort" : "Bubble Sort";
    
            // ---- Searching Titles ----
    
            // Linear Search (Titles)
            startTime = System.nanoTime();
            linearSearch(sampleTitles, "Non-Existent Book"); // Search for a non-existent title
            endTime = System.nanoTime();
            double linearSearchTime = (endTime - startTime) / NANO_TO_MILLIS;
    
            // Binary Search (Titles)
            titlesCloneQS = sampleTitles.clone();
            pagesCloneQS = samplePages.clone();
            quickSort(titlesCloneQS, pagesCloneQS, 0, size - 1, true); // Data must be sorted for binary search
            startTime = System.nanoTime();
            binarySearchTitle(titlesCloneQS, "Non-Existent Book");
            endTime = System.nanoTime();
            double binarySearchTime = (endTime - startTime) / NANO_TO_MILLIS;
    
            // Determine better algorithm for searching titles
            String betterSearchTitle = (linearSearchTime < binarySearchTime) ? "Linear Search" : "Binary Search";
    
            // ---- Display Results ----
    
            // Display sorting results for title sorting
            System.out.println("----- Sorting by Title -----");
            System.out.printf("Quick Sort Time: %.2f ms\n", quickSortTimeTitles);
            System.out.printf("Bubble Sort Time: %.2f ms\n", bubbleSortTimeTitles);
            System.out.println("Better Algorithm for Sorting Titles: " + betterSortTitle);
    
            // Display sorting results for page sorting
            System.out.println("\n----- Sorting by Pages -----");
            System.out.printf("Quick Sort Time: %.2f ms\n", quickSortTimePages);
            System.out.printf("Bubble Sort Time: %.2f ms\n", bubbleSortTimePages);
            System.out.println("Better Algorithm for Sorting Pages: " + betterSortPages);
    
            // Display searching results for title searching
            System.out.println("\n----- Searching Titles -----");
            System.out.printf("Linear Search Time: %.6f ms\n", linearSearchTime);
            System.out.printf("Binary Search Time: %.6f ms\n", binarySearchTime);
            System.out.println("Better Algorithm for Searching Titles: " + betterSearchTitle);
        }
    }

    /**
     * The `readFile` method reads the book data from a specified file. It performs two passes over the file:
     * 1. The first pass counts the number of lines in the file to initialize the arrays for storing
     *    book titles and pages.
     * 2. The second pass reads the actual book data and populates the arrays with the book titles and page numbers.
     * 
     * The method expects the file to contain two columns: book titles and page numbers, separated by commas.
     * The book name may contain quotation marks, exclamation mark, or extra commas so and RegEx is implemented to deal with those cases.  
     * Titles are assumed to be in the first column, and the page numbers in the second column.
     * 
     * **Advantages:**
     * - Handles both reading and parsing of the file in a straightforward manner.
     * - Uses a two-pass approach, ensuring that the arrays are allocated with the correct size based on the number of lines in the file.
     * - Catches and handles `IOException` and `NumberFormatException` to provide feedback on errors during file reading and number parsing.
     * 
     * **Drawbacks:**
     * - The method assumes a specific file structure (titles in the first column, pages in the second), which could lead to errors if the file format differs.
     * - No flexibility in handling different file structures or malformed data.
     * - reading the file twice in large files could be inefficiently processed due to the two-pass approach.
     * 
     * **Possible Improvements:**
     * - Add support for configurable column mapping, allowing the user to specify which columns contain titles and page numbers.
     * - Handle files with different delimiters (e.g., tabs or semicolons) by making the delimiter configurable.
     * - Add more robust error handling for missing or malformed data in the file.
     * - We can consider a single-pass approach, or using different data structures to read and count lines in one go, improving efficiency.
     *
     * @param filename The name of the file to read.
     * @throws IOException - This method catches `IOException` internally but prints stack traces for debugging.
     * @throws NumberFormatException - Catches parsing errors when reading page numbers from the file.
     */
    public static void readFile(String filename) {
        bookCount = 0; // Initialize book count
        String line;
        int index = 0; // Track index for populating book arrays
    
        // First pass to count the lines in the file
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
                bookCount++; // Increment the book count for each line
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace for debugging if file reading fails
        }
    
        // Second pass to read and parse the file data
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            bookTitles = new String[bookCount]; // Initialize array for book titles
            bookPages = new int[bookCount]; // Initialize array for book pages
    
            while ((line = br.readLine()) != null) {
                // Split the line by commas, handling quoted values
                String[] bookInfo = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    
                // Store the title and page count in the respective arrays
                bookTitles[index] = bookInfo[0].replaceAll("\"", "").trim(); // Remove quotes and trim whitespace
                bookPages[index] = Integer.parseInt(bookInfo[1].trim()); // Parse and store the page number
    
                index++; // Move to the next index
            }
            br.close(); // Close the BufferedReader
            System.out.println("File read successfully"); // Inform the user of success
    
        } catch (IOException e) {
            // Handle file reading errors
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            // Handle number parsing errors (e.g., if the page count is not a valid number)
            System.out.println("Error parsing number from file: " + e.getMessage());
            System.exit(1); // Exit the program if there's a critical parsing error
        }
    }    

    /**
     * The `bubbleSort` method sorts an array of book titles and their corresponding page numbers 
     * using the Bubble Sort algorithm. It sorts the titles in either ascending or descending order 
     * based on the user's input and adjusts the page numbers to stay aligned with their respective titles.
     * This algorithm is is used just in Assess Algorithms part to improve performance.
     * 
     * **Algorithm Explanation:**
     * - Bubble Sort is a simple comparison-based algorithm that repeatedly steps through the list, compares adjacent 
     *   elements, and swaps them if they are in the wrong order. This process is repeated until the list is fully sorted.
     * 
     * The method uses a nested loop to iterate through the array and swap titles and pages based on the chosen order (ascending or descending).
     * 
     * **Advantages:**
     * - Simple and easy-to-understand algorithm.
     * - Works well on small datasets due to its simplicity.
     * 
     * **Drawbacks:**
     * - Inefficient for large datasets (O(n^2) time complexity), making it impractical for sorting large lists.
     * - The method sorts both titles and pages, meaning that any improvements to sorting efficiency would require changes to both.
     * 
     * **Possible Improvements:**
     * - Add a break condition to stop early if the array is already sorted, reducing unnecessary iterations.
     *
     * @param titles An array of book titles to be sorted.
     * @param pages An array of corresponding page numbers, which will be adjusted in alignment with their respective titles.
     * @param ascending A boolean indicating whether to sort in ascending order (`true`) or descending order (`false`).
     */
    public static void bubbleSort(String[] titles, int[] pages, boolean ascending) {
        int n = titles.length; // Number of titles (and pages) in the array
    
        // Outer loop to control the number of passes
        for (int i = 0; i < n - 1; i++) {
            // Inner loop to perform comparisons and swaps within the array
            for (int j = 0; j < n - i - 1; j++) {
                // Determine the sorting condition based on the order (ascending/descending)
                boolean condition = ascending
                    ? titles[j].compareToIgnoreCase(titles[j + 1]) > 0 // Ascending: Swap if current title > next title
                    : titles[j].compareToIgnoreCase(titles[j + 1]) < 0; // Descending: Swap if current title < next title
    
                if (condition) {
                    // Swap titles
                    String tempTitle = titles[j];
                    titles[j] = titles[j + 1];
                    titles[j + 1] = tempTitle;
    
                    // Swap corresponding page numbers
                    int tempPage = pages[j];
                    pages[j] = pages[j + 1];
                    pages[j + 1] = tempPage;
                }
            }
        }
    }    

    /**
     * The `bubbleSort` method sorts an array of book page numbers and their corresponding titles using the Bubble Sort 
     * algorithm. It sorts the page numbers in either ascending or descending order based on the user's input and adjusts 
     * the book titles to stay aligned with their respective page numbers.
     * 
     * **Algorithm Explanation:**
     * - Bubble Sort is a comparison-based algorithm that repeatedly steps through the array, compares adjacent elements 
     *   (in this case, page numbers), and swaps them if they are in the wrong order. This process is repeated until 
     *   the list is fully sorted.
     * 
     * The method uses a nested loop to iterate through the array and swap page numbers and titles based on the chosen order 
     * (ascending or descending).
     * 
     * **Advantages:**
     * - Simple and easy-to-understand algorithm.
     * - Works well on small datasets due to its simplicity.
     * 
     * **Drawbacks:**
     * - DITTO: Inefficient for large datasets due to O(n^2) time complexity, making it impractical for sorting large arrays.
     * - Sorting both pages and titles requires swapping both arrays, adding overhead to the operation.
     * 
     * **Possible Improvements:**
     * - Add a break condition to stop early if the array is already sorted.
     *
     * @param pages An array of book page numbers to be sorted.
     * @param titles An array of corresponding book titles, which will be adjusted in alignment with the page numbers.
     * @param ascending A boolean indicating whether to sort in ascending order (`true`) or descending order (`false`).
     */
    public static void bubbleSort(int[] pages, String[] titles, boolean ascending) {
        int n = pages.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                boolean condition = ascending ? pages[j] > pages[j + 1] : pages[j] < pages[j + 1];

                if (condition) {
                    // Swap pages
                    int tempPage = pages[j];
                    pages[j] = pages[j + 1];
                    pages[j + 1] = tempPage;

                    // Swap titles
                    String tempTitle = titles[j];
                    titles[j] = titles[j + 1];
                    titles[j + 1] = tempTitle;
                }
            }
        }
    }

    /**
     * The `quickSort` method sorts an array of book titles (and their corresponding page numbers) using the Quick Sort 
     * algorithm. It recursively partitions the array into smaller segments, sorts them based on a pivot element, and 
     * combines them to produce a fully sorted array. The sorting can be done in either ascending or descending order 
     * depending on the `ascending` parameter.
     * 
     * **Algorithm Explanation:**
     * - Quick Sort is a divide-and-conquer algorithm that selects a pivot element, partitions the array into two segments 
     *   (one with elements smaller than the pivot and the other with elements larger than the pivot), and recursively sorts 
     *   the segments.
     * 
     * The `partitionTitles` method is responsible for partitioning the array by selecting a pivot (usually the last element) 
     * and ensuring that all titles on the left of the pivot are smaller (or larger, depending on the order) than the pivot, 
     * and those on the right are greater.
     * 
     * **Advantages:**
     * - Quick Sort is much faster than Bubble Sort for large datasets, with an average time complexity of O(n log n).
     * - Efficient for most datasets and performs well on large datasets due to its divide-and-conquer nature.
     * 
     * **Drawbacks:**
     * - Quick Sort's worst-case time complexity is O(n^2), which can occur if the pivot is poorly chosen (e.g., already sorted data without randomization).
     * - Recursive calls can lead to stack overflow for very large arrays.
     * 
     * **Possible Improvements:**
     * - Use randomization or a "median-of-three" strategy to select better pivot elements and reduce the likelihood of 
     *   worst-case performance.
     * - Use an iterative version of Quick Sort to avoid deep recursion and prevent stack overflow.
     *
     * @param titles An array of book titles to be sorted.
     * @param pages An array of corresponding page numbers, which will be adjusted in alignment with the sorted titles.
     * @param low The starting index of the segment to be sorted.
     * @param high The ending index of the segment to be sorted.
     * @param ascending A boolean indicating whether to sort in ascending order (`true`) or descending order (`false`).
     */
    // QuickSort for Titles
    public static void quickSort(String[] titles, int[] pages, int low, int high, boolean ascending) {
        if (low < high) {
            // Partition the array and get the pivot index
            int pi = partitionTitles(titles, pages, low, high, ascending);
            
            // Recursively sort the left side of the pivot
            quickSort(titles, pages, low, pi - 1, ascending);
            
            // Recursively sort the right side of the pivot
            quickSort(titles, pages, pi + 1, high, ascending);
        }
    }

    /**
     * The `partitionTitles` method is responsible for partitioning the array based on the pivot element. It rearranges the 
     * elements so that all titles smaller than the pivot are placed before it, and all titles greater than the pivot are placed 
     * after it (or vice versa for descending order).
     * 
     * It also ensures that the corresponding page numbers are swapped in sync with the titles, maintaining alignment between 
     * the two arrays.
     * 
     * @param titles An array of book titles to be partitioned.
     * @param pages An array of corresponding page numbers, which will be adjusted in alignment with the titles.
     * @param low The starting index of the segment to be partitioned.
     * @param high The ending index of the segment to be partitioned.
     * @param ascending A boolean indicating whether to sort in ascending order (`true`) or descending order (`false`).
     * @return The index of the pivot element after partitioning.
     */
    public static int partitionTitles(String[] titles, int[] pages, int low, int high, boolean ascending) {
        String pivot = titles[high]; // Select the pivot element (usually the last element)
        int i = low - 1; // Pointer for the smaller element
    
        // Loop through the array and compare elements to the pivot
        for (int j = low; j < high; j++) {
            boolean condition = ascending
                ? titles[j].compareToIgnoreCase(pivot) < 0 // Ascending order: Swap if the current title < pivot
                : titles[j].compareToIgnoreCase(pivot) > 0; // Descending order: Swap if the current title > pivot
    
            if (condition) {
                i++; // Increment the smaller element index
    
                // Swap titles
                String tempTitle = titles[i];
                titles[i] = titles[j];
                titles[j] = tempTitle;
    
                // Swap corresponding pages to keep alignment
                int tempPage = pages[i];
                pages[i] = pages[j];
                pages[j] = tempPage;
            }
        }
    
        // Place the pivot element in its correct position
        String tempTitle = titles[i + 1];
        titles[i + 1] = titles[high];
        titles[high] = tempTitle;
    
        // Swap the corresponding pages
        int tempPage = pages[i + 1];
        pages[i + 1] = pages[high];
        pages[high] = tempPage;
    
        return i + 1; // Return the pivot index
    }

    /**
     * The `quickSort` method sorts an array of book page numbers (and their corresponding titles) using the Quick Sort 
     * algorithm. It recursively partitions the array into smaller segments based on the page numbers, sorts them around 
     * a pivot element, and combines them to produce a fully sorted array. The sorting can be done in either ascending or 
     * descending order depending on the `ascending` parameter.
     * 
     * **Algorithm Explanation:**
     * - DITTO: Quick Sort is a divide-and-conquer algorithm that selects a pivot element, partitions the array into two segments 
     *   (one with elements smaller than the pivot and the other with elements larger than the pivot), and recursively sorts 
     *   the segments.
     * 
     * The `partitionPages` method is responsible for partitioning the array by selecting a pivot (usually the last element) 
     * and ensuring that all page numbers on the left of the pivot are smaller (or larger, depending on the order) than the 
     * pivot, and those on the right are greater.
     *
     * @param pages An array of book page numbers to be sorted.
     * @param titles An array of corresponding book titles, which will be adjusted in alignment with the sorted pages.
     * @param low The starting index of the segment to be sorted.
     * @param high The ending index of the segment to be sorted.
     * @param ascending A boolean indicating whether to sort in ascending order (`true`) or descending order (`false`).
     */
    public static void quickSort(int[] pages, String[] titles, int low, int high, boolean ascending) {
        if (low < high) {
            // Partition the array and get the pivot index
            int pi = partitionPages(pages, titles, low, high, ascending);
            
            // Recursively sort the left side of the pivot
            quickSort(pages, titles, low, pi - 1, ascending);
            
            // Recursively sort the right side of the pivot
            quickSort(pages, titles, pi + 1, high, ascending);
        }
    }

    /**
     * The `partitionPages` method is responsible for partitioning the array based on the pivot element. It rearranges the 
     * elements so that all page numbers smaller than the pivot are placed before it, and all page numbers greater than 
     * the pivot are placed after it (or vice versa for descending order).
     * 
     * It also ensures that the corresponding book titles are swapped in sync with the page numbers, maintaining alignment 
     * between the two arrays.
     * 
     * @param pages An array of book page numbers to be partitioned.
     * @param titles An array of corresponding book titles, which will be adjusted in alignment with the pages.
     * @param low The starting index of the segment to be partitioned.
     * @param high The ending index of the segment to be partitioned.
     * @param ascending A boolean indicating whether to sort in ascending order (`true`) or descending order (`false`).
     * @return The index of the pivot element after partitioning.
     */
    public static int partitionPages(int[] pages, String[] titles, int low, int high, boolean ascending) {
        int pivot = pages[high]; // Select the pivot element (last element in the array)
        int i = low - 1; // Pointer for the smaller element
    
        // Loop through the array and compare elements to the pivot
        for (int j = low; j < high; j++) {
            boolean condition = ascending
                ? pages[j] < pivot // Ascending order: Swap if the current page < pivot
                : pages[j] > pivot; // Descending order: Swap if the current page > pivot
    
            if (condition) {
                i++; // Increment the smaller element index
    
                // Swap page numbers
                int tempPage = pages[i];
                pages[i] = pages[j];
                pages[j] = tempPage;
    
                // Swap corresponding titles to keep alignment
                String tempTitle = titles[i];
                titles[i] = titles[j];
                titles[j] = tempTitle;
            }
        }
    
        // Place the pivot element in its correct position
        int tempPage = pages[i + 1];
        pages[i + 1] = pages[high];
        pages[high] = tempPage;
    
        // Swap the corresponding titles
        String tempTitle = titles[i + 1];
        titles[i + 1] = titles[high];
        titles[high] = tempTitle;
    
        return i + 1; // Return the pivot index
    }

    /**
     * The `linearSearch` method is used for assessment and performs a linear search on an array of book titles to find an exact match 
     * for a specified search title. It iterates through the array sequentially, comparing each title with 
     * the search title in a case-insensitive manner.
     * 
     * **Algorithm Explanation:**
     * - Linear Search is a straightforward algorithm that checks each element in the array one by one until 
     *   the target element is found or the end of the array is reached.
     * 
     * This method returns the index of the first occurrence of the search title if found; otherwise, it returns 
     * -1 to indicate that the book was not found.
     * 
     * **Advantages:**
     * - Simple and easy to implement with minimal code.
     * - Does not require the array to be sorted, making it versatile for various datasets.
     * - Efficient for small or unsorted datasets where more complex algorithms do not offer significant benefits.
     * 
     * **Drawbacks:**
     * - Inefficient for large datasets as it may require checking every element, resulting in O(n) time complexity.
     * - Does not take advantage of any potential ordering or structure within the dataset to optimize the search.
     * 
     * **Possible Improvements:**
     * - Incorporate logging to track search operations for debugging or analytics purposes.
     *
     * @param titles An array of book titles to be searched.
     * @param searchTitle The title of the book to search for. The search is case-insensitive.
     * @return The index of the book if found; otherwise, -1 to indicate that the book was not found.
     */
    public static int linearSearch(String[] titles, String searchTitle) {
        for (int i = 0; i < titles.length; i++) {
            if (titles[i].equalsIgnoreCase(searchTitle)) {
                return i; // Exact match found
            }
        }
        return -1; // Book not found
    }

    /**
     * The `binarySearchTitle` method is used for assessment and performs a binary search on a sorted array of book titles to find an exact match for 
     * a specified search title. It uses a divide-and-conquer approach, repeatedly narrowing down the search range by 
     * comparing the middle element of the range with the target title.
     * 
     * **Algorithm Explanation:**
     * - Binary Search is an efficient search algorithm with a time complexity of O(log n). It works by repeatedly 
     *   dividing the search range in half and focusing only on the part of the array that could contain the target element.
     * - The array must be sorted in order for binary search to work correctly.
     * 
     * This method returns the index of the matching title if found; otherwise, it returns -1 to indicate that the book was 
     * not found.
     * 
     * **Advantages:**
     * - Binary Search is much faster than linear search for large datasets, especially if the array is already sorted.
     * - Reduces the number of comparisons required to find the target, making it highly efficient for sorted data.
     * 
     * **Drawbacks:**
     * - Requires the array to be sorted, which may add overhead if the dataset is unsorted.
     * - Only finds exact matches, which may limit its use in cases where partial or fuzzy matches are desired.
     * 
     * **Possible Improvements:**
     * - Handle cases where the array is unsorted by incorporating a sorting step before performing the search.
     *
     * @param titles An array of book titles to be searched. The array must be sorted.
     * @param searchTitle The title of the book to search for. The search is case-insensitive.
     * @return The index of the book if found; otherwise, -1 to indicate that the book was not found.
     */
    public static int binarySearchTitle(String[] titles, String searchTitle) {
        int low = 0, high = titles.length - 1;
    
        // Perform binary search as long as low <= high
        while (low <= high) {
            int mid = (low + high) / 2; // Calculate the mid-point index
            int comparison = titles[mid].compareToIgnoreCase(searchTitle); // Compare the middle element with the search title
    
            if (comparison == 0) {
                return mid; // Exact match found
            } else if (comparison < 0) {
                low = mid + 1; // Search the right half
            } else {
                high = mid - 1; // Search the left half
            }
        }
    
        return -1; // Book not found
    }

    /**
     * The `partialTitleSearch` method performs a case-insensitive search for a partial match of a given title 
     * within an array of book titles. It checks if each book title contains the search title as a substring 
     * and returns a list of indices where matches are found.
     * 
     * **Algorithm Explanation:**
     * - The method uses a linear search to iterate through the array of titles. For each title, it checks 
     *   if the search title is a substring of the current title using the `contains` method, while ignoring 
     *   case differences.
     * - All matching indices are collected in a list and returned to the caller.
     * 
     * **Advantages:**
     * - Supports partial matching, making it useful for searching when only part of the title is known.
     * - Simple and flexible, as it does not require the array to be sorted and works for both exact and 
     *   partial matches.
     * 
     * **Drawbacks:**
     * - Inefficient for large datasets due to O(n) time complexity, as it checks each title one by one.
     * - Only works for substring matching and does not support more advanced search patterns like fuzzy matching.
     * 
     * **Possible Improvements:**
     * - Introduce more advanced matching algorithms (e.g., regular expressions or fuzzy search) for more flexible matching.
     *
     * @param titles An array of book titles to search within.
     * @param searchTitle The partial title to search for. The search is case-insensitive.
     * @return A list of indices where titles contain the search title. If no matches are found, an empty list is returned.
     */
    // Partial Title Search
    public static List<Integer> partialTitleSearch(String[] titles, String searchTitle) {
        List<Integer> matchedIndices = new ArrayList<>(); // Initialize a list to store matching indices

        // Iterate through the book titles array
        for (int i = 0; i < bookCount; i++) {
            // Perform a case-insensitive substring search
            if (titles[i].toLowerCase().contains(searchTitle.toLowerCase())) {
                matchedIndices.add(i); // Add the index of the match to the list
            }
        }

        return matchedIndices; // Return the list of matching indices
    }

    /**
     * The `partialPageSearch` method performs a search for an exact match of a given page count within an 
     * array of book page numbers. It iterates through the array and checks if the current page count 
     * matches the `searchPage` parameter.
     * 
     * **Algorithm Explanation:**
     * - This method uses a linear search algorithm, which checks each page number one by one to find exact 
     *   matches for the `searchPage` parameter.
     * - It returns a list of indices where the page numbers match the given page count.
     * 
     * **Advantages:**
     * - Simple and effective for finding exact page count matches.
     * - Does not require the array to be sorted, making it flexible for unsorted data.
     * - Returns multiple matches, so if multiple books have the same page count, all their indices are returned.
     * 
     * **Drawbacks:**
     * - Inefficient for large datasets due to its O(n) time complexity, as each page number is checked sequentially.
     * - Only supports exact matches and does not allow for partial or range-based searches (e.g., finding books with page counts within a certain range).
     * 
     * **Possible Improvements:**
     * - We could add support for range-based or fuzzy matching of page numbers to increase flexibility.
     * 
     * @param pages An array of book page numbers to search within.
     * @param searchPage The specific page count to search for.
     * @return A list of indices where the page count matches the searchPage. If no matches are found, an empty list is returned.
     */
    public static List<Integer> partialPageSearch(int[] pages, int searchPage) {
        List<Integer> matchedIndices = new ArrayList<>(); // Initialize a list to store matching indices
    
        // Iterate through the array of page numbers
        for (int i = 0; i < bookCount; i++) {
            if (pages[i] == searchPage) { // Check for exact page count match
                matchedIndices.add(i); // Add the index of the match to the list
            }
        }
    
        return matchedIndices; // Return the list of matching indices
    }

    /**
     * The `askUserForInt` method prompts the user to input an integer and handles any invalid input by 
     * repeatedly asking for a valid integer. It displays a custom message to the user, waits for input, 
     * and ensures that the input is a valid integer.
     * 
     * **Method Description:**
     * - The method continuously prompts the user for an input until a valid integer is entered. If the input 
     *   is not a valid integer, it catches the `NumberFormatException` and prompts the user to enter a valid 
     *   integer again.
     * 
     * **Advantages:**
     * - Provides a robust way to handle user input by ensuring that only valid integers are accepted.
     * - Prevents the program from crashing due to invalid input by handling exceptions.
     * 
     * **Drawbacks:**
     * - The method only supports integer input, so it may not be suitable for scenarios requiring other types of input (e.g., floats or strings).
     * - The method blocks further execution until a valid integer is entered, which may be an issue in cases where non-blocking behavior is needed.
     * 
     * **Possible Improvements:**
     * - Allow the user to exit the input loop with a special command (e.g., entering "exit" to break out of the loop).
     * 
     * @param message A custom message that is displayed to the user when prompting for input.
     * @return The valid integer entered by the user.
     * @throws NumberFormatException if the user input is not a valid integer (handled within the method).
     */
    public static int askUserForInt(String message) {
        int result = -1; // Initialize the result variable
        boolean valid = false; // Boolean flag to track the validity of input
    
        // Continue prompting the user until valid input is provided
        while (!valid) {
            System.out.print(message); // Display the custom message
            try {
                // Read the input, trim any extra spaces, and attempt to parse it as an integer
                result = Integer.parseInt(scanner.nextLine().trim());
                valid = true; // Set valid to true if parsing succeeds
            } catch (NumberFormatException e) {
                // Handle invalid input
                System.out.println("Invalid input, please enter a valid integer.");
            }
        }
    
        return result; // Return the valid integer input
    }    

    /**
     * The `formatAndDisplayBooks` method formats and displays a list of book titles and their corresponding 
     * page counts in a tabular format. The method prints a table header, followed by up to 50 entries from 
     * the provided arrays. Each entry shows the index, book title, and page count.
     * 
     * **Method Description:**
     * - The method prints a table-like structure with columns for the index, book title, and page count.
     * - It uses formatted printing (`printf`) to ensure consistent alignment of the data in a neat and readable way.
     * - The method limits the number of displayed entries to 50 for readability, even if more entries are present.
     * 
     * **Advantages:**
     * - Presents the book data in a clean and readable format, making it easier for users to view the results.
     * - Limits the output to 50 books to prevent overwhelming the user with too much information.
     * 
     * **Drawbacks:**
     * - Limits the display to only the first 50 books, which may not be ideal for users who want to see the entire list.
     * - The formatting assumes a fixed layout, which may not be suitable for very long book titles.
     * 
     * **Possible Improvements:**
     * - Add pagination or scrolling functionality to allow users to view more than 50 books if needed.
     * - Dynamically adjust the formatting based on the length of the book titles to ensure the table looks clean even with longer titles.
     * 
     * @param bookTitles An array of book titles to be displayed.
     * @param bookPages An array of corresponding page counts to be displayed.
     */
    private static void formatAndDisplayBooks(String[] bookTitles, int[] bookPages) {
        System.out.printf(HEADER_FORMAT, "Index", "Book Title", "Pages");
        System.out.println(SEPARATOR);

        for (int i = 0; i < Math.min(50, bookTitles.length); i++) {
            System.out.printf(HEADER_FORMAT, "[" + i + "]", bookTitles[i], bookPages[i]);
        }
    }

    /**
     * The `MenuOption` enum represents the possible options available in the main menu of the program. 
     * Each option is associated with an integer value that corresponds to the user's input.
     * 
     * The enum provides four main options:
     * 1. `SORT` - Option to sort the list of books by title or page count.
     * 2. `SEARCH` - Option to search for books by title or page count.
     * 3. `ASSESS_ALGORITHMS` - Option to assess and compare sorting and searching algorithms.
     * 4. `EXIT` - Option to exit the program.
     * 
     * **Advantages:**
     * - Enums provide a type-safe way to represent and handle menu options, ensuring that only valid options 
     *   are used throughout the program.
     * - By associating each option with an integer value, the program can easily map user input to the corresponding menu option.
     * 
     * **Drawbacks:**
     * - If additional options are added to the menu in the future, this enum will need to be modified.
     * 
     * **Possible Improvements:**
     * - Add documentation for each option to explain what each menu option does in more detail.
     * - We could extend the enum to include user-friendly descriptions that can be displayed directly in the menu.
     * 
     * @see #getValue(int) Provides a way to retrieve the enum option corresponding to a specific integer value.
     */
    enum MenuOption {
        SORT(1),
        SEARCH(2),
        ASSESS_ALGORITHMS(3),
        EXIT(4);
    
        private final int value; // The integer value associated with the menu option
    
        // Constructor to associate each enum option with a specific integer value
        MenuOption(int value) {
            this.value = value;
        }
    
        /**
         * Retrieves the `MenuOption` corresponding to the given integer value. 
         * If the value does not match any of the defined options, the method returns `null`.
         * 
         * @param value The integer value provided by the user.
         * @return The corresponding `MenuOption` or `null` if the value does not match any option.
         */
        public static MenuOption getValue(int value) {
            // Loop through the enum values to find a match with the provided integer
            for (MenuOption option : values()) {
                if (option.value == value) {
                    return option; // Return the matching enum option
                }
            }
            return null; // Return null if no match is found
        }
    }

    /**
     * The `SortingOption` enum represents the options available in the sorting menu. Each option is 
     * associated with an integer value corresponding to the user's input for how they would like to 
     * sort the list of books.
     * 
     * The enum provides three options:
     * 1. `ByTITLE` - Sort the books by title.
     * 2. `ByPAGES` - Sort the books by page count.
     * 3. `BACK` - Return to the previous menu.
     * 
     * @see #getValue(int) Provides a way to retrieve the enum option corresponding to a specific integer value.
     */
    enum SortingOption {
        ByTITLE(1),
        ByPAGES(2),
        BACK(3);
    
        private final int value; // The integer value associated with the sorting option
    
        // Constructor to associate each enum option with a specific integer value
        SortingOption(int value) {
            this.value = value;
        }
    
        /**
         * Retrieves the `SortingOption` corresponding to the given integer value.
         * If the value does not match any of the defined options, the method returns `null`.
         * 
         * @param value The integer value provided by the user.
         * @return The corresponding `SortingOption` or `null` if the value does not match any option.
         */
        public static SortingOption getValue(int value) {
            // Loop through the enum values to find a match with the provided integer
            for (SortingOption option : values()) {
                if (option.value == value) {
                    return option; // Return the matching enum option
                }
            }
            return null; // Return null if no match is found
        }
    }

    /**
     * The `SortingOrder` enum represents the available sorting orders in the sorting menu. Each option 
     * is associated with an integer value corresponding to the user's input for how they would like 
     * to order the sorted list.
     * 
     * The enum provides three options:
     * 1. `ASCENDING` - Sort the books in ascending order.
     * 2. `DESCENDING` - Sort the books in descending order.
     * 3. `BACK` - Return to the previous menu.
     * 
     * @see #getValue(int) Provides a way to retrieve the enum option corresponding to a specific integer value.
     */
    enum SortingOrder {
        ASCENDING(1),
        DESCENDING(2),
        BACK(3);
    
        private final int value; // The integer value associated with the sorting order
    
        // Constructor to associate each enum option with a specific integer value
        SortingOrder(int value) {
            this.value = value;
        }
    
        /**
         * Retrieves the `SortingOrder` corresponding to the given integer value.
         * If the value does not match any of the defined options, the method returns `null`.
         * 
         * @param value The integer value provided by the user.
         * @return The corresponding `SortingOrder` or `null` if the value does not match any option.
         */
        public static SortingOrder getValue(int value) {
            // Loop through the enum values to find a match with the provided integer
            for (SortingOrder order : values()) {
                if (order.value == value) {
                    return order; // Return the matching enum option
                }
            }
            return null; // Return null if no match is found
        }
    }

    /**
     * The `SearchOption` enum represents the available options for searching within the search menu.
     * Each option is associated with an integer value corresponding to the user's input for how they 
     * would like to search the list of books.
     * 
     * The enum provides three options:
     * 1. `ByTITLE` - Search the books by title.
     * 2. `ByPAGES` - Search the books by the number of pages.
     * 3. `BACK` - Return to the previous menu.
     * 
     * @see #getValue(int) Provides a way to retrieve the enum option corresponding to a specific integer value.
     */
    enum SearchOption {
        ByTITLE(1),
        ByPAGES(2),
        BACK(3);
    
        private final int value; // The integer value associated with the search option
    
        // Constructor to associate each enum option with a specific integer value
        SearchOption(int value) {
            this.value = value;
        }
    
        /**
         * Retrieves the `SearchOption` corresponding to the given integer value.
         * If the value does not match any of the defined options, the method returns `null`.
         * 
         * @param value The integer value provided by the user.
         * @return The corresponding `SearchOption` or `null` if the value does not match any option.
         */
        public static SearchOption getValue(int value) {
            // Loop through the enum values to find a match with the provided integer
            for (SearchOption option : values()) {
                if (option.value == value) {
                    return option; // Return the matching enum option
                }
            }
            return null; // Return null if no match is found
        }
    }
}
