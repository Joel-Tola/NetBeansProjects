import java.util.Scanner;

/**
 * This class is a simple password checker program.
 */
public class PasswordChecker {

    /**
     * The main method of the program.
     * It prompts the user to input a password and checks if it matches the correct
     * password.
     * It allows a maximum number of attempts before denying access.
     * 
     * @param args The command-line arguments passed to the program (not used in
     *             this program).
     */
    public static void main(String[] args) {

        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);

        // Initialize variables
        String userInput;
        String correctPassword = "secret";
        int attempts = 5;
        boolean isAccessGranted = false;

        // Loop until either the correct password is entered or the maximum attempts are
        // reached
        do {
            System.out.println("Please insert your password");
            userInput = scanner.nextLine(); // Read user input

            // Check if the correct password is entered
            if (userInput.equals(correctPassword)) {
                isAccessGranted = true;
                System.out.println("Access granted");
            } else {
                attempts--; // Decrement the attempts counter
                System.out.println("Incorrect. " + attempts + " more attempts remaining");

                // Check if the maximum attempts are reached
                if (attempts == 0) {
                    System.out.println("Maximum attempts reached.");
                }
            }
        } while (!isAccessGranted && attempts > 0);

        // Close the Scanner object to release resources
        scanner.close();
    }
}
