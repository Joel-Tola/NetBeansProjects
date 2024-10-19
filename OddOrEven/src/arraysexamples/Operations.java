package arraysexamples;

public class Operations {
    // we are going tp conduct some operations on an array.
    // The maximum absolute difference of the array
    // Largest element and second largest
    // Compare 2 arrays
    // Reverse the order of the array

    // Test the methods
    public static void main(String[] args) {
        Operations operations = new Operations();

        int[] arr = {3, 1, 4, 1, 5, 9};
        System.out.println("Max: " + getMax(arr));
        System.out.println("Second Max: " + secondMax(arr));
        System.out.println("Max Absolute Difference: " + getMaximumAbsolute(arr));

        int[] arr2 = {3, 1, 4, 1, 5, 9};
        System.out.println("Arrays equal: " + compareArrays(arr, arr2));

        reverseArray(arr);
        System.out.println("Reversed Array: ");
        for (int value : arr) {
            System.out.print(value + " ");
        }
    }

    // Method to find the maximum absolute difference of the array
    public static int getMaximumAbsolute(int[] arr) {
        int a = 0;
        int x = 0;
        int y = 0;
        int z = 0;

        for (int i = 0; i < arr.length; i++) {
            x = arr[i];
            y = arr[ i + 1];
            // get the two value difference
            z = x - y;

            //compare the current maximum difference
            if (z > a) {
                a = z; // A become the stored the absolute maximum
            }
        }
        return a;
    }

    // Method to find the largest element in the array
    public static int getMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must have at least one element");
        }

        int max = arr[0];
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    // Method to find the second-largest element in the array
    public static int secondMax(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Array must have at least two elements");
        }

        int firstLargest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int value : arr) {
            if (value > firstLargest) {
                secondLargest = firstLargest;
                firstLargest = value;
            } else if (value > secondLargest && value != firstLargest) {
                secondLargest = value;
            }
        }

        return secondLargest;
    }

    // Method to compare two arrays
    public static boolean compareArrays(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            return false;
        }

        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // Method to reverse the order of the array
    public static void reverseArray(int[] arr) {
        if (arr == null) {
            return;
        }

        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }
}
