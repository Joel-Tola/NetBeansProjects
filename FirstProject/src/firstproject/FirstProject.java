/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package firstproject;

import java.lang.Object;
import java.math.BigDecimal;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Number;

/**
 *
 * @author sljoe
 */
public class FirstProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = "C:/Users/sljoe/Documents/NetBeansProjects/FirstProject/src/firstproject/Weather Data.csv"; // Replace with your file path
        String line = "";
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            line = br.readLine();
            String[] columns = line.split(",");
            String column1 = columns[0];
            String column2 = columns[1];
            String column3 = columns[2];
            int totalTemperatures = 0;
            int counter = 0;
            int avTemperatures;
            
            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] values = line.split(",");
                int temp = Integer.parseInt(values[1]);
                totalTemperatures =+ temp;
                counter++;

                System.out.print(column1 + " " + values[0] + ", " + column2 + " " + values[1] + ", " + column3  + " " + values[2]);
                
                // // Print out the parsed values
                // for(String value : values) {
                //     System.out.print(value + " ");
                // }
                System.out.println(); // Print a newline after each row
            }
            
            br.close();

            avTemperatures = totalTemperatures / counter;

            System.out.print("Average temps: " + avTemperatures);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
