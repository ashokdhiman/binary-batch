package HashmapStackAndQueue;
import java.util.*;

public class Hashmap {

    public static void main(String[] args) {
        // Q1
        System.out.println("Q1:");
        Map<Integer, String> input1 = new HashMap<>();
        input1.put(5, "Rahul");
        input1.put(7, "Lakshman");
        input1.put(1, "Ram");
        input1.put(4, "Krrish");
        input1.put(2, "Lakshay");
        System.out.println(sortByKeys(input1));

        // Q2
        System.out.println("\nQ2:");
        Map<Integer, String> input2 = new HashMap<>(input1);
        System.out.println(sortByValues(input2));

        // Q3
        System.out.println("\nQ3:");
        int[] nums1 = {1, 2, 3, 4};
        int[] nums2 = {1, 2, 3, 4, 1};
        System.out.println(containsDuplicate(nums1)); // No
        System.out.println(containsDuplicate(nums2)); // Yes

        // Q4
        System.out.println("\nQ4:");
        int[] nums3 = {4, 2, 7, 1, 9};
        System.out.println(majorityElement(nums3));

        // Q5
        System.out.println("\nQ5:");
        String ransomNote1 = "a", magazine1 = "b";
        String ransomNote2 = "aa", magazine2 = "ab";
        System.out.println(canConstruct(ransomNote1, magazine1)); // false
        System.out.println(canConstruct(ransomNote2, magazine2)); // false
    }

    // Q1: Sort map by keys
    public static Map<Integer, String> sortByKeys(Map<Integer, String> map) {
        TreeMap<Integer, String> sorted = new TreeMap<>(map);
        return sorted;
    }

    // Q2: Sort map by values
    public static Map<String, Integer> sortByValues(Map<Integer, String> map) {
        List<Map.Entry<Integer, String>> list = new ArrayList<>(map.entrySet());
        list.sort(Comparator.comparing(Map.Entry::getValue));
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, String> entry : list) {
            sortedMap.put(entry.getValue(), entry.getKey());
        }
        return sortedMap;
    }

    // Q3: Check for duplicates
    public static String containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) return "Yes";
        }
        return "No";
    }

    // Q4: Find the majority element
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        return Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    // Q5: Check if ransomNote can be constructed from magazine
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];
        for (char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (count[c - 'a']-- <= 0) return false;
        }
        return true;
    }
}
