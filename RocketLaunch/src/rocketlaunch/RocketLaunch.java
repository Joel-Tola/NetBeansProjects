/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocketlaunch;

import java.util.Scanner;

/**
 *
 * @author Ken
 */
public class RocketLaunch {

    /**
     * Simple for loop demo counting backwards
     */
    public static void main(String[] args) {
        
        Scanner myKB = new Scanner(System.in);
        
        //start at 10
        // keep going while bigger than 0
        //count backwards by 1
        for (int i = 10; i > 0; i--){
            
            System.out.println(i + "...");
            myKB.nextLine(); //pause until user hits enter
        }
        //must have got to zero
        
        System.out.println("LAUNCH!!!!!");
        
    }
    
}
