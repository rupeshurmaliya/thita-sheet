package com.thitasheet.tree


/**
 * Time Complexity: O(n)
 *  ```
 *  Why? We visit every node in the tree exactly once.
 *  Operations:
 *  queue.removeFirst() and queue.add() are O(1) operations in an ArrayDeque.
 *
 *  Adding an element to an ArrayList is O(1).
 *
 *  Result: Since we do a constant amount of work for all n nodes, the total time is linear.
 * ```
 *
 * Space Complexity: O(w)
 * * Why? The "extra" space we use (the space not counting the final result list) is determined by the maximum width of the tree (w).
 * * The Queue: At any given time, the queue holds at most one full level of nodes. In a perfect binary tree, the bottom level has n/2 nodes.
 * * The Depth: Even in a very deep, skinny tree (a line), the queue would only hold 1 node at a time, but the recursion-like depth isn't an issue here because we are using a loop, not recursion
 *
 * [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/description/?envType=problem-list-v2&envId=wj7n672g)
 */

class BinaryTreeLevelOrderTraversal {

    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (root == null) return result

        // ArrayDeque is generally more efficient than LinkedList
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)

        while (!queue.isEmpty()) {
            val levelSize = queue.size
            // Pre-size the ArrayList to avoid repeated resizing
            val currentList = ArrayList<Int>(levelSize)

            repeat(levelSize) {
                val current = queue.removeFirst()
                current.left?.let { queue.add(it) }
                current.right?.let { queue.add(it) }
                currentList.add(current.`val`)
            }
            result.add(currentList)
        }
        return result
    }
}