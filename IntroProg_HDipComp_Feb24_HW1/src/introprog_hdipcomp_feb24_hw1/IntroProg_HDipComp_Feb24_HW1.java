/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package introprog_hdipcomp_feb24_hw1;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Scanner;

/**
 *
 * @author Joel Tola
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
        int mark; // Variable to store the input mark.
        double markPercentage; // Variable to store the percentage equivalent of the mark.
        DecimalFormat decFor = new DecimalFormat("0.00"); // Decimal formatter to format percentage.
        boolean isValidMark = false; // Flag to track if the mark entered is valid.

        // Loop until a valid mark is entered.
        while (!isValidMark) {
            System.out.println("Please enter your mark"); // Prompt the user to enter their mark.
            mark = readNumber(); // Read the mark input from the user.

            // Check if the entered mark is within the valid range.
            if (mark > 0 && mark <= 100) {
                isValidMark = true; // Mark is valid.
                markPercentage = ((double) mark / 100) * 25; // Calculate the percentage equivalent.
                // Format the output message with the percentage score.
                String message = MessageFormat.format("You scored {0}% out of a maximum of 25%",
                        decFor.format(markPercentage));
                System.out.println(message); // Display the result.
            } else {
                System.out.println("Please enter a number between 0 and 100"); // Invalid mark entered.
            }
        }
    }

    /**
     * Method to read an integer input from the user.
     * 
     * @return the integer input read from the user
     */
    private static int readNumber() {
        Scanner scanner = new Scanner(System.in); // Scanner object to read input.
        int number; // Variable to store the input number.
        try {
            number = scanner.nextInt(); // Attempt to read an integer input.
            return number; // Return the input number.
        } catch (Exception e) {
            System.out.println("Please insert a valid number"); // Input is not a valid integer.
            return readNumber(); // Recursive call to read a valid number.
        }
    }
}