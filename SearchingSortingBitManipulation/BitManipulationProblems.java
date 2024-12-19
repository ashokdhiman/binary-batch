package SearchingSortingBitManipulation;
public class BitManipulationProblems {

    public static void main(String[] args) {
        // Problem 1: Binary Representation
        System.out.println("Binary Representation of 5: " + getBinary(5));
        System.out.println("Binary Representation of 10: " + getBinary(10));

        // Problem 2: Power of Two
        System.out.println("Is 15 a power of two? " + isPowerOfTwo(15));
        System.out.println("Is 32 a power of two? " + isPowerOfTwo(32));

        // Problem 3: Odd or Even
        System.out.println("Is 8 Even? " + isEven(8));
        System.out.println("Is 3 Even? " + isEven(3));

        // Problem 4: Count Set Bits
        System.out.println("Number of Set Bits in 5: " + countSetBits(5));
        System.out.println("Number of Set Bits in 15: " + countSetBits(15));

        // Problem 5: Odd Occurring Element
        int[] arr = {4, 3, 6, 2, 6, 4, 2, 3, 4, 3, 3};
        System.out.println("The odd occurring element is: " + findOddOccurring(arr));
    }

    public static String getBinary(int number) {
        return Integer.toBinaryString(number);
    }

    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static boolean isEven(int number) {
        return (number & 1) == 0;
    }

    public static int countSetBits(int number) {
        int count = 0;
        while (number > 0) {
            count += (number & 1);
            number >>= 1;
        }
        return count;
    }

    public static int findOddOccurring(int[] arr) {
        int result = 0;
        for (int num : arr) {
            result ^= num;
        }
        return result;
    }
}
