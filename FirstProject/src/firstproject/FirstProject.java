/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package firstproject;

import java.lang.Object;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Number;

/**
 *
 * @author sljoe
 */
public class FirstProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = "C:/Users/sljoe/Documents/NetBeansProjects/FirstProject/src/firstproject/Weather Data.csv"; // Replace with your file path
        String line = "";

        Properties prop = new Properties();
        InputStream myInput = null;

        try {
            // myInput = new FileInputStream("C:/Users/sljoe/Documents/NetBeansProjects/FirstProject/src/firstproject/config.properties");
            myInput = new FileInputStream("../NetBeansProjects/FirstProject/src/firstproject/config.properties");
            prop.load(myInput);

            System.out.println(prop.getProperty("prop1"));
            System.out.println(prop.getProperty("testProp"));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            if (myInput != null) {
                try {
                    myInput.close();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }
        
    //     try {
    //         BufferedReader br = new BufferedReader(new FileReader(path));

    //         line = br.readLine();
    //         String[] columns = line.split(",");
    //         String column1 = columns[0];
    //         String column2 = columns[1];
    //         String column3 = columns[2];
    //         int totalTemperatures = 0;
    //         int counter = 0;
    //         int avTemperatures;
            
    //         while ((line = br.readLine()) != null) {
    //             // Use comma as separator
    //             String[] values = line.split(",");
    //             int temp = Integer.parseInt(values[1]);
    //             totalTemperatures =+ temp;
    //             counter++;

    //             System.out.print(column1 + " " + values[0] + ", " + column2 + " " + values[1] + ", " + column3  + " " + values[2]);
                
    //             // // Print out the parsed values
    //             // for(String value : values) {
    //             //     System.out.print(value + " ");
    //             // }
    //             System.out.println(); // Print a newline after each row
    //         }
            
    //         br.close();

    //         avTemperatures = totalTemperatures / counter;

    //         System.out.print("Average temps: " + avTemperatures);
            
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     // Set up a Scanner to read input from the console
    //     Scanner scanner = new Scanner(System.in);

    //     // Prompt the user to enter their boarding pass number
    //     System.out.println("Please insert your boarding pass number");

    //     String input;
    //     boolean isInputValid = false;

    //     do {
    //         // Read the next line of input from the user
    //         input = scanner.nextLine();

    //         // Check if the input consists only of letters and numbers
    //         boolean isJustLettersAndNumbers = input.matches("[A-Za-z0-9]+");

    //         // Check the length of the input to be exactly 10 or 11 characters
    //         boolean isLengthValid = input.length() == 10 || input.length() == 11;

    //         // Extract the first two characters and convert to uppercase
    //         String firstTwo = input.length() >= 2 ? input.substring(0, 2).toUpperCase() : "";

    //         // Check if the first two characters are either "EI" or "FR"
    //         boolean isStartValid = firstTwo.matches("EI|FR");

    //         // Check if the body (excluding the first two and the last two characters) consists only of digits
    //         boolean isBodyValid = input.substring(2, input.length() - 2).matches("\\d+");

    //         // Check if the last two characters are "22"
    //         boolean isEndingValid = input.endsWith("22");

    //         // Validate the input based on all the above conditions
    //         isInputValid = isJustLettersAndNumbers && isLengthValid && isStartValid && isBodyValid && isEndingValid;

    //         // If the input is not valid, prompt the user to enter a valid boarding pass number
    //         if (!isInputValid) {
    //             System.out.println("Invalid boarding pass number. Please try again.");
    //         }

    //     } while (!isInputValid);

    //     // Close the scanner to avoid resource leaks
    //     scanner.close();

    //     // Indicate that the boarding pass number is valid
    //     System.out.println("Boarding pass number is valid.");
    }
}
