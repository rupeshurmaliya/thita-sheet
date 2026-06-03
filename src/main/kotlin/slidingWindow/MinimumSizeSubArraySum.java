package slidingWindow;

/**
 * Created by Rupesh Urmaliya on 23/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/minimum-size-subarray-sum/description/">209. Minimum Size Subarray Sum</a>
 */


public class MinimumSizeSubArraySum {

    private int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int currentSum = 0;
        int minLength = Integer.MAX_VALUE;

        // The right pointer expands the window
        for (int right = 0; right < nums.length; right++) {
            // Expand the window by adding the element at the right pointer
            currentSum += nums[right];
            // Once the window's sum is valid, try to shrink it
            while (currentSum >= target) {
                // Update the minimum length found
                minLength = Math.min(minLength, right - left + 1);
                // Shrink the window from the left
                currentSum -= nums[left];
                left++;
            }
        }
        // If minLength was never updated, it means no valid subarray was found
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }


    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        MinimumSizeSubArraySum minimumSizeSubArraySum = new MinimumSizeSubArraySum();
        int subArrayLength = minimumSizeSubArraySum.minSubArrayLen(7, nums);
        System.out.println(subArrayLength);
    }
}
