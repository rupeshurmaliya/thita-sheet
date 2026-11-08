package com.thitasheet.tree

import java.util.*


/**
 *
 *    Time Complexity:
 *  - hasNext(): O(1) — just a stack emptiness check.
 *  - next(): Average O(1). Although pushAllLeft can take O(h) in a single call, every node in the tree is pushed and popped from the stack exactly once across all calls to next(). Thus, for N nodes, N calls to next() take O(N) time total.
 *
 *
 *     Space Complexity: O(h),
 *  - where h is the height of the tree. The stack stores at most the nodes from the root to the deepest leaf.
 *
 *  * Problem Logic
 *
 *   1. Initialization (init):
 *       • Starting from the root, traverse down the left children as far as possible, pushing every node onto the stack.
 *       • When we hit null, the top of the stack holds the minimum value of the entire BST.
 *   2. Advancing to Next Element (next):
 *       • Pop the top element from the stack (smallestNode = stack.removeLast()).
 *       • If smallestNode has a right child, that right subtree contains elements larger than smallestNode but smaller than smallestNode's parent.
 *       • Therefore, we visit smallestNode.right and immediately push all of its leftmost descendants onto the stack.
 *       • Return smallestNode.val.
 *   3. Checking for Remaining Elements (hasNext):
 *       • If the stack is not empty, there is at least one unvisited node remaining. Return stack.isNotEmpty().
 *
 *
 *
 * [173. Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator/description/?envType=problem-list-v2&envId=wj7n672g)
 *
 * [Solution explanation](https://www.youtube.com/watch?v=RXy5RzGF5wo)
 */

class BinarySearchTreeIterator(root: TreeNode?) {

    // Stack to maintain active ancestors along the current left spine
    private val stack = Stack<TreeNode>()

    init {
        // Push all nodes along the leftmost path starting from the root
        pushAllLeft(root)
    }

    /** Returns the next smallest number in the in-order traversal */
    fun next(): Int {
        // The top of the stack is always the next smallest element
        val smallestNode = stack.pop()

        // If the processed node has a right child, push its leftmost branch
        if (smallestNode.right != null) {
            pushAllLeft(smallestNode)
        }
        return smallestNode.`val`
    }

    /** Returns whether we have a next smallest number */
    fun hasNext(): Boolean {
        return stack.isEmpty().not()
    }

    /** Helper method to push a node and all of its left descendants onto the stack */
    private fun pushAllLeft(node: TreeNode?) {
        var current = node
        while (current != null) {
            stack.push(node)
            current = current.left
        }
    }


    /**
     * * Fails the O(H) Space Follow-up: Storing all nodes requires O(N) memory. If N = 100_000, we allocate memory for 100,000 integers even if the tree height H is only 17.
     *
     * * Not Lazy (High Startup Cost): The constructor does O(N) work upfront. If the caller only inspects the first few elements or stops early, all remaining traversal work and memory allocation are wasted.
     */
    class BSTIteratorBruteForce(root: TreeNode?) {
        // Flatten the entire BST into a sorted list
        private val values = mutableListOf<Int>()
        private var currentIndex = 0

        init {
            inorderTraversal(root)
        }

        private fun inorderTraversal(node: TreeNode?) {
            if (node == null) return
            inorderTraversal(node.left)
            values.add(node.`val`)
            inorderTraversal(node.right)
        }

        fun next(): Int {
            return values[currentIndex++]
        }

        fun hasNext(): Boolean {
            return currentIndex < values.size
        }
    }

}