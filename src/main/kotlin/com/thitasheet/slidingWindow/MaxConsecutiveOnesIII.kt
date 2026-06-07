package com.thitasheet.slidingWindow


/**
 * * Time complexity: O(N) where N is the length of  nums. Both left and right pointers traverse the array at most once from left to right.
 *
 *
 * * Space complexity:  O(1) as we only use three integer variables ( maxLength, left, zeroCount ) regardless of array size.
 *
 * [1004. Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/?envType=problem-list-v2&envId=dogesdee)
 *
 * [YouTube Solution](https://www.youtube.com/watch?v=vLsRew-ABVs)
 */
class MaxConsecutiveOnesIII {

    fun longestOnes(nums: IntArray, k: Int): Int {
        var left = 0
        var zeroCount = 0
        var maxLength = 0

        for (right in nums.indices) {
            // If the element entering the window is a zero, increment our count
            if (nums[right] == 0) {
                zeroCount++
            }

            // Shrink the window from the left if the number of zeros exceeds k
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--
                }
                left++
            }

            // Calculate current window size and update the maximum length
            maxLength = maxOf(maxLength, right - left + 1)
        }
        return maxLength
    }

}