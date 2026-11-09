package com.thitasheet.tree

import java.util.*


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

    /**
     * Performs an iterative in-order traversal of a binary tree.
     *
     * Time Complexity: O(n) - visits each node exactly once.
     *
     * Space Complexity: O(h) - where h is the height of the tree.
     */
    fun inorderTraversalIterative(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        // Use ArrayDeque (Modern, non-synchronized)
        val stack = ArrayDeque<TreeNode>()
        var current = root

        // We continue as long as there are nodes to visit or nodes on the stack
        while (current != null || !stack.isEmpty()) {
            // 1. Reach the leftmost node of the current subtree
            while (current != null) {
                stack.addLast(current)
                current = current.left
            }
            // 2. Current is null at this point, so we pop from stack
            val node = stack.removeLast()
            // 3. Visit the node (add to result)
            result.add(node.`val`)
            // 4. We have visited the left and the root, now go right
            current = node.right
        }

        return result
    }
}