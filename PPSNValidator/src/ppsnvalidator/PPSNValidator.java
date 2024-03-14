/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppsnvalidator;

import java.util.Scanner;

/**
 *
 * @author Ken Healy
 */
public class PPSNValidator {

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Ask user to enter a PPSN
        //check to see if it is valid and output an appropriate message
        
        String userPPSN = "";
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("Please enter your PPSN:");
        
        try{
            userPPSN = myScanner.nextLine(); // read in PPSN
            userPPSN = userPPSN.trim(); //get rid of pointless spaces
            
            if ( userPPSN.length() == 8 || userPPSN.length() == 9){
                //length is OK -- must have 8 or 9 chars
                
                //check the rest
                String firstSeven = userPPSN.substring(0,7); //first 7 chars
                String lastBit = userPPSN.substring(7); //last 1 or 2 chars
                
                if ( (lastBit.matches("[A-Za-z]+")) && firstSeven.matches("[0-9]+")) {
                   //valid 
                    System.out.println("Thank you " + userPPSN + " is a valid PPSN.");
                   }
                else {
                    //not valid
                    System.out.println("Invalid - you must have 7 digits followed by 1 or 2 letters.");
                   }
                
            } else {
                //not a valid length
                System.out.println("Invalid PPSN - must be 8 or 9 charachters");
            }
        }catch(Exception e) {
            
        }
    }
        
    }
    

