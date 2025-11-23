package twoPointers;

import java.util.Arrays;

/**
 * Created by Rupesh Urmaliya on 22/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/sort-array-by-parity/description/">905. Sort Array By Parity</a>
 */


public class SortArrayByParity {

    private int[] sortArrayByParity(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            if (isEven(nums[start])) {
                start++;
            } else if (!isEven(nums[end])) {
                end--;
            } else {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
        return nums;
    }

    private boolean isEven(int num) {
        return num % 2 == 0;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 4};
        SortArrayByParity sortArrayByParity = new SortArrayByParity();
        sortArrayByParity.sortArrayByParity(nums);
        System.out.println(Arrays.toString(nums));
    }
}
