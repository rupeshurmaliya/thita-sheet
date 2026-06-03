package twoPointers.sortedArraysTargetSum;

import java.util.Arrays;

/**
 * Created by Rupesh Urmaliya on 10/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/squares-of-a-sorted-array/description/">977. Squares of a Sorted Array</a>
 */


public class SquaresOfSortedArray {

    // Time complexity: O(N log N)
    //Space: O(N)
    private int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int index = 0;
        for (int num : nums) {
            result[index++] = Math.abs(num * num);
        }
        Arrays.sort(result);
        return result;
    }

    //Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?
    private int[] sortedSquaresOptimal(int[] nums) {
        int[] result = new int[nums.length];
        int start = 0;
        int end = nums.length - 1;
        int index = nums.length - 1;
        while (start <= end) {
            int startTemp = Math.abs(nums[start] * nums[start]);
            int endTemp = Math.abs(nums[end] * nums[end]);
            if (startTemp > endTemp) {
                result[index] = startTemp;
                start++;
            } else {
                result[index] = endTemp;
                end--;
            }
            index--;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 3, 10};
        SquaresOfSortedArray squaresOfSortedArray = new SquaresOfSortedArray();
        int[] result = squaresOfSortedArray.sortedSquaresOptimal(nums);
        System.out.println(Arrays.toString(result));
    }
}
