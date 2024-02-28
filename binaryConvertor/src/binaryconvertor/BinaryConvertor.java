/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package binaryconvertor;

import java.io.Console;
import java.text.MessageFormat;
import java.util.Scanner;

/**
 *
 * @author sljoe
 */
public class BinaryConvertor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String stringBinNumber;
        System.out.println("Binary convertor");
        System.out.println("Please enter a number");
        int decNumber = readNumber();

        // stringBinNumber = convertDecToBinary(decNumber);
        // System.out.println(MessageFormat.format("The number {0} in Binary is {1}", decNumber, stringBinNumber));

        System.out.println(MessageFormat.format("The number {0} in Binary is: ", decNumber));
        decimalToBinary(decNumber);


    }

    private static String convertDecToBinary(int decNumber) {
        StringBuilder stringNumber = new StringBuilder();

        while (decNumber > 0) {
            if (decNumber % 2 == 0) {
                stringNumber = stringNumber.append("0");
            } else {
                stringNumber = stringNumber.append("1");
            }
            decNumber = (int) decNumber / 2;
        }
        stringNumber = stringNumber.reverse();

        return stringNumber.toString();
    }

    // Function to display the binary equivalent
    // of an integer
    public static int decimalToBinary(int val) {
        int binary;
        if (val != 0) {
            decimalToBinary(val / 2);
            binary = (val % 2);
            System.out.print(binary);
            return 0;
        } else {
            return 0;
        }
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
}
