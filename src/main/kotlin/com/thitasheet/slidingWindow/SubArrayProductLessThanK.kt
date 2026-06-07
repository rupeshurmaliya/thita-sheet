package com.thitasheet.slidingWindow


/**
 * Time complexity:  O(N)  where  N  is the length of  nums . Even though we have a nested  while  loop, both the  left  and  right  pointers move only from left to right. Each
 *   element is added to the product at most once, and removed at most once.
 *
 *
 * Space complexity: O(1)  as we only store simple scalar variables ( left ,  right ,  currentProduct ,  totalCount ).
 *
 * [713. Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/description/?envType=problem-list-v2&envId=dogesdee)
 *
 * [YouTube solution](https://www.youtube.com/watch?v=-eEZskncDLc)
 */
class SubArrayProductLessThanK {

    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
        // Edge Case: Since nums[i] >= 1, the minimum product is 1.
        // If k is 0 or 1, no subarray product can be strictly less than k.
        if (k <= 1) {
            return 0
        }

        var totalCount = 0
        var currentProduct = 1
        var left = 0

        // Expand the window by moving the right pointer
        for (right in nums.indices) {
            currentProduct *= nums[right]

            // Shrink the window from the left if the product is too large
            while (currentProduct >= k && left <= right) {
                currentProduct /= nums[left]
                left++
            }

            // Add the number of valid subarrays ending at index 'right'
            totalCount += (right - left + 1)
        }
        return totalCount
    }

}