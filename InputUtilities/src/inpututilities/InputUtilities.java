/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inpututilities;

import java.util.Scanner;

/**
 * HDIP Comp Feb 2024 cohort
 * @author Joel
 */
public class InputUtilities {
    Scanner myKB = new Scanner(System.in);

    /**
     * Ask for a text to the user until user insert a valid text
     * @param prompt that the user will see
     * @return userInput
     */
    public String askUserForTex(String prompt) {
        String userInput ;
        
        do{
            System.out.println(prompt);
            System.out.println("Enter some text please - no numbers!");
            userInput = myKB.nextLine();
            
        } while (!userInput.matches("[a-zA-Z!.,@\"?]+"));
        
        return userInput;
    }
    
    /**
     * Ask the user to enter any integer value
     * if they do not a valid integer value will ask again
     * @param prompt the question or prompt to ask the user
     * @return a valid int entered by the user
     */
    public int askUserForInt(String prompt) {
        String userInput ;
        
        do{
            System.out.println("Enter integer values only please!");
            userInput = myKB.nextLine();
            
        } while (!userInput.matches("[0-9]+"));
        
        return Integer.parseInt(userInput);
    }
}
