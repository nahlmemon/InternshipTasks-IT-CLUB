public class BubbleSort {
    
    // Bubble Sort Algorithm
    public static int[] bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap the elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    // Method to print the array
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample Arrays for Sorting
        int[] arr1 = {45, 12, 89, 33, 67, 24, 78, 56, 90, 15};
        int[] arr2 = {5, 12, 19, 26, 33, 45, 58, 63, 77, 84};
        int[] arr3 = {90, 80, 70, 60, 50, 40, 30, 20, 10, 0};
        int[] arr4 = {-12, 56, -34, 23, 45, -67, 78, -89, 90, -15};
        int[] arr5 = {987, 123, 564, 776, 234, 879, 345, 112, 678, 901};

        // Perform bubble sort and print results
        System.out.println("Bubble Sort:");
        printArray(bubbleSort(arr1.clone())); // Clone to preserve the original array
        printArray(bubbleSort(arr2.clone()));
        printArray(bubbleSort(arr3.clone()));
        printArray(bubbleSort(arr4.clone()));
        printArray(bubbleSort(arr5.clone()));
    }
}