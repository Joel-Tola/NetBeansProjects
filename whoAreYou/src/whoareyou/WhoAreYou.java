/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package whoareyou;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Scanner;

/**
 *
 * @author sljoe
 */
public class WhoAreYou {

    /**
     * 1) Create a new Project called whoAreYou.
     * Write a piece of code that will prompt the user (i.e. output a question) for
     * their name.
     * Read in the name using a Scanner and output a message saying:
     * “Oh, that is a very nice name, <user name goes here>”
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Read line using Scanner");
        System.out.println("Who are you?");
        System.out.println(MessageFormat.format("Oh, that is a very nice name, {0}", readLine()));

        System.out.println("--------------------------------");

        System.out.println("Read line using Buffer Reader");
        System.out.println("Who are you?");
        System.out.println(MessageFormat.format("Oh, that is a very nice name, {0}", readLineByBufferReader()));

    }

    private static String readLine() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            throw e;
        }
    }

    private static String readLineByBufferReader() {
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferReader.readLine();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "";
    }
}
