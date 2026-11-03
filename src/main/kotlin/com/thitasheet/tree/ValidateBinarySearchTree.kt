package com.thitasheet.tree


/**
 * `Time Complexity: O(n)`
 *
 * * n is the number of nodes in the tree.
 * * In the worst case (a valid BST), we have to visit every single node exactly once to verify it doesn't break the rules.
 *
 * `Space Complexity: O(h)`
 *
 * * h is the height of the tree.
 * * This space is occupied by the Recursion Stack.
 * * In a well-balanced tree, the height is log n.
 * * In a "degenerate" tree (where every node only has a right child, looking like a linked list), the height is n, so the space would be O(n).
 *
 * [98. Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/description/?envType=problem-list-v2&envId=wj7n672g)
 */

class ValidateBinarySearchTree {

    fun isValidBST(root: TreeNode?): Boolean {

        fun validate(node: TreeNode?, min: Long, max: Long): Boolean {
            // An empty tree is a valid BST
            if (node == null) return true

            // The current node's value must be strictly within the min and max
            if (node.`val` <= min || node.`val` >= max) {
                return false
            }

            // When going LEFT: The new MAX becomes the current node's value
            // When going RIGHT: The new MIN becomes the current node's value
            return validate(node.left, min, node.`val`.toLong()) && validate(node.right, node.`val`.toLong(), max)
        }

        // We use Long to handle the edge case of Integer.MIN_VALUE or MAX_VALUE
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE)

    }
}