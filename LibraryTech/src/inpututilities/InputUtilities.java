/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inpututilities;

import java.util.Scanner;

/**
 * HDIP Comp Feb 2024 cohort
 * 
 * @author Joel Tola
 */
public class InputUtilities {

    /**
     * Ask user to enter some text - if they enter non-text (like numbers)
     * then ask them again
     * 
     * @param prompt - the question or prompt to ask the user
     * @return a String containing whatever text the user entered
     */
    public String askUserForText(String prompt) {
        Scanner myKB = new Scanner(System.in);
        String userInput;

        do {
            System.out.print(prompt + " ");
            userInput = myKB.nextLine();
            
            if (!userInput.matches("[a-zA-Z_!.,@\"? ]+")) {
                System.out.println("Enter text only please - no numbers!");
            }

        } while (!userInput.matches("[a-zA-Z_!.,@\"? ]+"));
        return userInput;
    }

    public String askUserForTextWithNumbers(String prompt) {
        Scanner myKB = new Scanner(System.in);
        String userInput;

        do {
            System.out.print(prompt + " ");
            userInput = myKB.nextLine();
            
            if (!userInput.matches("[a-zA-Z0-9_!.,@\"? ]+")) {
                System.out.println("Enter text only please - no numbers!");
            }

        } while (!userInput.matches("[a-zA-Z0-9_!.,@\"? ]+"));
        return userInput;
    }

    /**
     * Ask the user to enter any integer value (negatives are allowed)
     * if they do not enter an integer ask them again
     * 
     * @param prompt the question or prompt to ask the user
     * @return a valid int entered by the user
     */
    public int askUserForInt(String prompt) {
        Scanner myKB = new Scanner(System.in);
        int userInput = 0;
        boolean valid = false;
        String errorMessage = "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ERROR !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! \n" +
        "\t Enter integer values only please! \n" +
        "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! \n";

        do {
            // this gets repeated
            try {
                System.out.println(prompt);
                userInput = myKB.nextInt(); // this might go wrong
                valid = true;
            } catch (Exception e) {
                System.out.println(errorMessage);
                myKB.nextLine(); // prevents infinite loop
                valid = false; // just to be sure
            }
            // use loop guard to check that input is greater than minValue
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
        Scanner myKB = new Scanner(System.in);
        int userInput = 0;
        boolean valid = false;

        do {
            // this gets repeated
            try {
                System.out.println(prompt);
                System.out.println("You must enter a integer larger than " + minValue);
                userInput = myKB.nextInt(); // this might go wrong
                valid = true;
            } catch (Exception e) {
                System.out.println("That was not an integer value.");
                myKB.nextLine(); // prevents infinite loop
                valid = false; // just to be sure
            }
            // use loop guard to check that input is greater than minValue
        } while (!valid || (userInput <= minValue));
        // input must be valid
        return userInput;
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

        Scanner myKB = new Scanner(System.in);
        int userInput = 0;
        boolean valid = false;

        do {
            // this gets repeated
            try {
                System.out.println(prompt);
                System.out.println("You must enter a value between " + minValue + " and " + maxValue);
                userInput = myKB.nextInt(); // this might go wrong
                valid = true;
            } catch (Exception e) {
                System.out.println("That was not an integer value.");
                myKB.nextLine(); // prevents infinite loop
            }
        } while (!valid || (userInput < minValue) || (userInput > maxValue));
        // input must be valid
        return userInput;
    }

    public String askUserForDate(String prompt) {
        Scanner myKB = new Scanner(System.in);
        String userInput;
        boolean isValidDate = false;

        do {
            System.out.print(prompt + " ");
            userInput = myKB.nextLine();
            isValidDate = userInput.matches("\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$");

            if (!isValidDate) {
                System.out.println("Enter date in the format YYYY-MM-DD");
            }

        } while (!isValidDate);
        return userInput;
    };
}