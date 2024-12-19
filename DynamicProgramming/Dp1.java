package DynamicProgramming;
public class Dp1 {

    // Q1: Count the number of ways to reach the top with m steps at a time
    public static int countWays(int n, int m) {
        int[] dp = new int[n + 1];
        dp[0] = 1; // Base case: 1 way to stay at the bottom (do nothing)

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m && i - j >= 0; j++) {
                dp[i] += dp[i - j];
            }
        }

        return dp[n];
    }

    // Q2: Tribonacci sequence
    public static int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }

    // Q3: Rob houses (no two adjacent houses can be robbed)
    public static int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    // Q4: Unique paths in a grid
    public static int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }

        return dp[n - 1];
    }

    // Q5: Minimum path sum in a triangle
    public static int minimumTotal(int[][] triangle) {
        int n = triangle.length;
        int[] dp = new int[n];

        // Start from the last row
        for (int i = 0; i < n; i++) {
            dp[i] = triangle[n - 1][i];
        }

        // Move upwards and calculate the minimum sum for each element
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                dp[col] = triangle[row][col] + Math.min(dp[col], dp[col + 1]);
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        // Q1: Ways to reach the top (n = 5, m = 3)
        System.out.println("Ways to reach the top: " + countWays(5, 3)); // Output: 7

        // Q2: Tribonacci number (n = 4)
        System.out.println("Tribonacci(4): " + tribonacci(4)); // Output: 4

        // Q3: Robbing houses (nums = [1, 2, 3, 1])
        System.out.println("Max amount robbing houses: " + rob(new int[]{1, 2, 3, 1})); // Output: 4

        // Q4: Unique paths in grid (m = 3, n = 7)
        System.out.println("Unique paths in grid: " + uniquePaths(3, 7)); // Output: 28

        // Q5: Minimum path sum in triangle
        int[][] triangle = {
                {2},
                {3, 4},
                {6, 5, 7},
                {4, 1, 8, 3}
        };
        System.out.println("Minimum path sum in triangle: " + minimumTotal(triangle)); // Output: 11
    }
}

