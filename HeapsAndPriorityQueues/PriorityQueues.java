package HeapsAndPriorityQueues;
import java.util.*;

public class PriorityQueues {

    // Q1. Rearrange the characters so that no two adjacent characters are the same
    public static String rearrangeString(String s) {
        if (s == null || s.length() == 0) return "";

        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );
        maxHeap.addAll(freqMap.entrySet());

        StringBuilder result = new StringBuilder();
        Map.Entry<Character, Integer> prev = null;

        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> current = maxHeap.poll();
            result.append(current.getKey());

            if (prev != null && prev.getValue() > 0) {
                maxHeap.offer(prev);
            }

            current.setValue(current.getValue() - 1);
            prev = current;
        }

        return result.length() == s.length() ? result.toString() : "";
    }

    // Q2. Find k pairs with the smallest sums
    public static List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return result;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0] + a[1]));

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                minHeap.offer(new int[]{nums1[i], nums2[j]});
            }
        }

        while (k-- > 0 && !minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }

        return result;
    }

    // Q3. Max score you can get by taking stones from three piles
    public static int maxScore(int a, int b, int c) {
        int score = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(a);
        maxHeap.add(b);
        maxHeap.add(c);

        while (maxHeap.size() > 1) {
            int first = maxHeap.poll();
            int second = maxHeap.poll();
            score++;

            if (first > 1) maxHeap.offer(first - 1);
            if (second > 1) maxHeap.offer(second - 1);
        }

        return score;
    }

    // Q4. Find the kth smallest array sum from a matrix
    public static int kthSmallest(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        int[] firstSum = new int[m];
        Arrays.fill(firstSum, 0);
        minHeap.offer(firstSum);

        while (k > 0) {
            int[] curr = minHeap.poll();
            k--;

            if (k == 0) {
                int sum = 0;
                for (int i = 0; i < m; i++) {
                    sum += mat[i][curr[i]];
                }
                return sum;
            }

            for (int i = m - 1; i >= 0; i--) {
                if (curr[i] + 1 < n) {
                    int[] next = curr.clone();
                    next[i]++;
                    minHeap.offer(next);
                }
            }
        }

        return -1;
    }

    // Q5. Find the median of the data stream
    private static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public static void addNum(int num) {
        if (maxHeap.size() == 0 || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public static double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        // Testing Q1
        String s = "aab";
        System.out.println(rearrangeString(s));  // Output: "aba"

        // Testing Q2
        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        int k = 3;
        List<int[]> pairs = kSmallestPairs(nums1, nums2, k);
        for (int[] pair : pairs) {
            System.out.println(Arrays.toString(pair));
        }

        // Testing Q3
        System.out.println(maxScore(2, 4, 6));  // Output: 6

        // Testing Q4
        int[][] mat = {{1,3,11},{2,4,6}};
        System.out.println(kthSmallest(mat, 5));  // Output: 7

        // Testing Q5
        addNum(1);
        System.out.println(findMedian());  // Output: 1
        addNum(2);
        System.out.println(findMedian());  // Output: 1.5
        addNum(3);
        System.out.println(findMedian());  // Output: 2
    }
}

