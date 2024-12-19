package DevideAndConquer;

import java.util.*;

public class DAndC {

    // Q1. Sort the array with two swapped elements in linear time.
    public static void sortSwappedArray(int[] arr) {
        int n = arr.length;
        int x = -1, y = -1;

        // Find the two elements that are swapped.
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                if (x == -1) {
                    x = i;
                }
                y = i + 1;
            }
        }

        // Swap the two elements to restore the sorted order.
        if (x != -1 && y != -1) {
            int temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
        }
    }

    // Q2. Segregate negative and positive numbers (in linear time, constant space).
    public static void segregateArray(int[] arr) {
        int n = arr.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            if (arr[left] < 0 && arr[right] < 0) {
                left++;
            } else if (arr[left] >= 0 && arr[right] >= 0) {
                right--;
            } else if (arr[left] >= 0 && arr[right] < 0) {
                // Swap negative and positive elements
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            } else {
                left++;
                right--;
            }
        }
    }

    // Q3. Segregate negative and positive numbers while maintaining relative order (in linear time, constant space).
    public static void segregateArrayWithOrder(int[] arr) {
        List<Integer> negatives = new ArrayList<>();
        List<Integer> positives = new ArrayList<>();

        for (int num : arr) {
            if (num < 0) {
                negatives.add(num);
            } else {
                positives.add(num);
            }
        }

        // Merge the lists: negatives followed by positives
        int i = 0;
        for (int num : negatives) {
            arr[i++] = num;
        }
        for (int num : positives) {
            arr[i++] = num;
        }
    }

    // Q4. Check if permutation of two arrays exists such that a[i] + b[i] >= k
    public static boolean isPermutationExist(int[] a, int[] b, int k) {
        Arrays.sort(a);
        Arrays.sort(b);
        int n = a.length;

        for (int i = 0; i < n; i++) {
            if (a[i] + b[n - i - 1] < k) {
                return false;
            }
        }
        return true;
    }

    // Q5. Check if any two intervals overlap
    public static boolean areIntervalsIntersecting(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);  // Sort by start time

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= intervals[i - 1][1]) {
                return true; // Intervals overlap
            }
        }
        return false; // No overlap
    }

    public static void main(String[] args) {
        // Q1. Sort swapped array
        int[] arr1 = {3, 8, 6, 7, 5, 9, 10};
        sortSwappedArray(arr1);
        System.out.println("Q1 Output: " + Arrays.toString(arr1)); // Output: [3, 5, 6, 7, 8, 9, 10]

        // Q2. Segregate negative and positive numbers
        int[] arr2 = {19, -20, 7, -4, -13, 11, -5, 3};
        segregateArray(arr2);
        System.out.println("Q2 Output: " + Arrays.toString(arr2)); // Output: [-20, -13, -5, -4, 19, 11, 3, 7]

        // Q3. Segregate negative and positive numbers with order
        int[] arr3 = {19, -20, 7, -4, -13, 11, -5, 3};
        segregateArrayWithOrder(arr3);
        System.out.println("Q3 Output: " + Arrays.toString(arr3)); // Output: [-20, -4, -13, -5, 19, 7, 11, 3]

        // Q4. Check if permutation exists such that a[i] + b[i] >= k
        int[] a1 = {2, 1, 3};
        int[] b1 = {7, 8, 9};
        int k1 = 10;
        System.out.println("Q4 Output: " + (isPermutationExist(a1, b1, k1) ? "Yes" : "No")); // Output: Yes

        int[] a2 = {1, 2, 2, 1};
        int[] b2 = {3, 3, 3, 4};
        int k2 = 5;
        System.out.println("Q4 Output: " + (isPermutationExist(a2, b2, k2) ? "Yes" : "No")); // Output: No

        // Q5. Check if intervals intersect
        int[][] intervals1 = {{1, 3}, {5, 7}, {2, 4}, {6, 8}};
        System.out.println("Q5 Output: " + (areIntervalsIntersecting(intervals1) ? "Yes" : "No")); // Output: Yes

        int[][] intervals2 = {{1, 3}, {7, 9}, {4, 6}, {10, 13}};
        System.out.println("Q5 Output: " + (areIntervalsIntersecting(intervals2) ? "Yes" : "No")); // Output: No
    }
}

