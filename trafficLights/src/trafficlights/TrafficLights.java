/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trafficlights;

import java.util.Scanner;

/**
 *
 * @author sljoe
 */
public class TrafficLights {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String color;
        System.out.println("Please enter a traffic light color");
        color = readLine();
        color = color.toLowerCase();

        switch (color) {
            case "red":
                System.out.println("Stop!");
                break;
            case "orange":
                System.out.println("Slow down");
                break;
            case "green":
                System.out.println("Keep going");
                break;
            default:
                System.out.println("That is not a traffic light color!");
                break;
        }

        System.out.println("Please enter a number");
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
