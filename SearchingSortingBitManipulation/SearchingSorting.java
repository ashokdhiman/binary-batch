package SearchingSortingBitManipulation;
import java.util.Scanner;

public class SearchingSorting {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Problem 1: Linear Search
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.print("Enter the elements of the array: ");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.print("Enter the element to search (X): ");
        int x = scanner.nextInt();
        int index = linearSearch(array, x);
        System.out.println(index == -1 ? "Element not found in array" : "Element found at index: " + index);

        // Problem 2: Last Occurrence
        System.out.print("Enter the target for last occurrence search: ");
        int target = scanner.nextInt();
        System.out.println("Last occurrence of target: " + lastOccurrence(array, target));

        // Problem 3: Count Total 1's in Sorted Binary Array
        System.out.println("Total number of 1's in array: " + countOnes(array));

        // Problem 4: Count Occurrences of a Number
        System.out.print("Enter the target to count occurrences: ");
        int targetToCount = scanner.nextInt();
        int count = countOccurrences(array, targetToCount);
        if (count == 0) {
            System.out.println("Target " + targetToCount + " not found in the array.");
        } else {
            System.out.println("Target " + targetToCount + " occurs " + count + " times.");
        }

        // Problem 5: Check if Number is a Perfect Square
        System.out.print("Enter a number to check if it's a perfect square: ");
        int num = scanner.nextInt();
        System.out.println(isPerfectSquare(num) ? "True" : "False");

        scanner.close();
    }

    public static int linearSearch(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public static int lastOccurrence(int[] arr, int target) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int countOnes(int[] arr) {
        int count = 0;
        for (int num : arr) {
            if (num == 1) {
                count++;
            }
        }
        return count;
    }

    public static int countOccurrences(int[] arr, int target) {
        int count = 0;
        for (int num : arr) {
            if (num == target) {
                count++;
            }
        }
        return count;
    }

    public static boolean isPerfectSquare(int num) {
        int left = 1, right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}

