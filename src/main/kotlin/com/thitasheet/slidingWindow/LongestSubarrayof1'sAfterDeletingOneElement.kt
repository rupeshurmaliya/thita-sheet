package com.thitasheet.slidingWindow


/**
 * Time complexity:  O(N)  where  N  is the length of  nums . Both pointers ( left  and  right ) traverse the array from left to right at most once.
 *
 *
 * Space complexity:  O(1)  since we only store three simple integer counters.
 *
 * [1493. Longest Subarray of 1's After Deleting One Element](https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/?envType=problem-list-v2&envId=dogesdee)
 *
 * [YouTube solution](https://www.youtube.com/watch?v=RCTP3DIfSSg)
 */
class `LongestSubarrayof1'sAfterDeletingOneElement` {

    fun longestSubarray(nums: IntArray): Int {
        var left = 0
        var zeroCount = 0
        var maxLength = 0

        for (right in nums.indices) {

            // Increment zeroCount if the element entering the window is a zero
            if (nums[right] == 0) {
                zeroCount++
            }

            // Shrink the window from the left if the zero count exceeds 1
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--
                }
                left++
            }
            // Keep track of the maximum window size
            maxLength = maxOf(maxLength, right - left + 1)
        }
        // We must delete exactly one element
        return maxLength - 1
    }

}
