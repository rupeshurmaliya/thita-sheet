package com.thitasheet.slidingWindow


/**
 *  Time Complexity:  O(N log N)  where  N  is the length of  nums . Sorting the array takes  O(N log N) . The sliding window traversal takes  O(N)  since both  left  and  right
 *   pointers move from left to right at most once.
 *
 *
 * Space Complexity:  O(1)  (or  O(N)  depending on the sorting implementation's recursion stack). We only store a few pointer and sum variables.
 *
 * [1838. Frequency of the Most Frequent Element](https://leetcode.com/problems/frequency-of-the-most-frequent-element/description/)
 *
 * [YouTube Explanation](https://www.youtube.com/watch?v=vgBrQ0NM5vE)
 */
class FrequencyOfTheMostFrequentElement {

    fun maxFrequency(nums: IntArray, k: Int): Int {
        // Sort array to group closest numbers together
        nums.sort()

        var left = 0
        var maxFreq = 0
        var windowSum = 0L // Use Long to prevent integer overflow

        for (right in nums.indices) {
            val target = nums[right]
            windowSum += target

            // Cost to make all elements in nums[left..right] equal to target:
            // (windowSize * target) - windowSum
            // If this cost exceeds k, shrink the window from the left
            while ((right - left + 1).toLong() * target - windowSum > k) {
                windowSum -= nums[left]
                left++
            }

            // Update the maximum frequency (window size)
            maxFreq = maxOf(maxFreq, right - left + 1)
        }

        return maxFreq
    }
}