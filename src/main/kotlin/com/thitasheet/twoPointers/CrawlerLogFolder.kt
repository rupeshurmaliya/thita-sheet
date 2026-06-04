package com.thitasheet.twoPointers

/**
 *   • Time Complexity:  O(N)
 *   We perform a single pass over the  logs  array containing  N  elements. Within the loop, checking string conditions is an  O(1)  average-case operation.
 *
 *
 *   • Space Complexity:  O(1)
 *   We only maintain a single integer variable  depth  to track folder levels. No auxiliary collections are used.
 *
 * [1598. Crawler Log Folder](https://leetcode.com/problems/crawler-log-folder/)
 */

class CrawlerLogFolder {

    fun minOperationsKotlin(logs: Array<String>): Int {
        return logs.fold(0) { depth, log ->
            when (log) {
                "../" -> maxOf(0, depth - 1)
                "./" -> depth
                else -> depth + 1
            }
        }
    }

    fun minOperations(logs: Array<String>): Int {
        // Track the current nesting depth of the directory
        var depth = 0

        for (log in logs) {
            when (log) {
                "../" -> {
                    // Go up one level, but do not go below root (depth 0)
                    if (depth > 0) {
                        depth--
                    }
                }
                "./" -> {
                    // Remain in the current directory, do nothing
                }
                else -> {
                    // Move down into a child directory
                    depth++
                }
            }
        }

        // The final depth is the number of moves needed to return to the root
        return depth
    }
}