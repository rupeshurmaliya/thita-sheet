package com.thitasheet.twoPointers

/**
 *
 * [](https://leetcode.com/problems/crawler-log-folder/)
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