package com.thitasheet.tree


/**
 *
 *  Time Complexity: O(N), where N is the number of nodes in the binary tree. We visit each node in the tree exactly once.
 *
 *  Space Complexity: O(H), where H is the height of the tree. This is the maximum space occupied by the recursive call stack.
 * * In a balanced tree, the space complexity is O(log N).
 * * In a skewed tree (worst-case), the space complexity is O(N).
 *
 * [226. Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/description/?envType=problem-list-v2&envId=xlinfmnt)
 */
class InvertBinaryTree {

    fun invertTree(root: TreeNode?): TreeNode? {
        // Base case: empty subtree requires no operation
        if (root == null) {
            return null
        }

        // Bottom-up: invert subtrees first (Recurse-First / Post-order)
        val invertedLeft = invertTree(root.left)
        val invertedRight = invertTree(root.right)

        root.left = invertedRight
        root.right = invertedLeft
        return root
    }

    fun invert(root: TreeNode?): TreeNode? {
        if (root == null) return null

        // Perform an in-place swap of the left and right children (Swap-First / Pre-order)
        val temp = root.left
        root.left = root.right
        root.right = temp

        // Recursively invert the subtrees
        invert(root.left)
        invert(root.right)

        return root
    }

    fun invertTreeBFSIterative(root: TreeNode?): TreeNode? {
        if (root == null) {
            return null
        }

        val queue = ArrayDeque<TreeNode>()
        queue.addLast(root)

        while (!queue.isEmpty()) {
            val current = queue.removeFirst()

            // Swap left and right children
            val tempLeft = current.left
            current.left = current.right
            current.right = tempLeft

            // Add non-null children to queue for subsequent processing
            if (current.left != null) {
                queue.addLast(current.left!!)
            }
            if (current.right != null) {
                queue.addLast(current.right!!)
            }
        }

        return root
    }

    fun invertTreeDFSIterative(root: TreeNode?): TreeNode? {
        if (root == null) {
            return null
        }

        val stack = ArrayDeque<TreeNode>()
        stack.addLast(root)

        while (!stack.isEmpty()) {
            val current = stack.removeLast()

            // Swap children
            val tempLeft = current.left
            current.left = current.right
            current.right = tempLeft

            // Push children onto the stack
            if (current.left != null) {
                stack.addLast(current.left!!)
            }
            if (current.right != null) {
                stack.addLast(current.right!!)
            }
        }

        return root
    }
}
