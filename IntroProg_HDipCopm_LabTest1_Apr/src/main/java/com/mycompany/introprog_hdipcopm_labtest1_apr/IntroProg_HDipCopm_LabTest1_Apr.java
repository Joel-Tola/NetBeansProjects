/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.introprog_hdipcopm_labtest1_apr;

import java.util.Scanner;

/**
 *
 * @author sljoe
 */
public class IntroProg_HDipCopm_LabTest1_Apr {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int downListLimit = 1;
        int upListLimit = 30;
        int inputInt;
        int inputTotal = 0;
        int inputHighest = Integer.MIN_VALUE;
        double inputAverage = 0.0;

        // Ask the user for the number of inputs
        int listLength = getIntFromUser(scanner, "How many numbers do you want to insert (between 1 and 30)? ",
                downListLimit, upListLimit);

        // Collect numbers from the user
        System.out.println("Please enter each number:");
        for (int i = 0; i < listLength; i++) {
            inputInt = getIntFromUser(scanner, "Number " + (i + 1) + ": ");
            inputTotal += inputInt;
            if (inputInt > inputHighest) {
                inputHighest = inputInt;
            }
        }

        inputAverage = (double) inputTotal / listLength;

        // Menu to display results
        int userChoice;
        do {
            System.out.println("\n Choose an option: \n 1. Display TOTAL \n 2. Display HIGHEST \n 3. Display AVERAGE \n 4. Exit");
            userChoice = getIntFromUser(scanner, "Your choice: ");

            switch (userChoice) {
                case 1:
                    System.out.println("The TOTAL sum is: " + inputTotal);
                    break;
                case 2:
                    System.out.println("The HIGHEST value is: " + inputHighest);
                    break;
                case 3:
                    System.out.println("The AVERAGE value is: " + inputAverage);
                    break;
                case 4:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("That is not a valid option.");
                    break;
            }
        } while (userChoice != 4);

        scanner.close();
    }

    private static int getIntFromUser(Scanner scanner, String prompt, int min, int max) {
        int number;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number!");
                System.out.print(prompt);
                scanner.next(); // discard non-int input
            }
            number = scanner.nextInt();
            if (number < min || number > max) {
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            }
        } while (number < min || number > max);
        return number;
    }

    private static int getIntFromUser(Scanner scanner, String prompt) {
        int number;
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a number!");
            System.out.print(prompt);
            scanner.next(); // discard non-int input
        }
        number = scanner.nextInt();
        return number;
    }
}
