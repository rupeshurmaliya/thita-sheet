package com.thitasheet.tree


/**
 * Time Complexity: O(N)
 * * Each of the N nodes is enqueued and dequeued exactly once.
 * * Arithmetic addition and queue insertions/removals take O(1) time per node.
 * * Overall time scales strictly linearly with tree size N.
 *
 * Space Complexity: O(N)
 * * The queue holds at most the maximum width W of the binary tree.
 * * In a complete binary tree, the leaf level contains up to ceil(N / 2) nodes, requiring O(N) auxiliary memory in the queue.
 *
 * [1161. Maximum Level Sum of a Binary Tree](https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree)
 */

class MaximumLevelSumOfABinaryTree {

    fun maxLevelSum(root: TreeNode?): Int {
        // Base case: empty tree guard
        if (root == null) return 0

        var currentLevel = 1
        var bestLevel = 1
        var maxSum = root.`val`
        val queue = ArrayDeque<TreeNode>()

        // Seed queue with root
        queue.add(root)

        while (!queue.isEmpty()) {
            val levelSize = queue.size
            var levelSum = 0

            // Sum all node values at the current horizontal tier
            repeat(levelSize) {
                val node = queue.removeFirst()
                levelSum += node.`val`

                // Enqueue children for the subsequent row
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }

            // Strictly greater ensures we keep the smallest level index on ties
            if (levelSum > maxSum) {
                maxSum = levelSum
                bestLevel = currentLevel
            }
            currentLevel++
        }

        return bestLevel
    }
}