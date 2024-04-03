/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inpututilities;

import java.util.Scanner;

/**
 * HDIP Comp Feb 2024
 * Utility class for handling input from users in various formats.
 * Ensures that inputs meet specific criteria before accepting them.
 * 
 * @author Joel
 */
public class InputUtilities {
    // Single Scanner instance for reuse across all methods
    private final Scanner myScanner = new Scanner(System.in);

    /**
     * Ask user to enter some text - if they enter non-text (like numbers)
     * then ask them again
     * 
     * @param prompt - the question or prompt to ask the user
     * @return a String containing whatever text the user entered
     */
    public String askUserForText(String prompt) {
        String userInput;

        do {
            System.out.println(prompt);
            System.out.println("Enter text only please - no numbers!");
            userInput = myScanner.nextLine();

        } while (!userInput.matches("[a-zA-Z!.,@\"? ]+"));

        return (userInput);

    }

    /**
     * Ask the user to enter any integer value (negatives are allowed)
     * if they do not enter an integer ask them again
     * 
     * @param prompt the question or prompt to ask the user
     * @return a valid int entered by the user
     */
    public int askUserForInt(String prompt) {
        String userInput;

        do {
            System.out.println(prompt);
            System.out.println("Enter integer values only please!");
            userInput = myScanner.nextLine();

        } while (!userInput.matches("[0-9-]+"));
        // user has entered a numeric value but it is still a String

        return Integer.parseInt(userInput);
    }

    /**
     * Asks the user to enter any integer value, 
     * repeating the prompt if the input is not an integer.
     * 
     * @param prompt The question or prompt to ask the user.
     * @return An integer value entered by the user.
     */
    public int askUserForIntV2(String prompt) {
        String userInput;
        int userInt = 0;
        boolean isValid = false;

        do {
            System.out.println(prompt);
            System.out.println("Enter integer values only please!");
            userInput = myScanner.nextLine();
            try {
                userInt = Integer.parseInt(userInput);
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer value.");
            }
        } while (!isValid);

        return userInt;
    }

    /**
     * Ask the user to enter an integer value
     * if not an integer - display error and ask again
     * 
     * @param prompt the prompt or question to ask
     * @return a valid integer entered by user
     */
    public int askUserForIntV3(String prompt) {
        int userInput = 0;
        boolean valid = false;

        do {
            // this gets repeated
            try {
                System.out.println(prompt);
                System.out.println("Enter integer values only please!");
                userInput = myScanner.nextInt(); // this might go wrong
                valid = true;
            } catch (Exception e) {
                System.out.println("That was not an integer. Please try again.");
                myScanner.nextLine(); // prevents infinite loop
            }
        } while (!valid);
        // input must be valid
        return userInput;
    }

    /**
     * Ask user for an integer value bigger than a given minimum
     * if they do not enter a valid integer ask them again
     * 
     * @param prompt   the question or prompt to ask user
     * @param minValue the lowest value allowed
     * @return a valid int bigger than minValue
     */
    public int askUserForInt(String prompt, int minValue) {

        int userInt;
        do {
            userInt = askUserForInt(prompt); // Reuse the method to ask for an integer and maintain DRY (Don't Repeat
                                             // Yourself) principles, enhancing code maintainability.
            if (userInt < minValue) {
                System.out.println("The number must be greater than or equal to " + minValue);
            }
        } while (userInt < minValue);
        return userInt;
    }

    /**
     * Ask user for an integer value in a given range
     * if they do not enter a valid integer ask them again
     * 
     * @param prompt   the question or prompt to ask user
     * @param minValue the lowest value allowed
     * @param maxValue the highest value allowed
     * @return a valid int within the given range
     */
    public int askUserForInt(String prompt, int minValue, int maxValue) {

        int userInt;
        do {
            userInt = askUserForInt(prompt); // Reuse the method to ask for an integer and maintain DRY (Don't Repeat
                                             // Yourself) principles, enhancing code maintainability.
            if (userInt < minValue || userInt > maxValue) {
                System.out.println("The number must be between " + minValue + " and " + maxValue);
            }
        } while (userInt < minValue || userInt > maxValue);
        return userInt;
    }
}
