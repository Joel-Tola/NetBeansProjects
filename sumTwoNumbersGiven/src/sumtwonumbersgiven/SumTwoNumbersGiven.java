/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sumtwonumbersgiven;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Scanner;

/**
 *
 * @author sljoe
 */
public class SumTwoNumbersGiven {

    /**
     * 3)	Write a program using a Scanner that will ask the user to enter 2 numbers (separately). 
     * Then add the two numbers together and output a message saying 
     * “The sum of <first number> and <second number> is <answer>”
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int num1;
        int num2;

        System.out.println("Read line using Scanner");
        System.out.println("To sum two numbers please");
        System.out.println("Insert the first number");
        num1 = readNumber();
        System.out.println("Insert the second number");
        num2 = readNumber();
        System.out.println(MessageFormat.format("The sum of {0} and {1} is {2}", num1, num2, num1 + num2));

        System.out.println("--------------------------------");

        System.out.println("Read line using Buffer Reader");
        System.out.println("To sum two numbers please");
        System.out.println("Insert the first number");
        num1 = readNumberByBufferReader();
        System.out.println("Insert the second number");
        num2 = readNumberByBufferReader();
        System.out.println(MessageFormat.format("The sum of {0} and {1} is {2}", num1, num2, num1 + num2));
    }

    private static int readNumber() {
        Scanner scanner = new Scanner(System.in);
        int number;
        try {
            number = scanner.nextInt();
            return number;
        } catch (Exception e) {
            System.out.println("Please insert a valid number");
            return readNumber();
        }
    }

    
    private static int readNumberByBufferReader() {
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return Integer.parseInt(bufferReader.readLine());
        } catch (Exception e) {
            System.out.println("Please insert a valid number");
            return readNumberByBufferReader();
        }
    }
}
