package twoPointers;

import java.util.Arrays;

/**
 * Created by Rupesh Urmaliya on 09/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/">TwoSumII-InputArrayIsSorted</a>
 */


public class TwoSumIIInputArrayIsSorted {

    private int[] twoSum(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            int sum = numbers[start] + numbers[end];
            if (sum == target) {
                return new int[]{start + 1, end + 1};
            }
            else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }
        throw new RuntimeException("Numbers not present");
    }


    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        TwoSumIIInputArrayIsSorted twoSumIIInputArrayIsSorted = new TwoSumIIInputArrayIsSorted();
        int[] result = twoSumIIInputArrayIsSorted.twoSum(numbers, 9);
        System.out.println(Arrays.toString(result));
    }
}
