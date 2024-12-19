package SearchingSortingBitManipulation;

import java.util.Arrays;

public class SortingExamples {

    public static void main(String[] args) {
        int[] inputArray = {3, 5, 1, 6, 0};

        int[] bubbleSortedArray = inputArray.clone();
        bubbleSortDescending(bubbleSortedArray);
        System.out.println("Bubble Sorted Array (Descending): " + Arrays.toString(bubbleSortedArray));

        int[] selectionSortedArray = inputArray.clone();
        selectionSortDescending(selectionSortedArray);
        System.out.println("Selection Sorted Array (Descending): " + Arrays.toString(selectionSortedArray));

        int[] insertionSortedArray = inputArray.clone();
        insertionSortDescending(insertionSortedArray);
        System.out.println("Insertion Sorted Array (Descending): " + Arrays.toString(insertionSortedArray));

        int bubblePasses = countBubbleSortPasses(inputArray.clone());
        System.out.println("Number of Passes for Bubble Sort: " + bubblePasses);

        int selectionIterations = countSelectionSortIterations(inputArray.clone());
        System.out.println("Number of Iterations for Selection Sort: " + selectionIterations);
    }

    public static void bubbleSortDescending(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSortDescending(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[maxIdx]) {
                    maxIdx = j;
                }
            }
            int temp = arr[maxIdx];
            arr[maxIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertionSortDescending(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static int countBubbleSortPasses(int[] arr) {
        int n = arr.length;
        int passCount = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped) {
                passCount++;
            }
        }
        return passCount;
    }

    public static int countSelectionSortIterations(int[] arr) {
        int n = arr.length;
        int iterationCount = 0;
        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[maxIdx]) {
                    maxIdx = j;
                }
                iterationCount++;
            }
            int temp = arr[maxIdx];
            arr[maxIdx] = arr[i];
            arr[i] = temp;
        }
        return iterationCount;
    }
}
