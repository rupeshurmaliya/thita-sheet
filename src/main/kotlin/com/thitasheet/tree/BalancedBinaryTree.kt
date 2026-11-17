package com.thitasheet.tree

import kotlin.math.abs


/**
 *
 * **Time Complexity: O(n)**
 *
 *      • We visit every node exactly once.
 *      • Unlike the "naive" O(n^2) solution, we calculate the height and the balance factor in a single pass.
 *
 * **Space Complexity: O(h)**
 *
 *      • This is the space used by the recursion stack.
 *      • In the worst case (a skewed tree), it is O(n).
 *      • In a balanced tree, it is O(log n).
 *
 * [110. Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/description/?envType=problem-list-v2&envId=wj7n672g)
 *
 * Extension of [MaximumDepthOfBinaryTree]
 */

class BalancedBinaryTree {

    fun isBalanced(root: TreeNode?): Boolean {

        fun checkHeight(node: TreeNode?): Int {
            if (node == null) return 0

            // 1. Check the left subtree
            val left = checkHeight(node.left)
            // Left subtree is already unbalanced; propagate failure early
            if (left == -1) return -1

            // 1. Check the right subtree
            val right = checkHeight(node.right)
            // Right subtree is already unbalanced; propagate failure early
            if (right == -1) return -1

            // 3. Check current node: is the difference > 1?
            if (abs(left - right) > 1) return -1

            // 4. If balanced, return the actual height
            return 1 + maxOf(left, right)
        }

        // If our height function returns -1, it means the tree is unbalanced
        return checkHeight(root) != -1
    }
}