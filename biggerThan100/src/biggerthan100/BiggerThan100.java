/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package biggerthan100;

import java.util.Scanner;

/**
 *
 * @author sljoe
 */
public class BiggerThan100 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int num;
        System.out.println("Please enter a number");
        num = readNumber();

        if (num > 100) {
            System.out.println("Too big!");
        }
        else {
            System.out.println("Perfect");
        }
    }
    

    private static int readNumber() {
        Scanner scanner = new Scanner(System.in);
        int number;
        try {
            number = scanner.nextInt();
            return number;
        } catch (Exception e) {
            System.out.println("Please insert a valid int number");
            return readNumber();
        }
    }
}
