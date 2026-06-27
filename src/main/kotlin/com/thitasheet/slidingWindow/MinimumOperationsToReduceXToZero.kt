package com.thitasheet.slidingWindow


/**
 * Time complexity: O(N)
 *
 * Space complexity: O(1)
 *
 * [1658. Minimum Operations to Reduce X to Zero](https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/?envType=problem-list-v2&envId=dogesdee)
 *
 * [YouTube Explanation](https://www.youtube.com/watch?v=xumn16n7njs)
 */
class MinimumOperationsToReduceXToZero {

    fun minOperations(nums: IntArray, x: Int): Int {
        val totalSum = nums.sum()
        val target = totalSum - x

        // If target is negative, it's impossible to sum to x (since nums[i] >= 1)
        if (target < 0) {
            return -1
        }

        // If target is 0, we must remove all elements to reach x
        if (target == 0) {
            return nums.size
        }

        var maxLen = -1
        var currentSum = 0
        var left = 0

        // Sliding window to find the longest contiguous subarray summing to target
        for (right in nums.indices) {
            currentSum += nums[right]

            // Shrink the window from the left if the sum exceeds target
            while (currentSum > target && left <= right) {
                currentSum -= nums[left]
                left++
            }

            // If the window sum matches target, record the maximum length
            if (currentSum == target) {
                maxLen = maxOf(maxLen, right - left + 1)
            }
        }

        // If a valid subarray was found, subtract its length from total elements
        return if (maxLen != -1) nums.size - maxLen else -1
    }
}