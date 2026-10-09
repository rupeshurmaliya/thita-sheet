package com.thitasheet.tree


/**
 * Time Complexity: O(n)
 * * n is the total number of nodes in the tree.
 * * We visit every node once to verify the mirror property.
 *
 * Space Complexity: O(h)
 * * h is the height of the tree.
 * * This is the space on the Recursion Stack.
 * * In the worst case (a skewed tree), h = n, so O(n). In a balanced tree, h = log n.
 *
 * [101. Symmetric Tree](https://leetcode.com/problems/symmetric-tree/submissions/1999545164/?envType=problem-list-v2&envId=wj7n672g)
 *
 */
class SymmetricTree {

    fun isSymmetric(root: TreeNode?): Boolean {
        // A null tree is symmetric
        if (root == null) return true

        // Start the mirror check with the left and right subtrees

        fun isMirror(t1: TreeNode?, t2: TreeNode?): Boolean {
            // 1. If both are null, they are symmetric
            if (t1 == null && t2 == null) return true

            // 2. If only one is null, or values don't match, they aren't symmetric
            if (t1 == null || t2 == null || t1.`val` != t2.`val`) return false

            // 3. The Mirror Rule:
            // Left of t1 must match Right of t2 (Outer match)
            // Right of t1 must match Left of t2 (Inner match)
            return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left)
        }

        return isMirror(root.left, root.right)
    }

}