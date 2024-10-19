package arraysexamples;

import java.util.Arrays;

public class Performance {
    // This class will be in charge of assessing the performance of
    //the algorithms that we constructed

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 7, 9, 8, 6, 4, 5, 17 , 0, 21};
        int key = 17;
        LinearSearch myLinearSearch = new LinearSearch();
        long timerStart;
        long timerEnd;

        System.out.println("Linear Search");

        // // Timer start for inline declaration
        // timerStart = System.nanoTime();
        // System.out.println("The target number for linear search is found at index: " + myLinearSearch.linearSearch(numbers, key));
        // timerEnd = System.nanoTime();
        // System.out.println("The total time it took to run Linear Search in inline declaration: " + (timerEnd - timerStart));
        // // Timer end and print

        // Timer start for normal declaration
        timerStart = System.nanoTime();
        int result = LinearSearch.linearSearch(numbers, key);
        timerEnd = System.nanoTime();
        System.out.println("The target number for linear search is found at index: " + result);
        System.out.println("The total time it took to run Linear Search in normal declaration: " + (timerEnd - timerStart));
        // Time end and print

        BinarySearch myBinarySearch = new BinarySearch();
        // Binary Search ALWAYS require the arrays to be sorted.
        Arrays.sort(numbers);
        timerStart = System.nanoTime();
        result = myBinarySearch.binarySearch(numbers, key);
        timerEnd = System.nanoTime();
        System.out.println("The target number for binary search is found at index: " + result);
        System.out.println("The total time it took to run Binary Search in normal declaration: " + (timerEnd - timerStart));
    }
}
