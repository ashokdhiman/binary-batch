package HeapsAndPriorityQueues;
import java.util.*;

public class Heaps {

    // Q1. Find kth largest element using a priority queue (min-heap)
    public static int findKthLargest(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    // Q2. Connect ropes with minimum cost
    public static int connectRopes(int[] ropes) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int rope : ropes) {
            minHeap.offer(rope);
        }

        int totalCost = 0;
        while (minHeap.size() > 1) {
            int first = minHeap.poll();
            int second = minHeap.poll();
            int cost = first + second;
            totalCost += cost;
            minHeap.offer(cost);
        }

        return totalCost;
    }

    // Q3. Return the k most frequent strings from words
    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> maxHeap = new PriorityQueue<>((a, b) -> {
            int freqComparison = Integer.compare(frequencyMap.get(b), frequencyMap.get(a));
            if (freqComparison != 0) {
                return freqComparison;
            }
            return a.compareTo(b); // Lexicographical order
        });

        for (String word : frequencyMap.keySet()) {
            maxHeap.offer(word);
        }

        List<String> result = new ArrayList<>();
        while (k-- > 0) {
            result.add(maxHeap.poll());
        }

        return result;
    }

    // Q4. Game with stones, find the weight of the last remaining stone
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        while (maxHeap.size() > 1) {
            int first = maxHeap.poll();
            int second = maxHeap.poll();

            if (first != second) {
                maxHeap.offer(first - second);
            }
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    public static void main(String[] args) {
        // Test Q1: Find kth largest element
        int[] arr1 = {1, 2, 3, 5, 2, 6, 9};
        int k1 = 3;
        System.out.println("The " + k1 + "rd largest element is: " + findKthLargest(arr1, k1));

        // Test Q2: Connect ropes with minimum cost
        int[] ropes = {5, 4, 2, 8};
        System.out.println("The minimum cost to connect ropes is: " + connectRopes(ropes));

        // Test Q3: Top K frequent words
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k3 = 2;
        System.out.println("Top " + k3 + " frequent words: " + topKFrequent(words, k3));

        // Test Q4: Last stone weight
        int[] stones = {2, 7, 4, 1, 8, 1};
        System.out.println("The last stone weight is: " + lastStoneWeight(stones));
    }
}
