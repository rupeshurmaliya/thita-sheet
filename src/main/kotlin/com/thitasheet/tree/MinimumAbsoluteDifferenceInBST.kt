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
 * [530. Minimum Absolute Difference in BST](https://leetcode.com/problems/minimum-absolute-difference-in-bst)
 *
 * Similar question - 783. Minimum Distance Between BST Nodes [MinimumAbsoluteDifferenceInBST]
 */

class MinimumAbsoluteDifferenceInBST {

    fun getMinimumDifference(root: TreeNode?): Int {
        // Initial minDiff value
        var minDiff = Int.MAX_VALUE
        var prevVal: Int? = null

        fun inorderTraversal(node: TreeNode?) {
            if (node == null) return

            // 1. Traverse left subtree (smaller values)
            inorderTraversal(node.left)
            // 2. Process current node
            if (prevVal != null) {
                val diff = node.`val` - prevVal!!
                if (minDiff > diff) minDiff = diff
            }
            prevVal = node.`val`
            // 3. Traverse right subtree (larger values)
            inorderTraversal(node.right)
        }

        inorderTraversal(root)
        return minDiff
    }


    /**
     * **• Time Complexity: O(N)**
     *
     *       • In-order Traversal: Visiting each of the N nodes takes O(N) time. Appending each node to mutableListOf is amortized O(1).
     *       • Difference Loop: Iterating through nodes from 1 to N - 1 performs N - 1 subtractions and comparisons, taking O(N) time.
     *       • Total Time: O(N) + O(N) = O(N), where N is the total number of nodes in the BST.
     *
     * **• Space Complexity: O(N)**
     *
     *       • List Storage: The nodes list stores all N integers, requiring O(N) auxiliary heap memory.
     *       • Call Stack: The recursive DFS call stack consumes O(H) space, where H is the height of the tree (O(log N) for a balanced tree, O(N) in the worst case for a skewed tree).
     *       • Total Space: O(N) (dominated by the nodes list).
     */
    fun getMinimumDifferenceBruteForce(root: TreeNode?): Int {
        val nodes = mutableListOf<Int>()

        fun traverse(node: TreeNode?) {
            if (node == null) return

            traverse(node.left)
            nodes.add(node.`val`)
            traverse(node.right)
        }

        traverse(root)

        var minDiff = Int.MAX_VALUE
        for (i in 1..nodes.lastIndex) {
            val diff = nodes[i] - nodes[i - 1]
            if (minDiff > diff) {
                minDiff = diff
            }
        }
        return minDiff
    }
}
