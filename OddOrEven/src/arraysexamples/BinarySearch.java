package arraysexamples;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {

        int key = 7;
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        System.out.println("Looking for " + key + "in " + Arrays.toString(args));

    }
    
    public static int binarySearch(int[] arr, int key) {
        
        int middle = arr.length / 2;
        int left = 0;
        int right = arr.length -1;

        while (right >= left) {
            if (key < arr[middle]) {
                right = middle - 1;
            } else if (key > arr[middle]) {
                left = middle + 1;
            } else {
                return middle;
            }

            middle = (left + right) / 2;
        }

        return -1;
    }
}
