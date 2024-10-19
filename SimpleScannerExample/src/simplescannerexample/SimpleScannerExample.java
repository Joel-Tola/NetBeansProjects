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
        String ans = "";
        int num;

        try {

            System.out.println("Insert a number please");
            num = readPositiveNumber();

            if (isEven(num)) {
                System.out.println("Number " + num + " is even");
            } else {
                System.out.println("Number " + num + " is odd");
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean isEven(int num) {
        if (num == 0 )
            return true;
        
        return num % 2 == 0;
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

    private static int readPositiveNumber() {
        Scanner scanner = new Scanner(System.in);
        int number;
        try {
            do {
                number = scanner.nextInt();
                if (number < 0) {
                    System.out.println("Please insert a positive number");
                }
            } while (number < 0);

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
