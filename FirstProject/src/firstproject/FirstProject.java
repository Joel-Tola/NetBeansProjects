/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package firstproject;
import java.lang.Object;
import java.math.BigDecimal;
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
        // TODO code application logic here
        System.out.println("Hello");
        int a = 10;
          int b = 20;
 
        System.out.println("Before swapping, a = " + a + " and b = " + b);
 
        // Swapping a and b using XOR
        a = a ^ b;
        System.out.println("swapping, a = " + a + " ^ " + b);
        b = a ^ b;
        System.out.println("swapping, a = " + a + " ^ " + b);
        a = a ^ b;
 
        System.out.println("After swapping, a = " + a + " and b = " + b);

        float test = 12.3f;

        String string = "test";

    }
    
}
