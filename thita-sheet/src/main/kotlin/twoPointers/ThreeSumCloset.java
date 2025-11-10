package twoPointers;

import java.util.Arrays;

/**
 * Created by Rupesh Urmaliya on 09/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/3sum-closest/description/">Three Sum Closet</a>
 */


public class ThreeSumCloset {

    private int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {

            //Remove the duplicate triplet
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int start = i + 1;
            int end = nums.length - 1;

            while (start < end) {

                int currentSum = nums[i] + nums[start] + nums[end];
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }

                if (currentSum == target) {
                    return currentSum;
                } else if (currentSum < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return closestSum;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        ThreeSumCloset threeSumCloset = new ThreeSumCloset();
        int closestSum = threeSumCloset.threeSumClosest(nums, 1);
        System.out.println("Closest sum " + closestSum);
    }
}
