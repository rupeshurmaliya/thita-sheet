package com.thitasheet.slidingWindow


/**
 * Time complexity: O(n)
 *
 *
 * Space complexity: O(k) where K is the size of the window to remove the duplicate elements from HashSet in worst case
 *
 * [2461. Maximum Sum of Distinct Subarrays With Length K](https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/description/)
 *
 * [YouTube Slightly different but to understand](https://www.youtube.com/watch?v=pT-lOE1on3M)
 */
class MaximumSumOfDistinctSubarraysWithLengthK {

    fun maximumSubarraySum(nums: IntArray, k: Int): Long {
        var left = 0
        var maxSubarraySum = 0L
        val hashSet = mutableSetOf<Int>()
        var currentSum = 0L

        for (right in nums.indices) {
            val num = nums[right]

            while (hashSet.contains(num)) {
                currentSum -= nums[left]
                hashSet.remove(nums[left])
                left++
            }

            currentSum += nums[right]
            hashSet.add(num)

            if (right - left + 1 == k) {
                // if (hashSet.size == k) Both can be used, whatever makes more intuitive or readable.
                maxSubarraySum = maxOf(maxSubarraySum, currentSum)
                currentSum -= nums[left]
                hashSet.remove(nums[left])
                left++
            }
        }

        return maxSubarraySum
    }
}
