package com.thitasheet.slidingWindow


/**
 * • Time Complexity:  O(N)  where  N  is the length of  nums . Each element is added to and removed from each deque at most once, resulting in an amortized  O(1)  work per array
 * index.
 *
 * • Space Complexity:  O(N)  in the worst case to store the indices of the elements in the deques.
 *
 * [1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit](https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/description/?envType=problem-list-v2&envId=dogesdee)
 *
 * [YouTube Solution](https://www.youtube.com/watch?v=V-ecDfY5xEw&t=441s)
 */
class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    fun longestSubarray(nums: IntArray, limit: Int): Int {
        val maxDeque = ArrayDeque<Int>() // Stores indices of elements in decreasing order
        val minDeque = ArrayDeque<Int>() // Stores indices of elements in increasing order
        var left = 0
        var maxLength = 0

        for (right in nums.indices) {
            val num = nums[right]

            // Maintain decreasing order in maxDeque
            while (maxDeque.isNotEmpty() && nums[maxDeque.last()] <= num) {
                maxDeque.removeLast()
            }
            maxDeque.addLast(right)

            // Maintain increasing order in minDeque
            while (minDeque.isNotEmpty() && nums[minDeque.last()] >= num) {
                minDeque.removeLast()
            }
            minDeque.addLast(right)

            // If the absolute difference between max and min exceeds limit, shrink window
            while (nums[maxDeque.first()] - nums[minDeque.first()] > limit) {
                left++
                // Remove elements that are no longer within the sliding window
                if (maxDeque.first() < left) {
                    maxDeque.removeFirst()
                }
                if (minDeque.first() < left) {
                    minDeque.removeFirst()
                }
            }

            // Track maximum valid subarray length found so far
            maxLength = maxOf(maxLength, right - left + 1)
        }

        return maxLength
    }
}