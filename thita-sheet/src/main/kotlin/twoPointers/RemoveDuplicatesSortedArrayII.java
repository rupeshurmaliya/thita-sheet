package twoPointers;

import java.util.Arrays;

/**
 * Created by Rupesh Urmaliya on 16/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/">80. Remove Duplicates from Sorted Array II</a>
 * <p>
 * Note: Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 */


public class RemoveDuplicatesSortedArrayII {


    /**
     * Removes duplicates from a sorted array such that each element appears at most twice.
     * The modification is done in-place, and the function returns the new length.
     * <p>
     * Time Complexity: O(N) - Single pass over the array.
     * <p>
     * Space Complexity: O(1) - In-place modification.
     * <p>
     *
     * @param nums The input array, sorted in non-decreasing order.
     * @return The new length of the array after modification.
     * <p>
     * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
     */
    private int removeDuplicates(int[] nums) {
        // Edge case: If the array has 2 or fewer elements, no duplicates can exceed the limit.
        if (nums.length <= 2) {
            return nums.length;
        }

        // 'k' (or 'i' in some solutions) is the **Write Pointer**.
        // It tracks the index where the next valid element should be placed.
        // We initialize it to 2, because the first two elements (indices 0 and 1)
        // are always valid to keep in an array of size >= 2.
        int k = 2;

        // 'i' (or 'j' in some solutions) is the **Read Pointer**.
        // It iterates through the array starting from the third element (index 2).
        for (int i = 2; i < nums.length; i++) {

            // The core check:
            // We compare the current element (nums[i]) with the element two positions
            // behind the write pointer (nums[k - 2]).

            // If nums[i] is DIFFERENT from nums[k - 2], it means:
            // 1. If nums[k-1] was the same as nums[k-2], nums[i] creates a new, valid triplet.
            // 2. If nums[k-1] was different from nums[k-2], nums[i] is just a new, valid value.
            // In either case, adding nums[i] maintains the "at most twice" rule.
            if (nums[i] != nums[k - 2]) {
                // Write the unique element to the position 'k'
                nums[k] = nums[i];

                // Move the write pointer forward
                k++;
            }

            // If nums[i] == nums[k - 2], it means nums[i] is the third (or fourth, etc.)
            // occurrence of the element currently occupying nums[k-2] and nums[k-1].
            // We skip this element and let the read pointer (i) advance.
        }

        // 'k' now represents the new length of the array.
        return k;

    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        RemoveDuplicatesSortedArrayII removeDuplicatesSortedArrayII = new RemoveDuplicatesSortedArrayII();
        int elements = removeDuplicatesSortedArrayII.removeDuplicates(nums);
        System.out.println("Elements: " + elements);
        System.out.println(Arrays.toString(nums));
    }
}
