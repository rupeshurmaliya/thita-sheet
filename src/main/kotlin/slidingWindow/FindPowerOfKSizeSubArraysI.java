package slidingWindow;

import java.util.Arrays;

/**
 * Created by Rupesh Urmaliya on 23/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/calculate-compressed-mean/description/">3254. Find the Power of K-Size Subarrays I</a>
 */


public class FindPowerOfKSizeSubArraysI {

    //https://www.youtube.com/watch?v=EPALVnSCdnA
    private int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        if (n < k) {
            return new int[0];
        }
        int[] result = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            boolean isConsecutive = true;
            for (int j = i; j < i + k - 1; j++) {
                if (nums[j + 1] != nums[j] + 1) { //Consecutive ascending means always +1 from previous element
                    isConsecutive = false;
                    break;
                }
            }
            if (isConsecutive) {
                result[i] = nums[i + k - 1];
            } else {
                result[i] = -1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 3, 2, 5};
        FindPowerOfKSizeSubArraysI findPowerOfKSizeSubArraysI = new FindPowerOfKSizeSubArraysI();
        int[] result = findPowerOfKSizeSubArraysI.resultsArray(nums, 3);
        System.out.println(Arrays.toString(result));
    }
}
