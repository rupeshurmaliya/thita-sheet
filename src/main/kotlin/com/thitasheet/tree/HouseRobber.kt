package com.thitasheet.tree


/**
 * Time Complexity: O(N)
 *
 * * We iterate through the nums array once in a single pass of N steps.
 *
 * Space Complexity: O(1)
 * * We only maintain two tracking variables (prev2 and prev1), using zero extra memory array allocations.
 *
 *
 * [198. House Robber](https://leetcode.com/problems/house-robber/description/)
 *
 * [YouTube Solution](https://www.youtube.com/watch?v=VXqUQYGMnQg&t=6s)
 */

class HouseRobber {

    fun rob(nums: IntArray): Int {

        var max = 0

        for (i in nums.lastIndex..2) {
            val skipNext = nums[i - 2]
            val skipNextToNext = nums[i - 3]

        }
    }
}