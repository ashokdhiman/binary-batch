package GreedyAlgo;

import java.util.*;

public class Greedy {

    // Q1. Find the minimum sum of Products of two arrays of the same size, given that k modifications are allowed on the first array.
    public static int minProductSumAfterModification(int[] a, int[] b, int k) {
        int n = a.length;
        Arrays.sort(a); // Sort array a in ascending order
        Arrays.sort(b); // Sort array b in ascending order
        // Modify the smallest elements of a to minimize the product
        for (int i = 0; i < k; i++) {
            a[i] += 2; // Modify the smallest element of a by increasing it by 2
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i] * b[i]; // Calculate product sum
        }
        return sum;
    }

    // Q2. Select the maximum number of activities that can be performed by a single person
    public static List<Integer> maxActivities(int[] start, int[] finish) {
        int n = start.length;
        List<Integer> result = new ArrayList<>();
        int i = 0;
        result.add(i); // First activity is always selected
        for (int j = 1; j < n; j++) {
            if (start[j] >= finish[i]) { // If activity j starts after activity i finishes
                result.add(j);
                i = j; // Update the current activity to j
            }
        }
        return result;
    }

    // Q3. Gas station problem to determine the starting point for a circular route
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0, currentGas = 0, start = 0;
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            currentGas += gas[i] - cost[i];
            if (currentGas < 0) {
                start = i + 1;
                currentGas = 0;
            }
        }
        return totalGas < totalCost ? -1 : start;
    }

    // Q4. Flowerbed problem: can we plant n new flowers without violating the no-adjacent-flowers rule?
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                count++;
            }
        }
        return count >= n;
    }

    // Q5. Minimum number of intervals to remove to make the rest non-overlapping
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1])); // Sort by end time
        int count = 0, lastEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < lastEnd) { // If the current interval overlaps
                count++;
            } else {
                lastEnd = intervals[i][1]; // Update the end time of the last non-overlapping interval
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // Test Q1
        int[] a = {2, 3, 4, 5, 4};
        int[] b = {3, 4, 2, 3, 2};
        int k = 3;
        System.out.println("Minimum Product Sum: " + minProductSumAfterModification(a, b, k));

        // Test Q2
        int[] start = {10, 12, 20};
        int[] finish = {20, 25, 30};
        System.out.println("Maximum Activities: " + maxActivities(start, finish));

        // Test Q3
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println("Can complete circuit from station: " + canCompleteCircuit(gas, cost));

        // Test Q4
        int[] flowerbed = {1, 0, 0, 0, 1};
        int n = 1;
        System.out.println("Can place flowers: " + canPlaceFlowers(flowerbed, n));

        // Test Q5
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println("Minimum intervals to remove: " + eraseOverlapIntervals(intervals));
    }
}

