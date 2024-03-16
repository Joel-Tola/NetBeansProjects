/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package introprog_hdipcomp_feb24_hw1;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Joel Tola Soliz
 *         Program that will ask the user to enter the marks
 *         they have received for an assignment that is worth 25% of the marks
 *         for the module.
 *         The maximum mark that can be input is 100 and the minimum is 0
 *         (zero).
 *         Output an error message if they enter any other number OR if they do
 *         not enter a number at all.
 *         If they enter a valid number, output the PERCENTAGE equivalent.
 *         The maximum result should be 25%. It should be possible to output a
 *         decimal value (e.g. 11.5%).
 *         EXAMPLE OUTPUT:
 *         >> Please enter your mark
 *         <<user enters 70>>
 *         >> You scored 17.5% out of a maximum of 25%
 */
public class IntroProg_HDipComp_Feb24_HW1 {

    /**
     * The main method of the program.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner object to read input.
        int mark; // Variable to store the input mark.
        double markPercentage; // Variable to store the percentage equivalent of the mark.
        DecimalFormat decFor = new DecimalFormat("0.00"); // Decimal formatter to format percentage.

        mark = ReadValidMark(scanner); // Read next Integer input.
        markPercentage = ((double) mark / 100) * 25; // Calculate the percentage equivalent.
        // Format the output message with the percentage score.
        String message = MessageFormat.format("You scored {0}% out of a maximum of 25%",
                decFor.format(markPercentage));
        System.out.println(message); // Display the result.
        scanner.close(); // Closes this scanner to prevent Resource leak.
    }

    /**
     * Method to read a valid integer mark from the user.
     * 
     * @param scanner the Scanner object used for input
     * @return the integer input read from the user
     */
    private static int ReadValidMark(Scanner scanner) {
        boolean isValidMark = false; // Flag to track if the mark entered is valid.
        int number = 0; // Initialize number by default.

        try {
            // Loop until a valid mark is entered.
            while (!isValidMark) {
                System.out.println("Enter your mark"); // Prompt the user to enter their mark.
                if (scanner.hasNextInt()) { // Returns true if the next token in this scanner's input can be interpreted
                                            // as an int value.
                    number = scanner.nextInt(); // Read the mark input from the user.
                    // Check if the entered mark is within the valid range.
                    if (number >= 0 && number <= 100) {
                        isValidMark = true; // Mark is valid.
                    } else {
                        System.out.println("Please enter a number between 0 and 100"); // Invalid mark entered.
                    }
                } else {
                    System.out.println("Please enter a valid number"); // Display error message for non-integer input.
                    scanner.next(); // Consume the invalid input to avoid infinite loop.
                }
            }
        } catch (Exception e) { //Catch the error
            MessageFormat.format("ERROR: {0}", e.getMessage()); //Show the error description message.
        }

        return number;
    }
}