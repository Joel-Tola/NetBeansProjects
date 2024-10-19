package oddoreven;

import java.text.MessageFormat;
import java.util.Scanner;

import inpututilities.InputUtilities;


public class OddOrEven {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int odd = 0;
        int even = 0;
        int total = 0;
        InputUtilities inputHandler = new InputUtilities();
        String userInput = inputHandler.askUserForInput("Enter numbers separated by a comma: (i.e 1,2,3,4,5) or (end) tp Terminate");

        String[] numbers = userInput.split(",");

        for (String literalNumber : numbers) {
            if(literalNumber.trim().equalsIgnoreCase("end")){
                return;
            }

            try {

                int number = Integer.parseInt(literalNumber);

                if (isEven(number)) {
                    even++;
                } else {
                    odd++;
                }

                total++;
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input " + literalNumber + "invalid format.");
            }
             catch (Exception e) {
                System.out.println("General exeption " + e.getMessage() + "Trace: " + e.getStackTrace());
            }
        }

        System.out.println("You entered " + total + " numbers");
        System.out.println("Among them were " + even + " even numbers and " + odd + " odd numbers! ");
    }

    public static boolean isEven(int num) {
        if (num == 0 )
            return true;
        
        return num % 2 == 0;
    }
}
