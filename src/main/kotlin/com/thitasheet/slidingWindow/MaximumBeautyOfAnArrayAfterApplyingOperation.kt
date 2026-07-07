package com.thitasheet.slidingWindow


/**
 * Time complexity: O(N log N)
 *
 * Space complexity:  O(1)
 *
 * [2779. Maximum Beauty of an Array After Applying Operation](https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/description/)
 *
 * [YouTube solution](https://www.youtube.com/watch?v=zp4QVvmPDI4)
 */
class MaximumBeautyOfAnArrayAfterApplyingOperation {

    fun maximumBeauty(nums: IntArray, k: Int): Int {
        // Sort the array to group close elements together
        nums.sort()

        var left = 0
        var maxBeauty = 0

        // Expand the right boundary of the window
        for (right in nums.indices) {
            // Shrink the window from the left if the range exceeds 2 * k
            while (nums[right] - nums[left] > 2 * k) {
                left++
            }
            // Update the maximum beauty (size of the valid window)
            maxBeauty = maxOf(maxBeauty, right - left + 1)
        }
        return maxBeauty
    }

    fun maximumBeautyBruteForce(nums: IntArray, k: Int): Int {
        // Sort the array to group close elements together
        nums.sort()

        var maxBeauty = 0

        // Check every starting position
        for (i in nums.indices) {
            var j = i
            // Expand the sub-array as long as the difference is within 2 * k
            while (j < nums.size && nums[j] - nums[i] <= 2 * k) {
                j++
            }
            // Update the maximum length found
            maxBeauty = maxOf(maxBeauty, j - i)
        }
        return maxBeauty
    }
}