/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package numberplateprinter;

import inpututilities.InputUtilities;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author Ken Healy
 */
public class NumberPlatePrinter {

    /*
     * OVERVIEW
     *
     * This program will generate number plates and print them to a file
     * Max 10000 number plates allowed
     * User will be asked for a year (from 1990 to 2023)
     * Also asked for no. of plates to create (subject to max)
     * Also asked for a County name - this is not validated; we could validate it
     * but that would add some extra complexity
     *
     * Program generates plates in standard Irish format
     *
     * YY - C - NNNNN
     *
     * Where YY = year; C = county letter; NNNNN = number
     *
     */
    public static void main(String[] args) {

        InputUtilities myInput = new InputUtilities();

        String county; // what county
        int numOfPlates; // how many to print
        int year; // what year is it?

        // step 1 - get year input from user

        year = myInput.askUserForInt("Enter year", 1990, 2023);

        county = myInput.askUserForText("Please enter a county name");

        numOfPlates = myInput.askUserForInt("How many number plates would you like to print?", 1, 9999);

        // step 2 - extract the bits we need to print

        String printCounty = county.substring(0, 1); // the county bit is fairly straightforward - just the
                                                     // first letter.

        // This bit is more complex. It can be done in one step, but I will break it
        // into two

        String printYear = Integer.toString(year); // convert year to a String value using Integer.toString()

        printYear = printYear.substring(2); // get last two characters - I know this works because I know a year
                                            // only has 4 characters!

        /*
         * The shorter version is:
         *
         * printYear = Integer.toString(year).substring(2);
         */

        // Step 3 print a plate list to file

        try {
            // need a try catch for file access!

            BufferedWriter myWriter = new BufferedWriter(new FileWriter("licences.txt", false));
            // remember - true is append to end of file
            // I have used false so that any previous data is wiped and it is a 'fresh' file
            // each time

            for (int plateNum = 1; plateNum <= numOfPlates; plateNum++) {

                // To get the number plate length correct, we will need extra zeros

                String padding; // to store the extra zeros

                // the amounf of extra zeros varies depending on what no license plate we are
                // dealing with
                if (plateNum < 10) {

                    padding = "0000";

                } else if (plateNum < 100) {

                    padding = "000";

                } else if (plateNum < 1000) {

                    padding = "00";

                } else {

                    padding = "0";
                }

                // so each line is the year followed by - followed by the county lettey, then
                // another - and then the 'extra zeros' and lastly the current number
                myWriter.write(printYear + " - " + printCounty + " - " + padding
                        + Integer.toString(plateNum));
                myWriter.newLine(); // goes to next line

            }
            // print finished. don't forget to save
            myWriter.close();

        } catch (Exception e) {

            System.out.println("ERROR WRITING TO FILE");
        }
    }
}
