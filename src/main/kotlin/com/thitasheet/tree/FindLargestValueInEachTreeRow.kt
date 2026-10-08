package com.thitasheet.tree


/**
 * Time Complexity: O(N)
 * * Each of the N nodes is enqueued and dequeued exactly once.
 * * Updating maxVal and checking children takes O(1) time per node.
 * * Total time is strictly O(N) where N is the number of nodes.
 *
 *  Space Complexity: O(N)
 *  * The queue stores at most the maximum row width W of the tree.
 *  * In a complete binary tree, the last row contains up to ceil(N / 2) nodes --> O(N) memory in the worst case.
 *  * The result list requires O(H) space where H is the height of the tree (H <= N).
 *
 * [515. Find Largest Value in Each Tree Row](https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/)
 */

class FindLargestValueInEachTreeRow {

    fun largestValues(root: TreeNode?): List<Int> {
        // Base case: empty tree has no rows
        if (root == null) return emptyList()

        val result = mutableListOf<Int>()
        val queue = ArrayDeque<TreeNode>()

        // Seed queue with root
        queue.add(root)

        while (!queue.isEmpty()) {
            val levelSize = queue.size
            var maxVal = Int.MIN_VALUE

            // Process all nodes in the current row
            repeat(levelSize) {
                val node = queue.removeFirst()
                maxVal = maxOf(maxVal, node.`val`)

                // Enqueue children for the subsequent row
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
            result.add(maxVal)
        }

        return result
    }
}