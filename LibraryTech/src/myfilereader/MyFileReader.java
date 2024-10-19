package myfilereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MyFileReader {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // This is the location of the file we are going to read. Imagine it's like the address of a house.
        String filePath = "D:/Documents/NetBeansProjects/LibraryTech/src/librarytech/MOCK_DATA.txt";

        // These are empty boxes where we'll store the lines from the file.
        String line = "";
        int linesCount = 0; // We'll use this to count how many lines are in the file.
        int index = 0; // This is to keep track of which line we are on.

        // First, we will open the file and count how many lines (rows) are inside.
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            System.out.println("Counting how many lines are in the file...");
            while (br.readLine() != null) { // Read each line one by one.
                linesCount++; // Add 1 to the count each time we read a line.
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oops! The file at " + filePath + " is missing!"); // If the file isn't found.
            return;
        } catch (Exception e) {
            System.out.println("Uh oh! Something went wrong while counting: " + e.getMessage());
            return;
        }

        // Now, we are creating two lists to store names and page numbers from the file.
        String[] names = new String[linesCount]; // A list of names, one for each line in the file.
        int[] pages = new int[linesCount]; // A list of numbers, one for each line in the file.

        // Now, let's open the file again and fill these lists with information.
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            System.out.println("Reading the file and putting the information into our lists...");
            while ((line = br.readLine()) != null) { // Keep reading each line until there are no more.
                String[] lineDetails = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // This splits the line into two parts: name and page number.
                names[index] = lineDetails[0].replaceAll("\"", "").trim(); // Clean the name (remove extra symbols).
                pages[index] = Integer.parseInt(lineDetails[1].trim()); // Convert the page number into a real number and store it.
                index++; // Move to the next line.
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oops! The file at " + filePath + " is missing!");
            return;
        } catch (Exception e) {
            System.out.println("Uh oh! Something went wrong while filling the lists: " + e.getMessage());
            return;
        }

        String choice = "";
        int intOption = 0;

        // Here we ask the user what they want to do: Sort the list, search the list, or exit.
        do {
            System.out.println("OPTIONS:");
            System.out.println("Press 1 to SORT the list of names.");
            System.out.println("Press 2 to SEARCH for a name.");
            System.out.println("Press 3 to EXIT the program.");

            // Make sure the user enters only numbers.
            do {
                choice = scanner.nextLine();
                if (!choice.matches("[0-9]+")) {
                    System.out.println("Please enter numbers only.");
                }
            } while (!choice.matches("[0-9]+"));
            intOption = Integer.parseInt(choice); // Convert the text input into a number.

            // Depending on what number the user chooses, we do different things.
            switch (intOption) {
                case 1:
                    handleSorting(names, pages); // If they press 1, we sort the names.
                    break;
                case 2:
                    handleSearchLines(names, pages); // If they press 2, we search for a name.
                    break;
                case 3:
                    System.out.println("Goodbye! You have exited the program."); // If they press 3, we exit.
                    break;
                default:
                    System.out.println("That option doesn't exist. Please try again.");
            }

        } while (intOption != 3); // Keep asking until the user chooses to exit.

        scanner.close();
    }

    // This method sorts and shows the list of names and page numbers.
    public static void handleSorting(String[] names, int[] pages) {
        System.out.println("Sorting the list...");

        // For now, we just show the names and page numbers, but we could sort them here if needed.
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + " has " + pages[i] + " pages.");
        }
        System.out.println(); // A blank line for better readability.
    }

    // This method searches through the list and shows the names and pages.
    public static void handleSearchLines(String[] names, int[] pages) {
        System.out.println("Here are all the names and their pages:");

        // Just like sorting, we are showing all the names and their page numbers.
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + " has " + pages[i] + " pages.");
        }
        System.out.println(); // A blank line for better readability.
    }
}
