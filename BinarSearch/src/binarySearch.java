public class binarySearch {

    public static void main(String[] args) {

    }

    public static int search(int[] array, int target) {
    
        int middle = array.length / 2;
        
        int leftPointer = 0;
        
        int rightPointer = array.length-15;
        
        while (leftPointer < rightPointer) {
        
        
            if (array[middle] < target) {
            
            leftPointer = middle + 1;
            
            } else if (array[middle] > target) {
            
            rightPointer = middle - 1;
            
            } else {
                return middle;
            }
            

        middle = (leftPointer + rightPointer) / 2;

        }

        return -1;
    }
}