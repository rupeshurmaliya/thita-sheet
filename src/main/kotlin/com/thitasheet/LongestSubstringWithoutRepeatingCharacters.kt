package com.thitasheet


/**
 *      Time complexity: O(N)
 *   where  N  is the length of the string  s . We perform a single pass over the string using the  right  pointer. Inside the loop, map insertions,
 *   lookups, and updating the pointer are all  O(1)  operations.
 *
 *
 *      Space complexity: O(min(N, K))
 *
 *   where  N  is the length of the string and  K  is the size of the alphabet/character set. In the worst case of a string containing all unique
 *   characters, the map will store  N  items. However, if the alphabet is bounded (e.g., standard ASCII where  K = 128 , or extended ASCII where  K = 256 ), the space complexity
 *   simplifies to  O(1) .
 *
 * [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/description/?envType=problem-list-v2&envId=dma3hpu1)
 */

class LongestSubstringWithoutRepeatingCharacters {

    fun lengthSubstring(s: String): Int {
        var maxLength = 0
        var windowStart = 0
        // Maps each character to its most recently seen index
        val lastSeenIndices = mutableMapOf<Char, Int>()

        for (windowEnd in s.indices) {
            val currentChar = s[windowEnd]

            // If currentChar was already seen, move windowStart past its previous occurrence
            if (lastSeenIndices.containsKey(currentChar)) {
                val previousIndex = lastSeenIndices[currentChar]!!
                windowStart = maxOf(windowStart, previousIndex + 1)
            }

            // Record or update the most recent index of currentChar
            lastSeenIndices[currentChar] = windowEnd

            // Calculate the length of the current valid window and update maxLength
            val currentLength = windowEnd - windowStart + 1
            maxLength = maxOf(maxLength, currentLength)
        }

        return maxLength
    }

    /**
     *  If the character set consists only of standard ASCII characters (values 0 to 127), an IntArray(128) can replace the hash map for cache locality and higher execution speed.
     */
    fun lengthOfLongestSubstring(s: String): Int {
        var maxLength = 0
        var left = 0
        // Direct-access table for ASCII characters, initialized to -1 (unseen)
        val lastSeen = IntArray(128) { -1 }

        for (right in s.indices) {
            val charCode = s[right].code

            // If the character was seen inside the current window, advance left
            if (lastSeen[charCode] >= left) {
                left = lastSeen[charCode] + 1
            }

            // Record latest index and compute window size
            lastSeen[charCode] = right
            maxLength = maxOf(maxLength, right - left + 1)
        }

        return maxLength
    }
}