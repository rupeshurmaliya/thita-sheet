package com.thitasheet.slidingWindow


/**
 *  Time Complexity:  O(N)
 *
 * * Each element's index is added to  maxDeque  and  minDeque  at most once.
 * * Each element's index is removed from the front/back of the deques at most once.
 * * The inner loop increments  left  at most  N  times across the entire algorithm.
 * * Hence, the amortized time complexity is linear,  O(N) .
 *
 *
 *  Space Complexity:  O(N)
 *
 * * In the worst case, the deques can store up to  N  indices (e.g., when the array is strictly increasing/decreasing).
 *
 * [2762. Continuous Subarrays](https://leetcode.com/problems/continuous-subarrays/description/)
 */
class ContinuousSubarrays {

    fun continuousSubarrays(nums: IntArray): Long {
        var totalSubarrays = 0L
        var left = 0

        // Deques to maintain indices of max and min elements in the window
        val minDeque = ArrayDeque<Int>()
        val maxDeque = ArrayDeque<Int>()

        for (right in nums.indices) {
            val num = nums[right]

            // Maintain maxDeque in decreasing order of values
            while (!maxDeque.isEmpty() && nums[maxDeque.last()] <= num) {
                maxDeque.removeLast()
            }
            maxDeque.addLast(right)

            // Maintain minDeque in increasing order of values
            while (!minDeque.isEmpty() && nums[minDeque.last()] >= num) {
                minDeque.removeLast()
            }
            minDeque.addLast(right)

            // If the max and min difference in the window is > 2, shrink window
            while (nums[maxDeque.first()] - nums[minDeque.first()] > 2) {

                if (maxDeque.first() == left) {
                    maxDeque.removeFirst()
                }
                if (minDeque.first() == left) {
                    minDeque.removeFirst()
                }
                left++
            }

            // All subarrays ending at 'right' starting from 'left' to 'right' are valid
            totalSubarrays += right - left + 1
        }
        return totalSubarrays
    }

    fun continuousSubarraysBruteForce(nums: IntArray): Long {
        var count = 0L
        for (i in 0 until nums.size) {
            var minVal = nums[i]
            var maxVal = nums[i]
            for (j in i until nums.size) {
                minVal = minOf(minVal, nums[j])
                maxVal = maxOf(maxVal, nums[j])

                if (maxVal - minVal <= 2) count++
                else break
            }
        }
        return count
    }


}
