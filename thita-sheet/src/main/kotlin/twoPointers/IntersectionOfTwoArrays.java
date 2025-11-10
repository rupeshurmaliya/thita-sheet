package twoPointers;

import java.util.*;

/**
 * Created by Rupesh Urmaliya on 09/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/intersection-of-two-arrays/description/">349. Intersection of Two Arrays</a>
 */


public class IntersectionOfTwoArrays {


    //Time: O(n*m)
    private int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList<>();

        for (int j : nums1) {
            for (int k : nums2) {
                if (j == k && !result.contains(j)) {
                    result.add(j);
                }
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    //https://www.youtube.com/watch?v=He9kBIZJd40
    private int[] intersectionMap(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, i);
        }
        for (int j : nums2) {
            if (map.containsKey(j) && map.get(j) == 1) {
                map.put(j, 0);
                result.add(j);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    //Time Complexity: O(n + m)
    // Space:  O(n + m)
    private int[] intersectionSet(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        // 2. Use a second set to store the intersection elements.
        // This automatically handles the "unique elements only" requirement.
        Set<Integer> intersection = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                intersection.add(num);
            }
        }

        // 3. Convert the resulting HashSet back to an array.
        int[] result = new int[intersection.size()];
        int index = 0;

        for (int num : intersection) {
            result[index++] = num;
        }
        return result;
    }

    /**
     * Solution for LeetCode 349: Intersection of Two Arrays.
     * This approach uses sorting and the Two Pointers technique,
     * leveraging a HashSet only for ensuring the uniqueness of the result.
     * <p>
     * Time Complexity: O(N log N + M log M) due to sorting, where N and M are the
     * lengths of nums1 and nums2, respectively.
     * <p>
     * Space Complexity: O(N + M) in the worst case, used for the HashSet and result array.
     */
    private int[] intersectionTwoPointer(int[] nums1, int[] nums2) {
        // Step 1: Sort both arrays. This is the prerequisite for the Two Pointers approach.
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0; // Pointer for nums1
        int j = 0; // Pointer for nums2

        // Use a HashSet to store the intersection elements. This is mandatory
        // because the problem requires that "Each element in the result must be unique."
        Set<Integer> uniqueIntersection = new HashSet<>();

        // Step 2: Traverse both sorted arrays simultaneously using two pointers.
        while (i < nums1.length && j < nums2.length) {
            int val1 = nums1[i];
            int val2 = nums2[j];

            if (val1 == val2) {
                // Case 1: Elements match - we found an intersection.
                // Add the element to the set (HashSet automatically handles uniqueness).
                uniqueIntersection.add(val1);
                i++;
                j++;
            } else if (val1 < val2) {
                // Case 2: Element in nums1 is smaller.
                // Increment i to look for a larger value that might match val2.
                i++;
            } else { // val1 > val2
                // Case 3: Element in nums2 is smaller.
                // Increment j to look for a larger value that might match val1.
                j++;
            }
        }

        // Step 3: Convert the resulting Set back into an array.
        int[] result = new int[uniqueIntersection.size()];
        int index = 0;

        for (int num : uniqueIntersection) {
            result[index++] = num;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] num1 = {1, 2, 2, 1};
        int[] num2 = {2, 2};
        IntersectionOfTwoArrays intersection = new IntersectionOfTwoArrays();
        int[] result = intersection.intersection(num1, num2);
        System.out.println(Arrays.toString(result));
    }
}
