/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package questcolor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Scanner;

/**
 *
 * @author sljoe
 */
public class QuestColor {

    /**
     * 2) Create a new project. Ask the user TWO questions (one at a time) 
     * and each time read in the user’s answer:
     * Q1: What is your quest?
     * Q2: What is your favorite color?
     * Output a message saying: 
     * “Ah, I see your quest is to <user quest goes here>, and your favorite color is <user color goes here>”.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String myQuest;
        String myColor;

        System.out.println("Read line using Scanner");
        System.out.println("What is your quest?");
        System.out.println("What is your favorite color?");
        myQuest = readLine();
        myColor = readLine();
        System.out.println(MessageFormat.format("Ah, I see your quest is to {0}, and your favorite color is {1}", myQuest, myColor));
    
        System.out.println("--------------------------------");

        System.out.println("Read line using Buffer Reader");
        System.out.println("What is your quest?");
        System.out.println("What is your favorite color?");
        myQuest = readLineByBufferReader();
        myColor = readLineByBufferReader();
        System.out.println(MessageFormat.format("Ah, I see your quest is to {0}, and your favorite color is {1}", myQuest, myColor));
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
