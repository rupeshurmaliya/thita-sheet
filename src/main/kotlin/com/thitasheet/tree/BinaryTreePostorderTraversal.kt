package com.thitasheet.tree


/**
 *
 * Time Complexity: O(N)
 * * We visit every node in the tree exactly once.
 *
 * Space Complexity: O(H)
 * * This is the space used by the recursion stack. H is the height of the tree.
 * * In the worst case (skewed tree), O(N).
 * * In the best case (balanced tree), O(log N).
 *
 * [145. Binary Tree Postorder Traversal](https://leetcode.com/problems/binary-tree-postorder-traversal/description/?envType=problem-list-v2&envId=wj7n672g)
 *
 * **Hint: Pattern 15: Tree DFS - Recursive Postorder Traversal**
 */

class BinaryTreePostorderTraversal {

    fun postorderTraversal(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()

        fun postorder(node: TreeNode?) {
            // Base case: If the node is null, there's nothing to do
            if (node == null) return

            // 2. Traverse Left subtree
            postorder(node.left)
            // 2. Traverse Right subtree
            postorder(node.right)
            // 1. Visit Root: Add the current node's value to the list first
            result.add(node.`val`)
        }

        postorder(root)
        return result
    }
}