package com.thitasheet.tree


/**
 * Time complexity:
 *
 *
 * Space complexity:
 *
 * []()
 */
class SameTree {

    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        return when {
            // Both nodes are null: structural match at this branch
            p == null && q == null -> true

            // Only one node is null: structural mismatch
            p == null || q == null -> false

            // Values of the current nodes differ: value mismatch
            p.`val` != q.`val` -> false

            // Recursively verify left and right subtrees
            else -> isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
        }
    }
}