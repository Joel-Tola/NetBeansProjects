/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package introprog_hdipcomp_labtest1;

import java.util.Arrays;

import inpututilities.InputUtilities;

/**
 *
 * @author Joel Tola Soliz
 */
public class IntroProg_HDipComp_LabTest1 {

    public static void main(String[] args) {

        int listLength = 0;
        int inputInt;
        int downListLimit = 0;
        int upListLimit = 30;
        String prompt;
        InputUtilities myInp = new InputUtilities();

        // Menu operations variables.
        int inputTotal = 0;
        int inputHighest = 0;
        double inputAverage = 0;

        // Loop to create the inputArray
        do {
            prompt = "How many number do you want to insert? (between 0 and 30) ";
            inputInt = myInp.askUserForInt(prompt);

            // control the down and up list limits.
            if (inputInt < downListLimit || inputInt > upListLimit || inputInt <= 0) {
                System.out.println("Please Insert a number between 0 and 30\n");
            }

            listLength = inputInt;

            // control the down and up list limits.
        } while (inputInt < downListLimit || inputInt > upListLimit || inputInt <= 0);

        // Create an Array of integers.
        int[] inputArray = new int[listLength];

        // Loop for filling the list
        System.out.println("Please enter each number:");

        for (int i = 0; i < listLength; i++) {
            inputInt = myInp.askUserForInt("Number " + (i + 1) + ": ");
            inputArray[i] = inputInt;

            // Calculate the menu operation on the go to improve performance.
            inputTotal += inputInt;

            if (inputHighest < inputInt) {
                inputHighest = inputInt;
            }
        }

        inputAverage = (double) inputTotal/listLength;

        // Menu Options.
        prompt = "Option 1: Calculate the TOTAL of all the numbers entered (add them all together) \n" +
                "Option 2: Determine the HIGHEST number that was entered. \n" +
                "Option 3: Calculate the AVERAGE of the numbers entered (add them up and divide by the quantity) \n" +
                "Option 4: Exit \n" +
                "Your Option: ";

        do {
            // Print the full list to display values.
            // We don not need to do this! that is why we can to the same without an Array.
            // We only need an Array if we are required to do something with it.
            String stringArray = Arrays.toString(inputArray);
            System.out.println(stringArray);

            inputInt = myInp.askUserForInt(prompt);

            switch (inputInt) {
                case 1:
                    System.out.println("The TOTAL sum is: " + inputTotal + "\n");

                    // You can add more code in case block if you need to add more logic as it
                    // follows:
                    // int totalSum = 0;
                    // for (Integer index : inputArray) {
                    // totalSum += index;
                    // }
                    // System.out.println("The TOTAL sum is: " + totalSum + "\n");

                    break;
                case 2:
                    System.out.println("The HIGHEST value is: " + inputHighest + "\n");

                    // You can add more code in case block if you need to add more logic as it
                    // follows:
                    // int arrayHighest = 0;
                    // for (Integer value : inputArray) {
                    // if (value > arrayHighest) {
                    // arrayHighest = value;
                    // }
                    // }
                    // System.out.println("The HIGHEST value is: " + arrayHighest + "\n");

                    break;
                case 3:
                    System.out.println("The AVERAGE value is: " + inputAverage + "\n");

                    // You can add more code in case block if you need to add more logic as it
                    // follows:
                    // double arrayAverage = (double) inputTotal / (double) listLength;
                    // System.out.println("The AVERAGE value is: " + arrayAverage + "\n");

                    break;
                case 4:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("That is not a valid option" + "\n");
                    break;
            }
        } while (inputInt != 4);
    }
}
