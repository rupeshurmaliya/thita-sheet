package com.thitasheet.slidingWindow


/**
 * Time complexity:  O(N * W)  where  N  is the length of the string and  W  is the maximum contiguous block size.
 * * In the worst case (e.g.,  "aaaaaa" ),  W  can be equal to  N , which leads to  O(N^2)  operations.
 *
 * Space complexity: O(N)
 * * We use a 2D integer array of size  26 x (N + 1) , which requires linear memory with respect to the length of the string.
 *
 * [2981. Find Longest Special Substring That Occurs Thrice I](https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-i/description/)
 */
class FindLongestSpecialSubstringThatOccursThriceI {

    fun maximumLength(s: String): Int {
        val n = s.length

        // freq[charIndex][length] stores the frequency of special substrings
        // of a specific character and length
        val freq = Array(26) { IntArray(n + 1) }
        var left = 0
        var maxLength = -1

        for (right in s.indices) {
            // If the current character is different, reset the left boundary of the window
            if (s[right] != s[left]) {
                left = right
            }

            val charIndex = s[right] - 'a'
            val currentWindowSize = right - left + 1

            // Increment frequencies of all valid special substrings ending at 'right'
            for (len in 1..currentWindowSize) {
                freq[charIndex][len]++

                // If any special substring has occurred at least thrice
                if (freq[charIndex][len] >= 3) {
                    maxLength = maxOf(maxLength, len)
                }
            }
        }
        return maxLength
    }

    /**
     * Time Complexity:  O(N^2) to generate and count substrings. For  N = 50 , this is extremely fast. However, for  N = 5 * 10^5
     *
     * Space Complexity:  O(N^2) to store all generated substrings in the hash map.
     */
    fun maximumLengthBruteForce(s: String): Int {
        val frequencies = mutableMapOf<String, Int>()
        val n = s.length

        for (i in 0 until n) {
            val char = s[i]
            val stringBuilder = StringBuilder()
            for (j in i until n) {
                if (char != s[j]) break

                stringBuilder.append(s[j]).toString()
                frequencies[stringBuilder.toString()] = frequencies.getOrDefault(stringBuilder.toString(), 0) + 1
            }
        }

        var maxLength = -1
        for ((string, count) in frequencies) {
            if (count >= 3) {
                maxLength = maxOf(maxLength, string.length)
            }
        }
        return maxLength
    }
}