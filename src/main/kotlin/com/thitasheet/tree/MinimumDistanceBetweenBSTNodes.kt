package com.thitasheet.tree


/**
 * **• Time Complexity: O(N)**
 *
 *       • Every node in the BST is visited exactly once.
 *       • At each node, only O(1) operations (subtraction, comparison, and assignment) are performed.
 *
 * **• Space Complexity: O(H)**
 *
 *        • Auxiliary heap memory: O(1) (no list or collection allocated).
 *        • Call stack: O(H), where H is the height of the tree.
 *        • Balanced BST: H = O(log N).
 *        • Skewed BST (worst case): H = O(N).
 *
 * [783. Minimum Distance Between BST Nodes](https://leetcode.com/problems/minimum-distance-between-bst-nodes/)
 *
 * Similar question - 530. Minimum Absolute Difference in BST [MinimumAbsoluteDifferenceInBST]
 */

class MinimumDistanceBetweenBSTNodes {

    fun minDiffInBST(root: TreeNode?): Int {
        // Initial minDiff value
        var minDiff = Int.MAX_VALUE
        var prevValue: Int? = null

        fun inorderTraversal(node: TreeNode?) {
            if (node == null) return
            // 1. Traverse left subtree (smaller values)
            inorderTraversal(node.left)
            // 2. Process current node
            if (prevValue != null) {
                val diff = node.`val` - prevValue!!
                minDiff = minOf(minDiff, diff)
            }
            prevValue = node.`val`
            // 3. Traverse right subtree (larger values)
            inorderTraversal(node.right)
        }

        inorderTraversal(root)
        return minDiff
    }
}