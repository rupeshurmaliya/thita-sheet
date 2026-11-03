package com.thitasheet.tree


/**
 * `Time Complexity: O(n)`
 * * n is the total number of nodes in the tree.
 * * We must visit every single node exactly once to record its value.
 *
 * `Space Complexity: O(h)`
 * * h is the height of the tree.
 * * This space is used by the Recursion Stack.
 * * In a "balanced" tree, the height is log n.
 * * In the worst case (a "skewed" tree that looks like a straight line), the height is n, making the space complexity O(n).
 *
 *
 * [94. Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/?envType=problem-list-v2&envId=wj7n672g)
 */

class BinaryTreeInorderTraversal {

    fun inorderTraversal(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        if (root == null) return emptyList()

        fun inorder(node: TreeNode?) {
            if (node == null) return
            inorder(node.left)
            result.add(node.`val`)
            inorder(node.right)
        }

        inorder(root)
        return result
    }
}