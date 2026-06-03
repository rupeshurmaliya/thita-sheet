package twoPointers.sortedArraysTargetSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Rupesh Urmaliya on 09/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/4sum/description/">Four sum</a>
 */


public class FourSum {

    private List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            // Skip duplicates for the first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Second loop: Fix the second element (j)
            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicates for the second element
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // Set up two pointers for the remaining two elements
                int left = j + 1;
                int right = n - 1;

                // Two Pointers on the sub-array
                while (left < right) {
                    long currentSum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (currentSum == target) {
                        // Found a valid quadruplet
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Move pointers and skip duplicates for the third and fourth elements
                        left++;
                        right--;

                        while (left < right && nums[left] == nums[left - 1]) {
                            left++; // Skip duplicates for 'left'
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--; // Skip duplicates for 'right'
                        }
                    } else if (currentSum < target) {
                        // Sum is too small, need a larger element
                        left++;
                    } else { // currentSum > target
                        // Sum is too large, need a smaller element
                        right--;
                    }
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        FourSum fourSum = new FourSum();
        List<List<Integer>> result = fourSum.fourSum(nums, 0);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
