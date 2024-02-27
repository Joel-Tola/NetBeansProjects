/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package simplescannerexample;

import java.text.MessageFormat;
import java.util.Scanner;

/**
 *
 * @author sljoe
 */
public class SimpleScannerExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner myScanner = new Scanner(System.in);

        // String EXPECTED_STRING = "String Interpolation in Java with some Java examples.";
        // String first = "Interpolation";
        // String second = "Java";
        // String result = String.format("String %s in %s with some %s examples.", first, second, second);

        try {
            System.out.println("Insert your name");
            String myName = readLine();
            System.out.println("What is your age?");
            int myAge = readNumber();
            System.out.println(MessageFormat.format("Your Name is {0}, your Age is {1}", myName, myAge));
        } catch (Exception e) {
            throw e;
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

    private static String readLine() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            throw e;
        } finally {
            scanner.close();
        }
    }
}
