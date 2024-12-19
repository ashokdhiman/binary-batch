package DevideAndConquer;
import java.util.*;

public class InversionCount {

    // Function to perform Quick Select
    public static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];

        int pivotIndex = partition(arr, left, right);
        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }

    // Partition function using Lomuto's partition scheme
    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    // Swap helper function
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Function to find the kth largest element
    public static int findKthLargest(int[] arr, int k) {
        // Convert kth largest to 0-based index
        return quickSelect(arr, 0, arr.length - 1, arr.length - k);
    }

    public static void main(String[] args) {
        // Test case 1
        int[] arr1 = {1, 3, 2, 4, 5, 6, 7};
        int k1 = 3;
        System.out.println("Output 1: " + findKthLargest(arr1, k1));  // Output: 5

        // Test case 2
        int[] arr2 = {4, 3, 3, 2, 1};
        int k2 = 4;
        System.out.println("Output 2: " + findKthLargest(arr2, k2));  // Output: 2

        // Questions
        System.out.println("\nTime complexity of Quick Select algorithm:");
        System.out.println("Answer: " + "a) O(n)");

        System.out.println("\nData structure used in Quick Select algorithm:");
        System.out.println("Answer: " + "b) Array");

        System.out.println("\nPartitioning scheme used in Quick Select algorithm:");
        System.out.println("Answer: " + "a) Lomuto partition scheme");

        System.out.println("\nWorst-case time complexity of Quick Select algorithm:");
        System.out.println("Answer: " + "b) O(n^2)");

        System.out.println("\nPivot element in Quick Select algorithm:");
        System.out.println("Answer: " + "d) A random element");
    }
}

