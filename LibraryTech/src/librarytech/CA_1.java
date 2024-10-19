import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CA_1 {
    static String[] bookTitles;
    static int[] bookPages;
    static int bookCount;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Prompt the user for the CSV filename
        String fileName = "MOCK_DATA.txt";
        // Read the book data into arrays
        readFile(fileName);

        mainMenu();
    }

    private static void mainMenu() {
        String menuOptions = "************************** MAIN MENU ******************************* \n" +
                "\t Options: \n" +
                "\t 1) SORT \n" +
                "\t 2) SEARCH \n" +
                "\t 3) ASSESS ALGORITHMS \n" +
                "\t 4) EXIT \n" +
                "********************************************************************\n" +
                "Your Option: ";

        MenuOption option;

        do {
            int userInput = askUserForInt(menuOptions);
            option = MenuOption.getValue(userInput);

            if (option == null) {
                System.out.println("Invalid option, please select a valid option!");
            } else {
                switch (option) {
                    case SORT:
                        sortingMenu();
                        break;
                    case SEARCH:
                        searchingMenu();
                        break;
                    case ASSESS_ALGORITHMS:
                        assessAlgorithms();
                        break;
                    case EXIT:
                        System.out.println("Closing Program... Thank You!");
                        break;
                }
            }
        } while (option != MenuOption.EXIT);
    }

    private static void sortingMenu() {
        String menuOptions = "************************** SORTING MENU ******************************* \n" +
                "\t Options: \n" +
                "\t 1) BY TITLE \n" +
                "\t 2) BY PAGES \n" +
                "\t 3) BACK \n" +
                "********************************************************************\n" +
                "Your Option: ";

        SortingOption option;

        do {
            int userInput = askUserForInt(menuOptions);
            option = SortingOption.getValue(userInput);

            if (option == null) {
                System.out.println("Invalid option, please select a valid option!");
            } else {
                if (option == SortingOption.BACK) {
                    return;
                }
                sortingOrderMenu(option);
            }
        } while (true);
    }

    private static void sortingOrderMenu(SortingOption sortOption) {
        String menuOptions = "************************** SORTING ORDER MENU ******************************* \n" +
                "\t Options: \n" +
                "\t 1) ASCENDING \n" +
                "\t 2) DESCENDING \n" +
                "\t 3) BACK \n" +
                "********************************************************************\n" +
                "Your Option: ";

        SortingOrder sortingOrder;

        do {
            int userInput = askUserForInt(menuOptions);
            sortingOrder = SortingOrder.getValue(userInput);

            if (sortingOrder == null) {
                System.out.println("Invalid option, please select a valid option!");
            } else if (sortingOrder == SortingOrder.BACK) {
                return;
            } else {
                long startTime = System.nanoTime();
                String[] bookTitlesClone = bookTitles.clone();
                int[] bookPagesClone = bookPages.clone();

                if (sortOption == SortingOption.ByTITLE) {
                    quickSort(bookTitlesClone, bookPagesClone, 0, bookCount - 1,
                            sortingOrder == SortingOrder.ASCENDING);
                } else {
                    quickSort(bookPagesClone, bookTitlesClone, 0, bookCount - 1,
                            sortingOrder == SortingOrder.ASCENDING);
                }
                long endTime = System.nanoTime();

                System.out.println("Sorted by " + (sortOption == SortingOption.ByTITLE ? "Book Title" : "Number of Pages")
                        + " - " + (sortingOrder == SortingOrder.ASCENDING ? "Ascending" : "Descending"));

                // display the first 50 results after sorting
                for (int i = 0; i < 50; i++) {
                    System.out.println(bookTitlesClone[i] + " " + bookPagesClone[i]);
                }

                System.out.println("Sorting took: " + (endTime - startTime) / 1_000_000 + " ms");
            }
        } while (true);
    }

    private static void searchingMenu() {
        String menuOptions = "************************** SEARCH MENU ******************************* \n" +
                "\t Options: \n" +
                "\t 1) BY TITLE \n" +
                "\t 2) BY PAGES \n" +
                "\t 3) BACK \n" +
                "********************************************************************\n" +
                "Your Option: ";

        SearchOption option;

        do {
            int userInput = askUserForInt(menuOptions);
            option = SearchOption.getValue(userInput);

            if (option == null) {
                System.out.println("Invalid option, please select a valid option!");
            } else {
                if (option == SearchOption.BACK) {
                    return;
                }
                searchBooks(option);
            }
        } while (true);
    }

    private static void searchBooks(SearchOption option) {
        long startTime = System.nanoTime();
        List<Integer> matchedIndices = new ArrayList<>();

        if (option == SearchOption.ByTITLE) {
            scanner.nextLine(); // consume the leftover newline
            System.out.println("Please enter the partial title of the book you wish to find:");
            String searchTitle = scanner.nextLine();
            matchedIndices = partialTitleSearch(bookTitles, searchTitle);
        } else if (option == SearchOption.ByPAGES) {
            System.out.println("Please enter the number of pages to search for:");
            int searchPages = scanner.nextInt();
            matchedIndices = partialPageSearch(bookPages, searchPages);
        }

        long endTime = System.nanoTime();

        if (!matchedIndices.isEmpty()) {
            System.out.println("Books found:");
            for (int index : matchedIndices) {
                System.out.println(bookTitles[index] + " - " + bookPages[index] + " pages");
            }
        } else {
            System.out.println("No books found matching your search criteria.");
        }

        System.out.println("Searching took: " + (endTime - startTime) / 1_000_000 + " ms");
    }

    private static void assessAlgorithms() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assessAlgorithms'");
    }

    public static void readFile(String filename) {
        bookCount = 0;
        String line;
        int index = 0;

        // First pass to count the lines
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
                bookCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Second pass to read the data
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            bookTitles = new String[bookCount];
            bookPages = new int[bookCount];

            while ((line = br.readLine()) != null) {
                String[] bookInfo = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                bookTitles[index] = bookInfo[0].replaceAll("\"", "").trim();
                bookPages[index] = Integer.parseInt(bookInfo[1].trim());
                index++;
            }
            br.close();
            System.out.println("File read successfully");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // QuickSort for Titles
    public static void quickSort(String[] titles, int[] pages, int low, int high, boolean ascending) {
        if (low < high) {
            int pi = partitionTitles(titles, pages, low, high, ascending);
            quickSort(titles, pages, low, pi - 1, ascending);
            quickSort(titles, pages, pi + 1, high, ascending);
        }
    }

    public static int partitionTitles(String[] titles, int[] pages, int low, int high, boolean ascending) {
        String pivot = titles[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            boolean condition = ascending ? titles[j].compareToIgnoreCase(pivot) < 0
                    : titles[j].compareToIgnoreCase(pivot) > 0;
            if (condition) {
                i++;
                // Swap titles
                String tempTitle = titles[i];
                titles[i] = titles[j];
                titles[j] = tempTitle;

                // Swap pages
                int tempPage = pages[i];
                pages[i] = pages[j];
                pages[j] = tempPage;
            }
        }

        // Swap pivot
        String tempTitle = titles[i + 1];
        titles[i + 1] = titles[high];
        titles[high] = tempTitle;

        int tempPage = pages[i + 1];
        pages[i + 1] = pages[high];
        pages[high] = tempPage;

        return i + 1;
    }

    // QuickSort for Pages
    public static void quickSort(int[] pages, String[] titles, int low, int high, boolean ascending) {
        if (low < high) {
            int pi = partitionPages(pages, titles, low, high, ascending);
            quickSort(pages, titles, low, pi - 1, ascending);
            quickSort(pages, titles, pi + 1, high, ascending);
        }
    }

    public static int partitionPages(int[] pages, String[] titles, int low, int high, boolean ascending) {
        int pivot = pages[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            boolean condition = ascending ? pages[j] < pivot : pages[j] > pivot;
            if (condition) {
                i++;
                // Swap pages
                int tempPage = pages[i];
                pages[i] = pages[j];
                pages[j] = tempPage;

                // Swap titles
                String tempTitle = titles[i];
                titles[i] = titles[j];
                titles[j] = tempTitle;
            }
        }

        // Swap pivot
        int tempPage = pages[i + 1];
        pages[i + 1] = pages[high];
        pages[high] = tempPage;

        String tempTitle = titles[i + 1];
        titles[i + 1] = titles[high];
        titles[high] = tempTitle;

        return i + 1;
    }

    // Linear Search by Title (used for assessment)
    public static int linearSearch(String[] titles, String searchTitle) {
        for (int i = 0; i < titles.length; i++) {
            if (titles[i].equalsIgnoreCase(searchTitle)) {
                return i; // Exact match found
            }
        }
        return -1; // Book not found
    }

    // Binary Search by Title (used for assessment)
    public static int binarySearchTitle(String[] titles, String searchTitle) {
        int low = 0, high = titles.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = titles[mid].compareToIgnoreCase(searchTitle);

            if (comparison == 0) {
                return mid; // Found exact match
            } else if (comparison < 0) {
                low = mid + 1; // Search the right half
            } else {
                high = mid - 1; // Search the left half
            }
        }
        return -1; // Not found
    }

    // Partial Search by Title
    public static List<Integer> partialTitleSearch(String[] titles, String searchTitle) {
        List<Integer> matchedIndices = new ArrayList<>();
        for (int i = 0; i < bookCount; i++) {
            if (titles[i].toLowerCase().contains(searchTitle.toLowerCase())) {
                matchedIndices.add(i);
            }
        }
        return matchedIndices;
    }

    // Partial Search by Pages
    public static List<Integer> partialPageSearch(int[] pages, int searchPage) {
        List<Integer> matchedIndices = new ArrayList<>();
        for (int i = 0; i < bookCount; i++) {
            if (pages[i] == searchPage) {
                matchedIndices.add(i);
            }
        }
        return matchedIndices;
    }

    public static int askUserForInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Invalid input, please enter a number: ");
        }
        return scanner.nextInt();
    }
}
