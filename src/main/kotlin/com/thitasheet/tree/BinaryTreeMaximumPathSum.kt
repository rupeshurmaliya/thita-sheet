package com.thitasheet.tree


/**
 * **Time Complexity: O(N)**, where N is the number of nodes. We visit each node exactly once.
 *
 * **Space Complexity: O(H)**, where H is the height of the tree. This is the space used by the recursion stack. In the worst case (a skewed tree), O(N); in a balanced tree, O(logN).
 *
 * [124. Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/description/)
 *
 * **Hint: Pattern 15: Tree DFS - Recursive Postorder Traversal**
 */

class BinaryTreeMaximumPathSum {

    fun maxPathSum(root: TreeNode?): Int {
        // Declare global variable to track the maximum path sum
        var maxPathSum = Int.MIN_VALUE

        // Helper function that calculates the maximum contribution (gain),
        // A node can provide to its parent.
        fun traverse(node: TreeNode?): Int {
            if (node == null) {
                return 0
            }

            // 1. Recursively find the max gain from left and right subtrees.
            // If the gain is negative, we ignore it by using maxOf(..., 0).
            val leftPath = maxOf(traverse(node.left), 0)
            val rightPath = maxOf(traverse(node.right), 0)

            // 2. The potential max path through this node (forming a "V" shape)
            // is the sum of the node's value and the gains from both children
            val currentPathSum = node.`val` + leftPath + rightPath

            // 3. Update the global maximum if this new path is better.
            maxPathSum = maxOf(currentPathSum, maxPathSum)

            // 4. Return the maximum "single branch" gain to the parent.
            // A path going to the parent can only include one of the two subtrees.
            return node.`val` + maxOf(leftPath, rightPath)
        }

        traverse(root)
        return maxPathSum
    }
}