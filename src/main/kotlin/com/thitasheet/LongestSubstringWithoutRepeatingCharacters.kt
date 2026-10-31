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
        var left = 0
        val lastSeenIndex = mutableMapOf<Char, Int>()

        for (right in s.indices) {
            val currentChar = s[right]
            val previousIndex = lastSeenIndex[currentChar]
            if (previousIndex != null) {
                left = maxOf(previousIndex + 1, left)
            }
            lastSeenIndex[currentChar] = right
            maxLength = maxOf(maxLength, right - left + 1)
        }
        return maxLength
    }

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