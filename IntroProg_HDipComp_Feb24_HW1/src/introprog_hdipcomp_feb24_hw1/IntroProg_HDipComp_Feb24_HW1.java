/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package introprog_hdipcomp_feb24_hw1;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Scanner;

/**
 *
 * @author Joel Tola
 *         Program that will ask the user to enter the marks
 *         they have received for an assignment that is worth 25% of the marks
 *         for the module.
 *         The maximum mark that can be input is 100 and the minimum is 0
 *         (zero).
 *         Output an error message if they enter any other number OR if they do
 *         not enter a number at all.
 *         If they enter a valid number, output the PERCENTAGE equivalent.
 *         The maximum result should be 25%. It should be possible to output a
 *         decimal value (e.g. 11.5%).
 *         EXAMPLE OUTPUT:
 *         >> Please enter your mark
 *         <<user enters 70>>
 *         >> You scored 17.5% out of a maximum of 25%
 */
public class IntroProg_HDipComp_Feb24_HW1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int mark;
        double markPersentage;
        DecimalFormat decFor = new DecimalFormat("0.00");
        boolean isValidMark = false;

        while (!isValidMark) {
            System.out.println("Please enter your mark");
            mark = readNumber();
            if (mark > 0 && mark < 100) {
                isValidMark = true;
                markPersentage = ((double)mark / 100) * 25;
                String message = MessageFormat.format("You scored {0}% out of a maximum of 25%",
                        decFor.format(markPersentage));
                System.out.println(message);
            } else {
                System.out.println("Please enter a number between 0 and 100");
            }
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
