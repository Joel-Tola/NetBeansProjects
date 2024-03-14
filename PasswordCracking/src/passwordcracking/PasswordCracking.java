/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package passwordcracking;

import static java.lang.System.currentTimeMillis;

/**
 *
 * @author Lecturer
 */
public class PasswordCracking {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String password = "LaOiS";
        // String password = "!aOiS";
        int count = 0;
        long start = currentTimeMillis();
        for (int num1 = 65; num1 <= 122; num1++) {
            for (int num2 = 65; num2 <= 122; num2++) {
                for (int num3 = 65; num3 <= 122; num3++) {
                    for (int num4 = 65; num4 <= 122; num4++) {
                        for (int num5 = 65; num5 <= 122; num5++) {
                            char char1 = (char) num1;
                            char char2 = (char) num2;
                            char char3 = (char) num3;
                            char char4 = (char) num4;
                            char char5 = (char) num5;
                            count++;
                            String guess = String.valueOf(char1) + String.valueOf(char2) + String.valueOf(char3)
                                    + String.valueOf(char4) + String.valueOf(char5);
                            if (guess.equals(password)) {
                                System.out.println(
                                        "Password found in: " + ((currentTimeMillis() - start) / 1000) + " seconds.");
                                System.out.println("It took: " + count + " guesses.");
                                System.exit(0);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Password not found.");
    }

}
