package com.thitasheet.greedy

/**
 * Time complexity: O(N) where N is the length of the nums array.
 * Space complexity: O(1) as we only use a few variables for tracking indices and jump bounds.
 *
 * [45. Jump Game II](https://leetcode.com/problems/jump-game-ii/description/)
 */
class JumpGameII {

    fun jump(nums: IntArray): Int {
        // If the array has only 1 element, we are already at the destination.
        if (nums.size <= 1) {
            return 0
        }

        var jumps = 0
        var currentEnd = 0
        var maxReach = 0

        // Iterate through the array, except for the last element.
        // We don't need to jump from the last element.
        for (i in 0 until nums.size - 1) {
            // Update the furthest index we can reach from the current position.
            maxReach = maxOf(maxReach, i + nums[i])

            // If we have reached the boundary of the current jump range,
            // we must make another jump.
            if (i == currentEnd) {
                jumps++
                currentEnd = maxReach

                // If our maxReach can already get us to or past the last index,
                // we can stop early.
                if (currentEnd >= nums.size - 1) {
                    break
                }
            }
        }

        return jumps
    }
}
