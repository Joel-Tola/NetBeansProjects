/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package simplescopeexample;

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

        InputUtilities myInput = new InputUtilities();

        String userName = myInput.askUserForTex("What is your name?");

        System.out.println("Hello " + userName);

        int userAge = myInput.askUserForInt("What is your age");

        System.out.println("You do not look as old " + userName);

    }
    
}
