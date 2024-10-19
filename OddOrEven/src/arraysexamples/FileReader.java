package arraysexamples;

import java.util.Scanner;
import oddoreven.OddOrEven;

import java.io.File;

public class FileReader {
    
    public static void main(String[] args) {
        int odd = 0;
        int even = 0;

        Scanner inputData = new Scanner(System.in);
        System.out.println("Please provide the file name");

        String fileName = inputData.nextLine();

        try {

            Scanner inputFile = new Scanner(new File(fileName));

            while (inputFile.hasNextInt()) {
                int value = inputFile.nextInt();
                if (OddOrEven.isEven(value)) {
                    even++;
                } else {
                    odd++;
                }
            }
            
        } catch (Exception e) {
            System.out.println("Unable to read file, please make sure the file exits and matches with the name provided");
            e.printStackTrace();
        }
        
    }
}
