package com.thitasheet.slidingWindow


/**
 *  ```
 *  Time Complexity: O(n)
 *  ```
 *
 *  We traverse the string with the  right  pointer exactly once. The  left  pointer also moves at most  n  times in total across the entire run of the
 *  algorithm. Therefore, the inner  while  loop runs at most  n  times overall, resulting in linear time complexity.
 *
 *
 *  ```
 *  Space Complexity: O(1)
 *  ```
 * We only use static arrays of size 3 ( totalCount  and  windowCount ) and a few integer variables, which consumes constant auxiliary space
 *
 * [2516. Take K of Each Character From Left and Right](https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/description/)
 *
 * [YouTube explanation](https://www.youtube.com/watch?v=QzcxeJZByNM)
 */
class TakeKOfEachCharacterFromLeftAndRight {

    fun takeCharacters(s: String, k: Int): Int {
        val totalCount = IntArray(3)

        // Step 1: Count total occurrences of each character in s
        for (char in s) {
            totalCount[char - 'a']++
        }

        // Step 2: If we don't have at least k of each character overall, it's impossible
        if (totalCount[0] < k || totalCount[1] < k || totalCount[2] < k)
            return -1

        // Step 3: Calculate the maximum allowed characters in the middle window
        val maxA = totalCount[0] - k
        val maxB = totalCount[1] - k
        val maxC = totalCount[2] - k

        val windowsCount = IntArray(3)
        var maxWindowLength = 0
        var left = 0

        // Step 4: Expand the window with the right pointer
        for (right in s.indices) {
            windowsCount[s[right] - 'a']++

            // Step 4.1 Shrink the window from left if we exceed the allowed counts
            while (windowsCount[0] > maxA || windowsCount[1] > maxB || windowsCount[2] > maxC) {
                windowsCount[s[left] - 'a']--
                left++
            }

            // Step 4.2 Update the maximum window length found so far
            maxWindowLength = maxOf(maxWindowLength, right - left + 1)

        }
        // Step 5: The minimum characters to take is total length minus maximum window length

        return s.length - maxWindowLength

    }


}