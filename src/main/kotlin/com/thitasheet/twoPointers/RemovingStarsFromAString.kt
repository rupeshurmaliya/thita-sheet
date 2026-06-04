package com.thitasheet.twoPointers


/**
 *      Time complexity: O(N)
 *   We perform a single pass over the string of length  N  to push and pop characters. Each push and pop on  ArrayDeque  takes  O(1)  amortized time. Reconstructing the string from
 *   the stack takes  O(N)  time.
 *
 *      Space complexity: O(N)
 *  In the worst case (no stars), we store all  N  characters in the stack.
 * [2390. Removing Stars From a String](https://leetcode.com/problems/removing-stars-from-a-string/description/?envType=problem-list-v2&envId=dogesdee)
 */
class RemovingStarsFromAString {

    fun removeStars(s: String): String {
        // Deque to store characters as a Stack
        val stack = ArrayDeque<Char>()

        for (char in s) {
            // Delete the most recent character
            if (char == '*' && stack.isNotEmpty()) {
                stack.removeLast()
            } else {
                // Accumulate the letter
                stack.addLast(char)
            }
        }

        // Build the final string from remaining elements
        val result = StringBuilder()
        while (stack.isNotEmpty()) {
            result.append(stack.removeFirst())
        }
        return result.toString()
    }

    /**
     * Time Complexity:  O(N)
     *
     *  Space Complexity:  O(N)
     *
     *  Although both approaches have  O(N)  time and space complexity, the Two Pointer approach is significantly more efficient in practice:
     *  1. No Boxing Overhead: The Deque approach ( ArrayDeque<Char> ) forces Kotlin to box primitive  Char  types into wrapper  Character  objects, creating garbage collection
     *   overhead. The Two Pointer approach operates directly on primitive  char  arrays.
     *
     *  2. No Dynamic Reallocation: The Deque and  StringBuilder  dynamically resize their internal array buffers as they grow. The Two Pointer approach allocates the maximum size once
     *   up-front, avoiding reallocation overhead.
     */
    fun removeStarsTwoPointers(s: String): String {
        val chars = s.toCharArray()
        var writePointer = 0

        for (readPointer in chars.indices) {
            if (chars[readPointer] == '*') {
                // Delete the last character written by stepping back
                writePointer--
            } else {
                // Write the letter at the write position and advance
                chars[writePointer] = chars[readPointer]
                writePointer++
            }
        }

        // Build the string up to the write pointer
        return String(chars, 0, writePointer)
    }
}