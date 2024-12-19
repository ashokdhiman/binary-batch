public class RecursionProblems {

    public static void main(String[] args) {
        // Q1: Sum of digits using recursion
        int n1 = 1234;
        System.out.println("Sum of digits: " + sumOfDigits(n1));

        // Q2: Sum of natural numbers with alternate signs
        int n2 = 10;
        System.out.println("Sum with alternate signs: " + alternateSum(n2));

        // Q3: Maximum value in the array
        int[] arr1 = {13, 1, -3, 22, 5};
        System.out.println("Maximum value: " + findMax(arr1, arr1.length));

        // Q4: Sum of values in the array
        int[] arr2 = {92, 23, 15, -20, 10};
        System.out.println("Sum of array values: " + findSum(arr2, arr2.length));

        // Q5: Check if a number is an Armstrong number
        int n3 = 153;
        System.out.println(isArmstrong(n3) ? "Yes" : "No");
    }

    // Q1: Sum of digits using recursion
    public static int sumOfDigits(int n) {
        if (n == 0) return 0;
        return n % 10 + sumOfDigits(n / 10);
    }

    // Q2: Sum of natural numbers with alternate signs
    public static int alternateSum(int n) {
        if (n == 0) return 0;
        return n % 2 == 0 ? -n + alternateSum(n - 1) : n + alternateSum(n - 1);
    }

    // Q3: Find maximum value in an array using recursion
    public static int findMax(int[] arr, int n) {
        if (n == 1) return arr[0];
        return Math.max(arr[n - 1], findMax(arr, n - 1));
    }

    // Q4: Find sum of array values using recursion
    public static int findSum(int[] arr, int n) {
        if (n == 0) return 0;
        return arr[n - 1] + findSum(arr, n - 1);
    }

    // Q5: Check if a number is an Armstrong number
    public static boolean isArmstrong(int n) {
        int digits = (int) Math.log10(n) + 1;
        return n == armstrongHelper(n, digits);
    }

    private static int armstrongHelper(int n, int digits) {
        if (n == 0) return 0;
        int digit = n % 10;
        return (int) Math.pow(digit, digits) + armstrongHelper(n / 10, digits);
    }
}
