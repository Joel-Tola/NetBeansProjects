/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package simplescopeexample;

import java.util.ArrayList;

import inpututilities.InputUtilities;

/**
 *
 * @author sljoe
 */
public class SimpleScopeExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        InputUtilities handler = new InputUtilities();
        int userInput;
        // userInput = handler.askUserForInt("Enter any integer:");
        // System.out.println("You entered: " + userInput);

        // int userInput2 = handler.askUserForIntV2("Enter any integer:");
        // System.out.println("You entered: " + userInput2);

        // int userInput3 = handler.askUserForIntV3("Enter any integer:");
        // System.out.println("You entered: " + userInput3);

        // userInput = handler.askUserForInt("Enter an integer bigger than 5:", 5);
        // System.out.println("You entered: " + userInput);

        userInput = handler.askUserForInt("Enter an integer between 10 and 20:", 10, 20);
        System.out.println("You entered: " + userInput);

        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");

        // Enhanced for loop to iterate over the elements of the ArrayList
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
        
        
    }
}
