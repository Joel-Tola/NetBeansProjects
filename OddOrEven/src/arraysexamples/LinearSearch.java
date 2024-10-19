package arraysexamples;

public class LinearSearch {

    public static void main(String[] args) {
        int[] arr3 = {7,10,21,45,70,66};
        int key = 70;

        System.out.println("the key " + key + " was found at " + linearSearch(arr3, key));
    }
    
    public static int linearSearch(int[] arr, int key) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }

        return -1;
    }
}
