package slidingWindow;

/**
 * Created by Rupesh Urmaliya on 23/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/maximum-average-subarray-i/description/">643. Maximum Average Subarray I</a>
 */


public class MaximumAverageSubArrayI {

    private double findMaxAverage(int[] nums, int k) {
        int currentSum = 0;
        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }
        maxSum = currentSum;
        for (int i = k; i < nums.length; i++) {
            currentSum += nums[i] - nums[i - k];
            maxSum = Math.max(currentSum, maxSum);
        }
        return (double) maxSum / k;
    }

    public static void main(String[] args) {
        int[] nums = {1, 12, -5, -6, 50, 3};
        MaximumAverageSubArrayI maximumAverageSubArrayI = new MaximumAverageSubArrayI();
        double result = maximumAverageSubArrayI.findMaxAverage(nums, 4);
        System.out.println(result);
    }
}
