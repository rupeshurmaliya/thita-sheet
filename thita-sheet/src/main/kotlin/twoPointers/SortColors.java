package twoPointers;

import java.util.Arrays;

/**
 * Created by Rupesh Urmaliya on 16/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/sort-colors/">75. Sort Colors</a>
 */


public class SortColors {

    //https://www.youtube.com/watch?v=6sMssUHgaBs
    private void sortColors(int[] nums) {
        int start = 0;
        int middle = 0;
        int end = nums.length - 1;

        while (middle <= end) {
            if (nums[middle] == 0) {
                swap(nums, middle, start);
                start++;
                middle++;
            } else if (nums[middle] == 2) {
                swap(nums, middle, end);
                end--;
            } else {
                middle++;
            }
        }
    }

    private void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        SortColors sortColors = new SortColors();
        sortColors.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
