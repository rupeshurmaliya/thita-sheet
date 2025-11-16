package twoPointers.sortedArraysTargetSum;

import java.util.Arrays;

/**
 * Created by Rupesh Urmaliya on 10/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/3sum-smaller/description/">259. 3Sum Smaller</a>
 * <p>
 * <p>
 * Counts the number of triplets (i, j, k) such that i < j < k and
 * nums[i] + nums[j] + nums[k] < target.
 * <p>
 * Time Complexity: O(N^2) (dominated by nested loop after O(N log N) sort).
 * <p>
 * Space Complexity: O(1) (excluding space used by sorting, which can be O(log N) or O(N)).
 */


public class ThreeSumSmaller {

    private int threeSumSmaller(int[] nums, int target) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum < target) {
                    /* * CRITICAL INSIGHT:
                     * If the sum of nums[left] and nums[right] is less than newTarget,
                     * then any element k between 'left' and 'right' (left < k < right)
                     * will also satisfy the condition: nums[left] + nums[k] < newTarget.
                     * * Therefore, all pairs (nums[left], nums[k]) for k from 'left + 1' to 'right'
                     * form a valid triplet with the fixed nums[i].
                     * The number of such elements is (right - left).
                     */
                    count += end - start;
                    start++;
                } else {
                    end--;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        int[] nums = {-2, 0, 1, 3};
        ThreeSumSmaller threeSumSmaller = new ThreeSumSmaller();
        int number = threeSumSmaller.threeSumSmaller(nums, 2);
        System.out.println("Number of the triplets: " + number);
    }

}
